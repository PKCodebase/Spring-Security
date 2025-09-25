package com.nic.master.service.process.impl;

import com.nic.master.entity.process.ProcessDef;
import com.nic.master.entity.process.ProcessService;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.ProcessDefRepository;
import com.nic.master.repository.process.ProcessServiceRepository;
import com.nic.master.request.process.processservice.AddProcessServiceRequest;
import com.nic.master.request.process.processservice.UpdateProcessServiceRequest;
import com.nic.master.response.processServiceResponse.ProcessServiceResponse;
import com.nic.master.service.process.ProcessServices;
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
public class ProcessServiceImpl implements ProcessServices {

    private static  final Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);
    private final ProcessServiceRepository processServiceRepository;
    private final ModelMapper modelMapper;
    private final ProcessDefRepository processDefRepository;
    private final IpAddressGenerator ipAddressGenerator;
    private  final MacAddressGenerator macAddressGenerator;
    private final HttpServletRequest httpServletRequest;

    public ProcessServiceImpl(ProcessServiceRepository processServiceRepository, ModelMapper modelMapper, ProcessDefRepository processDefRepository, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator, HttpServletRequest httpServletRequest) {
        this.processServiceRepository = processServiceRepository;
        this.modelMapper = modelMapper;
        this.processDefRepository = processDefRepository;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public StatusParam addProcessService(String processDefGuid, AddProcessServiceRequest addProcessServiceRequest) {
        logger.info("Adding Process Service..");
        try{
            ProcessDef processDef = processDefRepository.findByProcessDefGuid(processDefGuid)
                    .orElseThrow(()->{
                        return new ResourceNotFoundException("ProcessDef not found with Guid : " + processDefGuid);
                    });
            ProcessService processService = modelMapper.map(addProcessServiceRequest, ProcessService.class);
            processService.setProcessDef(processDef);
            processService.setProcessServiceGuid(UUID.randomUUID().toString());
            processService.setCreatedBy("SYSTEM");
            processService.setCreatedUri(httpServletRequest.getRequestURI());
            processService.setCreatedDate(LocalDateTime.now());
            processService.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processService.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            processServiceRepository.save(processService);
            logger.info("Process Service saved successfully.");
            return new StatusParam(true,"Process Service added successfully");
        }catch (IllegalArgumentException ex){
            logger.error("Invalid argument provided: {}", ex.getMessage());
            throw new RuntimeException("Error while adding Process Service. " + ex.getMessage(), ex);
        }catch (Exception e){
            logger.error("Error occurred while adding Process Service: {}", e.getMessage());
            return new StatusParam(false,"Failed to add Process Service: " + e.getMessage());
        }
    }

    @Override
    public List<ProcessServiceResponse> getAllProcessServices() {
        return  processServiceRepository.findAll().stream()
                .map(processService -> modelMapper.map(processService, ProcessServiceResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProcessServiceResponse getProcessServiceByGuid(String processServiceGuid) {
        return processServiceRepository.findByProcessServiceGuid(processServiceGuid)
                .map(processService -> {
                    return modelMapper.map(processService, ProcessServiceResponse.class);
                }).orElseThrow(()->{
                    return new ResourceNotFoundException("Process Service not found with Guid : " + processServiceGuid);
                });
    }

    @Override
    public StatusParam updateProcessService(String processDefGuid, String processServiceGuid, UpdateProcessServiceRequest updateProcessServiceRequest) {
        logger.info("Updating Process Service...");
        try {
            ProcessDef processDef = processDefRepository.findByProcessDefGuid(processDefGuid)
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException("ProcessDef not found with Guid : " + processDefGuid);
                    });
            ProcessService processService = processServiceRepository.findByProcessServiceGuid(processServiceGuid)
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException("Process Service not found with Guid : " + processServiceGuid);
                    });
            modelMapper.map(updateProcessServiceRequest, processService);
            processService.setProcessDef(processDef);
            processService.setModifiedBy("SYSTEM");
            processService.setModifiedDate(LocalDateTime.now());
            processService.setModifiedUri(httpServletRequest.getRequestURI());
            processService.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processService.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
            processServiceRepository.save(processService);
            logger.info("Process Service updated successfully.");
            return new StatusParam(true, "Process Service updated successfully.");
        } catch (IllegalArgumentException ex) {
            logger.error("Invalid argument provided: {}", ex.getMessage());
            throw new RuntimeException("Error while updating Process Service. " + ex.getMessage(), ex);
        } catch (Exception e) {
            logger.error("Error occurred while updating Process Service: {}", e.getMessage());
            return new StatusParam(false, "Failed to update Process Service: " + e.getMessage());
        }
    }

}
