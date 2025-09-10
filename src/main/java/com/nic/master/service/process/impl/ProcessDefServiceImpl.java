package com.nic.master.service.process.impl;

import com.nic.master.entity.process.MstProcessType;
import com.nic.master.entity.process.ProcessDef;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.MstProcessTypeRepository;
import com.nic.master.repository.process.ProcessDefRepository;
import com.nic.master.request.process.processdefrequest.AddProcessDefRequest;
import com.nic.master.request.process.processdefrequest.UpdateProcessDefRequest;
import com.nic.master.response.processdefresponse.ProcessDefResponse;
import com.nic.master.service.process.ProcessDefService;
import com.nic.master.util.IpAddressGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProcessDefServiceImpl implements ProcessDefService {

    private static  final Logger logger = LoggerFactory.getLogger(ProcessDefServiceImpl.class);
    private final ProcessDefRepository processDefRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;
    private final MstProcessTypeRepository mstProcessTypeRepository;

    public ProcessDefServiceImpl(ProcessDefRepository processDefRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest,  IpAddressGenerator ipAddressGenerator,
                                 MstProcessTypeRepository mstProcessTypeRepository) {
        this.processDefRepository = processDefRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
        this.mstProcessTypeRepository = mstProcessTypeRepository;
    }

    @Override
    public StatusParam addProcessDef(String processTypeGuid, AddProcessDefRequest addProcessDefRequest) {
        logger.info("Adding ProcessDef..");
        try {
            MstProcessType mstProcessType = mstProcessTypeRepository.findByProcessTypeGuid(processTypeGuid)
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException("ProcessType not found with Guid : " + processTypeGuid);
                    });
            if (processDefRepository.existsByProcessDefCodeIgnoreCase(addProcessDefRequest.getProcessDefCode().trim())) {
                return new StatusParam(false, "ProcessDef already exists with code : " + addProcessDefRequest.getProcessDefCode());
            }
            ProcessDef processDef = modelMapper.map(addProcessDefRequest, ProcessDef.class);
            processDef.setProcessDefGuid(UUID.randomUUID().toString());
            processDef.setCreatedDate(LocalDateTime.now());
            processDef.setCreatedBy("SYSTEM");
            processDef.setCreatedMacAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processDef.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processDef.setProcessType(mstProcessType);
            processDefRepository.save(processDef);
            return new StatusParam(true, "ProcessDef Added successfully");
        } catch (IllegalArgumentException ex) {
            logger.error("Validation error while adding ProcessDef. Request{}", addProcessDefRequest, ex);
            throw new RuntimeException("Error while adding ProcessDef : " + ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error("Error while adding ProcessDef. Request{}", addProcessDefRequest, ex);
            throw new RuntimeException("Error while adding ProcessDef : " + ex.getMessage(), ex);
        }
    }

//    @Override
//    public List<ProcessDefResponse> getAllProcessDef() {
//        List<ProcessDef> all = processDefRepository.findAll();
//
//        return all.stream()
//                .map(pd -> modelMapper.map(pd, ProcessDefResponse.class))
//                .collect(Collectors.toList());
//    }

    @Override
    public List<ProcessDefResponse> getAllProcessDef() {
        return processDefRepository.findAll().stream()
                .map(processDef -> {
                    ProcessDefResponse response = modelMapper.map(processDef, ProcessDefResponse.class);
                    response.setProcessTypeNameEn(processDef.getProcessType().getProcessTypeNameEn()); // explicitly set
                    return response;
                })
                .collect(Collectors.toList());
    }


    @Override
    public ProcessDefResponse getProcessByGuid(String processDefGuid) {
        return processDefRepository.findByProcessDefGuid(processDefGuid.trim())
                .map((element) -> modelMapper.map(element, ProcessDefResponse.class))
                .orElseThrow(()->{
                    return new ResourceNotFoundException("ProcessDef not found with Guid : "+processDefGuid);
                });

    }

    @Override
    public SelectOptionParam getProcessDefByCode(String processDefCode) {
        logger.info("Fetching ProcessDef By Code..");
        return processDefRepository.findByProcessDefCodeIgnoreCase(processDefCode)
                .map(processDef -> {
                    return new SelectOptionParam(
                            processDef.getProcessDefGuid(),
                            processDef.getProcessDefCode(),
                            processDef.getProcessDefName()
                    );
                })
                .orElseThrow(()->{
                    return new ResourceNotFoundException("ProcessDef not found with Code : "+processDefCode);
                });
    }

    @Override
    public StatusParam updateProcessDef(String processTypeGuid, String processDefGuid, UpdateProcessDefRequest updateProcessDefRequest) {
        logger.info("Updating ProcessDef..");
        try{
           MstProcessType mstProcessType = mstProcessTypeRepository.findByProcessTypeGuid(processTypeGuid.trim())
                     .orElseThrow(()->{
                          return new ResourceNotFoundException("ProcessType not found with Guid : "+processTypeGuid);
                     });
            ProcessDef processDef = processDefRepository.findByProcessDefGuid(processDefGuid.trim())
                    .orElseThrow(()->{
                        return new ResourceNotFoundException("ProcessDef not found with Guid : "+processDefGuid);
                    });
            if(updateProcessDefRequest.getProcessDefCode() != null
            && !updateProcessDefRequest.getProcessDefCode().equalsIgnoreCase(processDef.getProcessDefCode())
            && processDefRepository.existsByProcessDefCodeIgnoreCase(updateProcessDefRequest.getProcessDefCode())){
                return new StatusParam(false,"ProcessDef already exists with code : "+updateProcessDefRequest.getProcessDefCode());
            }
            modelMapper.map(updateProcessDefRequest,processDef);
            processDef.setModifiedDate(LocalDateTime.now());
            processDef.setModifiedBy("SYSTEM");
            processDef.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processDef.setModifiedMacAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processDef.setProcessType(mstProcessType);
            processDefRepository.save(processDef);
            logger.info("ProcessDef updated successfully.");
            return new StatusParam(true,"ProcessDef updated successfully");
        }catch (IllegalArgumentException ex){
            logger.error("Validation error while updating ProcessDef. Guid={}, Request={}", processDefGuid, updateProcessDefRequest, ex);
            throw new RuntimeException("Error while updating ProcessDef : "+ex.getMessage(),ex);
        }catch ( Exception ex){
            logger.error("Error while updating ProcessDef. Guid={}, Request={}", processDefGuid, updateProcessDefRequest, ex);
            throw new RuntimeException("Error while updating ProcessDef : "+ex.getMessage(),ex);
        }
    }


}
