package com.nic.master.service.process.impl;

import com.nic.master.entity.process.MstSectionType;
import com.nic.master.entity.process.ProcessDef;
import com.nic.master.entity.process.ProcessDefDesc;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.MstSectionRepository;
import com.nic.master.repository.process.ProcessDefDescRepository;
import com.nic.master.repository.process.ProcessDefRepository;
import com.nic.master.requestDTO.process.processdefdescrequest.AddProcessDefDescRequest;
import com.nic.master.requestDTO.process.processdefdescrequest.UpdateProcessDefDescRequest;
import com.nic.master.responseDTO.Processdefdescresponse.ProcessDefDescResponse;
import com.nic.master.service.process.ProcessDefDescService;
import com.nic.master.util.IpAddressGenerator;
import com.nic.master.util.MacAddressGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProcessDefDescServiceImpl implements ProcessDefDescService {

    private static  final Logger logger = LoggerFactory.getLogger(ProcessDefDescServiceImpl.class);
    private final ProcessDefDescRepository processDefDescRepository;
    private final ProcessDefRepository processDefRepository;
    private final MstSectionRepository mstSectionRepository;
    private final ModelMapper modelMapper;
    private final IpAddressGenerator ipAddressGenerator;
    private final MacAddressGenerator macAddressGenerator;
    private final HttpServletRequest httpServletRequest;

    public ProcessDefDescServiceImpl(ProcessDefDescRepository processDefDescRepository, ProcessDefRepository processDefRepository, MstSectionRepository mstSectionRepository, ModelMapper modelMapper, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator, HttpServletRequest httpServletRequest) {
        this.processDefDescRepository = processDefDescRepository;
        this.processDefRepository = processDefRepository;
        this.mstSectionRepository = mstSectionRepository;
        this.modelMapper = modelMapper;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public StatusParam addProcessDefDesc(String processDefGuid, String sectionTypeGuid, AddProcessDefDescRequest addProcessDefDescRequest) {
        logger.info("Adding ProcessDefDesc..");
        try{
            ProcessDef processDef = processDefRepository.findByProcessDefGuid(processDefGuid)
                    .orElseThrow(()->{
                        return new ResourceNotFoundException("ProcessDef not found with Guid : " + processDefGuid);
                    });
            MstSectionType mstSectionType = mstSectionRepository.findBySectionTypeGuid(sectionTypeGuid)
                    .orElseThrow(()->{
                        return new ResourceNotFoundException("MstSectionType not found with Guid : " + sectionTypeGuid);
                    });
//            if(processDefDescRepository.existsByProcessDefAndMstSectionType(processDef,mstSectionType)){
//                return new StatusParam(false,"ProcessDefDesc already exists for ProcessDefGuid: " + processDefGuid +
//                        " and SectionTypeGuid: " + sectionTypeGuid);
//            }
            ProcessDefDesc processDefDesc = modelMapper.map(addProcessDefDescRequest, ProcessDefDesc.class);
            processDefDesc.setProcessDefDescGuid(UUID.randomUUID().toString());
            processDefDesc.setCreatedBy("SYSTEM");
            processDefDesc.setCreatedDate(LocalDateTime.now());
            processDefDesc.setCreatedUri(httpServletRequest.getRequestURI());
            processDefDesc.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processDefDesc.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            // Handle actionDueDate (ISO string → LocalDateTime)
            if (addProcessDefDescRequest.getActionDueDate() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                // parse as LocalDate and then convert to LocalDateTime at start of day
                processDefDesc.setActionDueDate(
                        LocalDate.parse(addProcessDefDescRequest.getActionDueDate(), formatter).atStartOfDay()
                );
            }
            // Check if a ProcessDefDesc with same processDef and levelNum already exists
            boolean exists = processDefDescRepository.existsByProcessDefAndLevelNum(processDef, addProcessDefDescRequest.getLevelNum());
            if (exists) {
                return new StatusParam(false, "Level number " + addProcessDefDescRequest.getLevelNum() +
                        " already exists for this ProcessDefGuid."+processDefGuid);
            }

            processDefDesc.setProcessDef(processDef);
            processDefDesc.setSectionType(mstSectionType);
            processDefDescRepository.save(processDefDesc);
            return new StatusParam(true,"ProcessDefDesc added successfully.");


        }  catch (IllegalArgumentException ex) {
            logger.error("Validation failed error. Request{}", addProcessDefDescRequest, ex);
            throw new RuntimeException("Error while adding ProcessDefDesc." + ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error("Error while adding ProcessDefDesc. Request{}", addProcessDefDescRequest, ex);
            throw new RuntimeException("Error while adding ProcessDefDesc" + ex.getMessage(), ex);
        }
    }

    @Override
    public List<ProcessDefDescResponse> getAllProcessDefDesc() {
        return processDefDescRepository.findAll()
                .stream()
                .map(processDefDesc -> {
                    ProcessDefDescResponse response = modelMapper.map(processDefDesc, ProcessDefDescResponse.class);
                    response.setProcessDefDescGuid(processDefDesc.getProcessDefDescGuid());
                    response.setProcessDefGuid(processDefDesc.getProcessDef().getProcessDefGuid());
                    response.setSectionTypeGuid(processDefDesc.getSectionType().getSectionTypeGuid());
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProcessDefDescResponse getProcessDefDescByGuid(String processDefDescGuid) {
        return processDefDescRepository.findByProcessDefDescGuid(processDefDescGuid.trim())
                .map(processDefDesc ->
                        modelMapper.map(processDefDesc, ProcessDefDescResponse.class))
                .orElseThrow(()->{
                    logger.error("ProcessDefDesc not found with GUID: {}", processDefDescGuid);
                    return new ResourceNotFoundException("ProcessDefDesc not found with GUID : " + processDefDescGuid);
                        }

                );
    }

//    @Override
//    public SelectOptionParam getByRoleCode(String roleCode) {
//        return processDefDescRepository.findByRoleCodeIgnoreCase(roleCode)
//                .map(processDefDesc -> {
//                           return new SelectOptionParam(
//                            processDefDesc.getProcessDefDescGuid(),
//                                    processDefDesc.getRoleCode(),
//                                    processDefDesc.getLevelDesc()
//                            );
//    })
//                .orElseThrow(() -> {
//                    logger.error("ProcessDefDesc not found with Role Code: {}", roleCode);
//                    return new ResourceNotFoundException("ProcessDefDesc not found with Role Code : " + roleCode);
//                });
//    }

    @Override
    public StatusParam updateProcessDefDesc(String processDefDescGuid, String sectionTypeGuid, String processDefGuid, UpdateProcessDefDescRequest updateProcessDefDescRequest) {
       try{

              ProcessDefDesc processDefDesc = processDefDescRepository.findByProcessDefDescGuid(processDefDescGuid.trim())
                     .orElseThrow(()->{
                          logger.error("ProcessDefDesc not found with GUID: {}", processDefDescGuid);
                          return new ResourceNotFoundException("ProcessDefDesc not found with GUID : " + processDefDescGuid);
                     });
           MstSectionType mstSectionType = mstSectionRepository.findBySectionTypeGuid(sectionTypeGuid)
                   .orElseThrow(()->{
                       return new ResourceNotFoundException("MstSectionType not found with Guid : " + sectionTypeGuid);
                   });
           ProcessDef processDef = processDefRepository.findByProcessDefGuid(processDefGuid)
                   .orElseThrow(()->{
                       return new ResourceNotFoundException("ProcessDef not found with Guid : " + processDefGuid);
                   });

              modelMapper.map(updateProcessDefDescRequest,processDefDesc);
              processDefDesc.setModifiedBy("SYSTEM");
              processDefDesc.setModifiedDate(LocalDateTime.now());
              processDefDesc.setModifiedUri(httpServletRequest.getRequestURI());
              processDefDesc.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
              processDefDesc.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
           if (updateProcessDefDescRequest.getActionDueDate() != null) {
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
               LocalDate date = LocalDate.parse(updateProcessDefDescRequest.getActionDueDate(), formatter);
               processDefDesc.setActionDueDate(date.atStartOfDay()); // convert to LocalDateTime
           }
              processDefDesc.setProcessDef(processDef);
              processDefDesc.setSectionType(mstSectionType);
              processDefDescRepository.save(processDefDesc);
              logger.info("ProcessDefDesc updated successfully");
              return new StatusParam(true,"ProcessDefDesc updated successfully");
       }catch (IllegalArgumentException ex){
           logger.error("Validation error while updating ProcessDefDesc. Request: {}", updateProcessDefDescRequest, ex);
           throw new RuntimeException("Error while updating ProcessDefDesc: " + ex.getMessage(), ex);
       }catch (Exception ex){
           logger.error("Error while updating ProcessDefDesc. Request: {}", updateProcessDefDescRequest, ex);
           throw new RuntimeException("Error while updating ProcessDefDesc: " + ex.getMessage(), ex);
       }
    }
}
