package com.nic.master.service.process.impl;

import com.nic.master.entity.process.ProcessDef;
import com.nic.master.entity.process.ProcessDefConfig;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.ProcessDefConfigRepository;
import com.nic.master.repository.process.ProcessDefRepository;
import com.nic.master.request.process.processdefconfigrequest.AddProcessDefConfigRequest;
import com.nic.master.request.process.processdefconfigrequest.UpdateProcessDefConfigRequest;
import com.nic.master.response.processdefconfigresponse.ProcessDefConfigResponse;
import com.nic.master.service.process.ProcessDefConfigService;
import com.nic.master.util.IpAddressGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProcessDefConfigServiceImpl implements ProcessDefConfigService {

    private static  final Logger logger = LoggerFactory.getLogger(ProcessDefConfigServiceImpl.class);
    private final ProcessDefConfigRepository processDefConfigRepository;
    private final ProcessDefRepository processDefRepository;
    private final ModelMapper modelMapper;
    private final IpAddressGenerator ipAddressGenerator;
    private final HttpServletRequest httpServletRequest;

    public ProcessDefConfigServiceImpl(ProcessDefConfigRepository processDefConfigRepository, ProcessDefRepository processDefRepository, ModelMapper modelMapper, IpAddressGenerator ipAddressGenerator, HttpServletRequest httpServletRequest) {
        this.processDefConfigRepository = processDefConfigRepository;
        this.processDefRepository = processDefRepository;
        this.modelMapper = modelMapper;
        this.ipAddressGenerator = ipAddressGenerator;
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public StatusParam addProcessDefConfig(String processDefGuid, AddProcessDefConfigRequest addProcessDefConfigRequest) {
        logger.info("Adding ProcessDefConfig..");
        try{
            ProcessDef processDef = processDefRepository.findByProcessDefGuid(processDefGuid)
                    .orElseThrow(()->{
                        return new ResourceNotFoundException("ProcessDef not found with Guid : " + processDefGuid);
                    });

            ProcessDefConfig processDefConfig = modelMapper.map(addProcessDefConfigRequest, ProcessDefConfig.class);
            processDefConfig.setProcessDefConfigGuid(UUID.randomUUID().toString());
            processDefConfig.setCreatedDate(LocalDateTime.now());
            processDefConfig.setCreatedBy("SYSTEM");
            processDefConfig.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processDefConfig.setCreatedMacAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processDefConfig.setProcessDef(processDef);
            processDefConfigRepository.save(processDefConfig);
            return new StatusParam(true, "ProcessDefConfig Added Successfully");
        }catch (IllegalArgumentException ex){
            logger.error("Validation error while adding ProcessDefConfig. Request: {}", addProcessDefConfigRequest, ex);
            throw new RuntimeException("Error while adding ProcessDefConfig: " + ex.getMessage(), ex);
        }
        catch (Exception ex){
            logger.error("Error while adding ProcessDefConfig. Request: {}", addProcessDefConfigRequest, ex);
            throw new RuntimeException("Error while adding ProcessDefConfig: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<ProcessDefConfig> getAllProcessDefConfig() {
        logger.info("Fetching all ProcessDefConfig...");
        return processDefConfigRepository.findAll();
    }

//    @Override
//    public SelectOptionParam getProcessDefConfigByCode(String processDefConfigCode) {
//        return null;
//    }

    @Override
    public ProcessDefConfigResponse getProcessDefConfigByGuid(String processDefConfigGuid) {
        return null;
    }

    @Override
    public StatusParam updateProcessDefConfig(String processDefGuid, String processDefConfigGuid, UpdateProcessDefConfigRequest updateProcessDefConfigRequest) {
        return null;
    }
}
