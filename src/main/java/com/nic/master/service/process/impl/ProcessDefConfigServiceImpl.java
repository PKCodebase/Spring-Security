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
import com.nic.master.util.MacAddressGenerator;
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
public class ProcessDefConfigServiceImpl implements ProcessDefConfigService {

    private static  final Logger logger = LoggerFactory.getLogger(ProcessDefConfigServiceImpl.class);
    private final ProcessDefConfigRepository processDefConfigRepository;
    private final ProcessDefRepository processDefRepository;
    private final ModelMapper modelMapper;
    private final IpAddressGenerator ipAddressGenerator;
    private final HttpServletRequest httpServletRequest;
    private final MacAddressGenerator macAddressGenerator;

    public ProcessDefConfigServiceImpl(ProcessDefConfigRepository processDefConfigRepository, ProcessDefRepository processDefRepository, ModelMapper modelMapper, IpAddressGenerator ipAddressGenerator, HttpServletRequest httpServletRequest, MacAddressGenerator macAddressGenerator) {
        this.processDefConfigRepository = processDefConfigRepository;
        this.processDefRepository = processDefRepository;
        this.modelMapper = modelMapper;
        this.ipAddressGenerator = ipAddressGenerator;
        this.httpServletRequest = httpServletRequest;
        this.macAddressGenerator = macAddressGenerator;
    }


    @Override
    public StatusParam addProcessDefConfig(String processDefGuid, AddProcessDefConfigRequest addProcessDefConfigRequest) {
        logger.info("Adding ProcessDefConfig..");
        try{
            ProcessDef processDef = processDefRepository.findByProcessDefGuid(processDefGuid)
                    .orElseThrow(()->{
                        return new ResourceNotFoundException("ProcessDef not found with Guid : " + processDefGuid);
                    });
            if (processDefConfigRepository.findByProcessDef(processDef).isPresent()) {
                return  new StatusParam(false,"ProcessDefConfig already exists for Guid: " + processDefGuid);
            }

            ProcessDefConfig processDefConfig = modelMapper.map(addProcessDefConfigRequest, ProcessDefConfig.class);
            processDefConfig.setProcessDefConfigGuid(UUID.randomUUID().toString());
            processDefConfig.setCreatedDate(LocalDateTime.now());
            processDefConfig.setCreatedBy("SYSTEM");
            processDefConfig.setCreatedUri(httpServletRequest.getRequestURI());
            processDefConfig.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processDefConfig.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
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
    public List<ProcessDefConfigResponse> getAllProcessDefConfig() {
        logger.info("Fetching all ProcessDefConfig...");
        return processDefConfigRepository.findAll().stream()
                .map(processDefConfig -> {
                    ProcessDefConfigResponse response = modelMapper.map(processDefConfig,ProcessDefConfigResponse.class);
                    response.setProcessDefConfigGuid(processDefConfig.getProcessDef().getProcessDefGuid());
                    return response;
                })
        .collect(Collectors.toList());
    }

//    @Override
//    public SelectOptionParam getProcessDefConfigByCode(String processDefConfigCode) {
//        return null;
//    }

    @Override
    public ProcessDefConfigResponse getProcessDefConfigByGuid(String processDefConfigGuid) {
        return processDefConfigRepository.findByProcessDefConfigGuid(processDefConfigGuid.trim())
                .map(processDefConfig -> modelMapper.map(processDefConfig,ProcessDefConfigResponse.class))
                .orElseThrow(()->{
                    logger.error("ProcessDefConfig not found with GUID: {}", processDefConfigGuid);
                    return new ResourceNotFoundException("ProcessDefConfig not found with GUID : " + processDefConfigGuid);
                });
    }

    @Override
    public StatusParam updateProcessDefConfig(String processDefGuid, String processDefConfigGuid, UpdateProcessDefConfigRequest updateProcessDefConfigRequest) {
        logger.info("Updating ProcessDefConfig..");
        try{
            ProcessDef processDef = processDefRepository.findByProcessDefGuid(processDefGuid)
                    .orElseThrow(()->{
                        logger.error("ProcessDef not found with GUID: {}", processDefGuid);
                        return new ResourceNotFoundException("ProcessDef not found with Guid : " + processDefGuid);
                    });
            ProcessDefConfig processDefConfig = processDefConfigRepository.findByProcessDefConfigGuid(processDefConfigGuid)
                    .orElseThrow(()->{
                        logger.error("ProcessDefConfig not found with GUID: {}", processDefConfigGuid);
                        return new ResourceNotFoundException("ProcessDefConfig not found with Guid : " + processDefConfigGuid);
                    });

            modelMapper.map(updateProcessDefConfigRequest,processDefConfig);
            processDefConfig.setModifiedBy("SYSTEM");
            if(updateProcessDefConfigRequest.getConfig() != null){
                processDefConfig.setConfig(updateProcessDefConfigRequest.getConfig());
            }
            processDefConfig.setModifiedDate(LocalDateTime.now());
            processDefConfig.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processDefConfig.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
            processDefConfig.setModifiedUri(httpServletRequest.getRequestURI());
            processDefConfig.setProcessDef(processDef);
            processDefConfigRepository.save(processDefConfig);
            logger.info("ProcessDefConfig updated successfully with GUID: {}", processDefConfig.getProcessDefConfigGuid());
            return new StatusParam(true, "ProcessDefConfig Updated Successfully");
        }catch (IllegalArgumentException ex){
            logger.error("Validation error while updating ProcessDefConfig. Request: {}", updateProcessDefConfigRequest, ex);
            throw new RuntimeException("Error while updating ProcessDefConfig: " + ex.getMessage(), ex);
        }
        catch (Exception ex){
            logger.error("Error while updating ProcessDefConfig. Request: {}", updateProcessDefConfigRequest, ex);
            throw new RuntimeException("Error while updating ProcessDefConfig: " + ex.getMessage(), ex);
        }
    }
}
