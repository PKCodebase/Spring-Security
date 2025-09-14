package com.nic.master.service.process.impl;

import com.nic.master.entity.process.MstActionType;
import com.nic.master.entity.process.ProcessAlert;
import com.nic.master.entity.process.ProcessDef;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.MstActionTypeRepository;
import com.nic.master.repository.process.ProcessAlertRepository;
import com.nic.master.repository.process.ProcessDefRepository;
import com.nic.master.request.process.processalertrequest.AddProcessAlertRequest;
import com.nic.master.request.process.processalertrequest.UpdateProcessAlertRequest;
import com.nic.master.response.Processdefdescresponse.ProcessDefDescResponse;
import com.nic.master.response.processalertresponse.ProcessAlertResponse;
import com.nic.master.service.process.ProcessAlertService;
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
public class ProcessAlertServiceImpl implements ProcessAlertService {

    private static  final Logger logger = LoggerFactory.getLogger(ProcessAlertServiceImpl.class);
    private final ProcessDefRepository processDefRepository;
    private final MstActionTypeRepository mstActionTypeRepository;
    private final ProcessAlertRepository processAlertRepository;
    private final ModelMapper modelMapper;
    private final IpAddressGenerator ipAddressGenerator;
    private final HttpServletRequest httpServletRequest;

    public ProcessAlertServiceImpl(ProcessDefRepository processDefRepository, MstActionTypeRepository mstActionTypeRepository, ProcessAlertRepository processAlertRepository, ModelMapper modelMapper, IpAddressGenerator ipAddressGenerator, HttpServletRequest httpServletRequest) {
        this.processDefRepository = processDefRepository;
        this.mstActionTypeRepository = mstActionTypeRepository;
        this.processAlertRepository = processAlertRepository;
        this.modelMapper = modelMapper;
        this.ipAddressGenerator = ipAddressGenerator;
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public StatusParam addProcessAlert(String processDefGuid, String actionTypeGuid, AddProcessAlertRequest addProcessAlertRequest) {
        logger.info("Adding ProcessAlert..");
        try {
            ProcessDef processDef = processDefRepository.findByProcessDefGuid(processDefGuid)
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException("ProcessDef not found with Guid : " + processDefGuid);
                    });
            MstActionType mstActionType = mstActionTypeRepository.findByActionTypeGuid(actionTypeGuid)
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException("MstActionType not found with Guid : " + actionTypeGuid);
                    });
            if (processAlertRepository.existsByProcessDefAndMstActionType(processDef, mstActionType)) {
                return new StatusParam(false, "ProcessAlert already exists for ProcessDefGuid : " + processDefGuid + " and ActionTypeGuid : " + actionTypeGuid);
            }
            ProcessAlert processAlert = modelMapper.map(addProcessAlertRequest, ProcessAlert.class);
            processAlert.setProcessAlertGuid(UUID.randomUUID().toString());
            processAlert.setCreatedDate(LocalDateTime.now());
            processAlert.setCreatedBy("SYSTEM");
            processAlert.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processAlert.setProcessDef(processDef);
            processAlert.setMstActionType(mstActionType);
            processAlert.setCreatedMacAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processAlertRepository.save(processAlert);
            logger.info("ProcessAlert added successfully.");
            return new StatusParam(true, "ProcessAlert added successfully.");
        }catch (IllegalArgumentException ex){
            logger.error("Validation error while adding ProcessAlert. Request: {}", addProcessAlertRequest, ex);
            throw new RuntimeException("Error while adding ProcessAlert: " + ex.getMessage(), ex);
        }
        catch (Exception ex){
            logger.error("Error while adding ProcessAlert. Request: {}", addProcessAlertRequest, ex);
            throw new RuntimeException("Error while adding ProcessAlert: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<ProcessAlertResponse> getAllProcessAlert() {
        return processAlertRepository.findAll()
                .stream()
                .map(processAlert -> {
                    ProcessAlertResponse response = modelMapper.map(processAlert, ProcessAlertResponse.class);
                    response.setProcessAlertGuid(processAlert.getProcessAlertGuid());
                    response.setProcessDefGuid(processAlert.getProcessDef().getProcessDefGuid());
                    response.setActionTypeGuid(processAlert.getMstActionType().getActionTypeGuid());
                    return response;
                })
                .collect(Collectors.toList());

    }

    @Override
    public ProcessAlertResponse getProcessAlertByGuid(String processAlertGuid) {
        return processAlertRepository.findByProcessAlertGuid(processAlertGuid.trim())
                .map(processAlert ->
                        modelMapper.map(processAlert,ProcessAlertResponse.class))
                .orElseThrow(()->{
                    logger.error("ProcessAlert not found with Guid: {}",processAlertGuid);
                    return new ResourceNotFoundException("ProcessAlert not found with Guid :"+processAlertGuid);
                });
    }

    @Override
    public StatusParam updateProcessAlert(String processDefGuid, String actionTypeGuid, String processAlertGuid, UpdateProcessAlertRequest updateProcessAlertRequest) {
        logger.info("Updating ProcessAlert..");
        try {
            ProcessDef processDef = processDefRepository.findByProcessDefGuid(processDefGuid)
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException("ProcessDef not found with Guid : " + processDefGuid);
                    });
            MstActionType mstActionType = mstActionTypeRepository.findByActionTypeGuid(actionTypeGuid)
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException("MstActionType not found with Guid : " + actionTypeGuid);
                    });
            ProcessAlert processAlert = processAlertRepository.findByProcessAlertGuid(processAlertGuid.trim())
                    .orElseThrow(() -> {
                        logger.error("ProcessAlert not found with Guid: {}", processAlertGuid);
                        return new ResourceNotFoundException("ProcessAlert not found with Guid :" + processAlertGuid);
                    });
            modelMapper.map(updateProcessAlertRequest, processAlert);
            processAlert.setModifiedBy("SYSTEM");
            processAlert.setModifiedDate(LocalDateTime.now());
            processAlert.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processAlert.setModifiedMacAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processAlert.setProcessDef(processDef);
            processAlert.setMstActionType(mstActionType);
            processAlertRepository.save(processAlert);
            logger.info("ProcessAlert updated successfully.");
            return new StatusParam(true, "ProcessAlert updated successfully.");
        }catch (IllegalArgumentException ex){
            logger.error("Validation error while updating ProcessAlert. Request: {}", updateProcessAlertRequest, ex);
            throw new RuntimeException("Error while updating ProcessAlert: " + ex.getMessage(), ex);
        }
        catch (Exception ex){
            logger.error("Error while updating ProcessAlert. Request: {}", updateProcessAlertRequest, ex);
            throw new RuntimeException("Error while updating ProcessAlert: " + ex.getMessage(), ex);
        }
    }
}
