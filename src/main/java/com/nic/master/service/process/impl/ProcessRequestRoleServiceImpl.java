package com.nic.master.service.process.impl;

import com.nic.master.entity.process.ProcessDef;
import com.nic.master.entity.process.ProcessedRequestRole;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.ProcessDefRepository;
import com.nic.master.repository.process.ProcessRequestRoleRepository;
import com.nic.master.request.process.processedrequestrole.AddProcessedRoleRequest;
import com.nic.master.request.process.processedrequestrole.UpdateProcessedRoleRequest;
import com.nic.master.response.processroleresponse.ProcessRoleResponse;
import com.nic.master.service.process.ProcessRequestRoleService;
import com.nic.master.util.IpAddressGenerator;
import com.nic.master.util.MacAddressGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProcessRequestRoleServiceImpl implements ProcessRequestRoleService {

    private  static final Logger logger = LoggerFactory.getLogger(ProcessRequestRoleServiceImpl.class);
    private final ProcessRequestRoleRepository processRequestRoleRepository;
    private final ModelMapper modelMapper;
    private final ProcessDefRepository processDefRepository;
    private final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;
    private final MacAddressGenerator macAddressGenerator;

    public ProcessRequestRoleServiceImpl(ProcessRequestRoleRepository processRequestRoleRepository, ModelMapper modelMapper, ProcessDefRepository processDefRepository, HttpServletRequest httpServletRequest, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator) {
        this.processRequestRoleRepository = processRequestRoleRepository;
        this.modelMapper = modelMapper;
        this.processDefRepository = processDefRepository;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
    }

    @Override
    public StatusParam addProcessRequestRole(String processDefGuid, AddProcessedRoleRequest addProcessedRoleRequest) {
        logger.info("Adding Process Request Role..");
        try{
            ProcessDef processDef = processDefRepository.findByProcessDefGuid(processDefGuid)
                    .orElseThrow(()->{
                        return new ResourceNotFoundException("Process Def  not found with Guid : " + processDefGuid);
                    });

            boolean exists = processRequestRoleRepository.existsByProcessDef_ProcessDefGuid(processDefGuid);
            if (exists) {
                logger.warn("Duplicate ProcessRequestRole found for ProcessDefGuid: {}", processDefGuid);
                return new StatusParam(false, "Process Request Role already exists for ProcessDefGuid: " + processDefGuid);
            }
            ProcessedRequestRole processedRequestRole = modelMapper.map(addProcessedRoleRequest,ProcessedRequestRole.class);
            processedRequestRole.setProcessedRequestRoleGuid(UUID.randomUUID().toString());
            processedRequestRole.setCreatedBy("SYSTEM");
            processedRequestRole.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processedRequestRole.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            processedRequestRole.setProcessDef(processDef);
            processRequestRoleRepository.save(processedRequestRole);
            logger.info("Process Request Role added successfully.");
            return new StatusParam(true,"Process Request Role added successfully.");
        }catch (IllegalArgumentException e){
            logger.error("Validation failed while adding Process Request Role. Request{} ",addProcessedRoleRequest,e);
            throw new RuntimeException("Error while adding Process Request Role. "+e.getMessage(),e);
        }catch (Exception ex){
            logger.error("Error while adding Process Request Role. Request{} ",addProcessedRoleRequest,ex);
            throw new RuntimeException("Error while adding Process Request Role. "+ex.getMessage(),ex);
        }
    }

    @Override
    public List<ProcessRoleResponse> getAllProcessRequestRoles() {
        return processRequestRoleRepository.findAll()
                .stream()
                .map(processedRequestRole -> {
                    ProcessRoleResponse processRoleResponse = modelMapper.map(processedRequestRole, ProcessRoleResponse.class);
                    processRoleResponse.setProcessDefGuid(processedRequestRole.getProcessDef().getProcessDefGuid());
                    return processRoleResponse;
                        }
                )
        .collect(Collectors.toList());
    }

    @Override
    public ProcessRoleResponse getProcessedRequestRoleByGuid(String processedRequestRoleGuid) {
        return processRequestRoleRepository.findByProcessedRequestRoleGuid(processedRequestRoleGuid)
                .map( (processedRequestRole )-> modelMapper.map(processedRequestRole,ProcessRoleResponse.class) )
                .orElseThrow(()->{
                    return new ResourceNotFoundException("Process Request Role not found with Guid : " + processedRequestRoleGuid);
                });
    }

    @Override
    public StatusParam updateProcessRequestRole(String processDefGuid, String processedRequestRoleGuid, UpdateProcessedRoleRequest updateProcessedRoleRequest) {
        return null;
    }

    @Override
    public List<ProcessRoleResponse> getProcessRequestRolesByApproveProcessedRoleCode(String approveProcessedRoleCode) {
        return List.of();
    }

    @Override
    public List<ProcessRoleResponse> getProcessRequestRolesByRejectProcessedRoleCode(String rejectProcessedRoleCode) {
        return List.of();
    }

    @Override
    public List<ProcessRoleResponse> getProcessRequestRolesByCloseProcessedRoleCode(String closeProcessedRoleCode) {
        return List.of();
    }

    @Override
    public List<ProcessRoleResponse> getProcessRequestRolesByProcessUserRoleCode(String processUserRoleCode) {
        return List.of();
    }
}
