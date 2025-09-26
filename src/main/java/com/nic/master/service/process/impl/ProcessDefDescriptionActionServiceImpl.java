package com.nic.master.service.process.impl;

import com.nic.master.entity.process.MstActionType;
import com.nic.master.entity.process.MstSectionType;
import com.nic.master.entity.process.ProcessDefDesc;
import com.nic.master.entity.process.ProcessDefDescAction;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.MstActionTypeRepository;
import com.nic.master.repository.process.MstSectionRepository;
import com.nic.master.repository.process.ProcessDefDescActionRepository;
import com.nic.master.repository.process.ProcessDefDescRepository;
import com.nic.master.requestDTO.process.processdefdescriptionaction.AddProcessDefDescActionRequest;
import com.nic.master.requestDTO.process.processdefdescriptionaction.UpdateProcessDefDescActionRequest;
import com.nic.master.responseDTO.processdefdescactionresponse.ProcessDefDescActionResponse;
import com.nic.master.service.process.ProcessDefDescActionService;
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
public class ProcessDefDescriptionActionServiceImpl implements ProcessDefDescActionService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessDefDescriptionActionServiceImpl.class);
    private final ProcessDefDescRepository processDefDescRepository;
    private final MstSectionRepository mstSectionRepository;
    private final MstActionTypeRepository mstActionTypeRepository;
    private final ProcessDefDescActionRepository processDefDescActionRepository;
    private final IpAddressGenerator ipAddressGenerator;
    private final MacAddressGenerator macAddressGenerator;
    private final HttpServletRequest httpServletRequest;
    private final ModelMapper modelMapper;

    public ProcessDefDescriptionActionServiceImpl(ProcessDefDescRepository processDefDescRepository, MstSectionRepository mstSectionRepository, MstActionTypeRepository mstActionTypeRepository, ProcessDefDescActionRepository processDefDescActionRepository, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator, HttpServletRequest httpServletRequest, ModelMapper modelMapper) {
        this.processDefDescRepository = processDefDescRepository;
        this.mstSectionRepository = mstSectionRepository;
        this.mstActionTypeRepository = mstActionTypeRepository;
        this.processDefDescActionRepository = processDefDescActionRepository;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
        this.httpServletRequest = httpServletRequest;
        this.modelMapper = modelMapper;
    }


    @Override
    public StatusParam addDescAction(String processDefDescGuid, String actionTypeGuid, String sectionTypeGuid, AddProcessDefDescActionRequest addProcessDefDescActionRequest) {
        logger.info("Adding Process Definition Description Action...");
        try {
            ProcessDefDesc processDefDesc = processDefDescRepository.findByProcessDefDescGuid(processDefDescGuid)
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException("Process Definition Description not found with Guid : " + processDefDescGuid);
                    });
            MstActionType mstActionType = mstActionTypeRepository.findByActionTypeGuid(actionTypeGuid)
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException("Action Type not found with Guid : " + actionTypeGuid);
                    });
            MstSectionType mstSectionType = mstSectionRepository.findBySectionTypeGuid(sectionTypeGuid)
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException("Section Type not found with Guid : " + sectionTypeGuid);
                    });


            boolean alreadyExists = processDefDescActionRepository
                    .existsByProcessDefDesc_ProcessDefDescGuidAndActionType_ActionTypeGuid(processDefDescGuid, actionTypeGuid);

            if (alreadyExists) {
                logger.warn("Duplicate entry detected for processDefDescGuid={} and actionTypeGuid={}", processDefDescGuid, actionTypeGuid);
                return new StatusParam(false, "Action already exists for this Process Definition Description and Action Type.");
            }

            if (Boolean.TRUE.equals(addProcessDefDescActionRequest.getHavePrefillData())
                    && (addProcessDefDescActionRequest.getPrefillQuery() == null || addProcessDefDescActionRequest.getPrefillQuery().trim().isEmpty())) {
                throw new IllegalArgumentException("Prefill query must be provided when havePrefillData = true");
            }

            if (Boolean.FALSE.equals(addProcessDefDescActionRequest.getHavePrefillData())
                    && addProcessDefDescActionRequest.getPrefillQuery() != null) {
                throw new IllegalArgumentException("Prefill query must be null when havePrefillData = false");
            }


            ProcessDefDescAction processDefDescAction = modelMapper.map(addProcessDefDescActionRequest, ProcessDefDescAction.class);

            processDefDescAction.setProcessDefDescActionGuid(UUID.randomUUID().toString());
            processDefDescAction.setProcessDefDesc(processDefDesc);
            processDefDescAction.setActionType(mstActionType);
            processDefDescAction.setSectionType(mstSectionType);

            processDefDescAction.setCreatedBy("SYSTEM");
            processDefDescAction.setCreatedDate(LocalDateTime.now());
            processDefDescAction.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            processDefDescAction.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            processDefDescAction.setCreatedUri(httpServletRequest.getRequestURI());


            processDefDescActionRepository.save(processDefDescAction);
            logger.info("Process Definition Description Action added successfully.");
            return new StatusParam(true, "Process Definition Description Action added successfully.");
        } catch (IllegalArgumentException ex) {
            logger.error("Validation error while adding Process Definition Description Action. Request: {}", addProcessDefDescActionRequest, ex);
            throw new RuntimeException("Error while adding Process Definition Description Action : " + ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error("Error while adding Process Definition Description Action. Request: {}", addProcessDefDescActionRequest, ex);
            throw new RuntimeException("Error while adding Process Definition Description Action : " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<ProcessDefDescActionResponse> getAllDescActions() {
        return processDefDescActionRepository.findAll()
                .stream()
                .map(processDefDescAction -> {
                    ProcessDefDescActionResponse response = modelMapper.map(
                            processDefDescAction,
                            ProcessDefDescActionResponse.class
                    );

                    if (processDefDescAction.getProcessDefDesc() != null) {
                        response.setProcessDefDescGuid(
                                processDefDescAction.getProcessDefDesc().getProcessDefDescGuid()
                        );
                    }

                    if (processDefDescAction.getActionType() != null) {
                        response.setActionTypeGuid(
                                processDefDescAction.getActionType().getActionTypeGuid()
                        );
                    }

                    if (processDefDescAction.getSectionType() != null) {
                        response.setSectionTypeGuid(
                                processDefDescAction.getSectionType().getSectionTypeGuid()
                        );
                    } else {
                        // Optional hai → safe null handle
                        response.setSectionTypeGuid(null);
                    }

                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProcessDefDescActionResponse getDescActionByGuid(String processDefDescActionGuid) {
        return processDefDescActionRepository.findByProcessDefDescActionGuid(processDefDescActionGuid)
                .map(processDefDescAction -> {
                    ProcessDefDescActionResponse response =
                            modelMapper.map(processDefDescAction, ProcessDefDescActionResponse.class);
                    if (processDefDescAction.getProcessDefDesc() != null) {
                        response.setProcessDefDescGuid(processDefDescAction.getProcessDefDesc().getProcessDefDescGuid());
                    }
                    if (processDefDescAction.getActionType() != null) {
                        response.setActionTypeGuid(processDefDescAction.getActionType().getActionTypeGuid());
                    }
                    if (processDefDescAction.getSectionType() != null) {
                        response.setSectionTypeGuid(processDefDescAction.getSectionType().getSectionTypeGuid());
                    }

                    return response;
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Process Definition Description Action not found with Guid : " + processDefDescActionGuid)
                );
    }

    @Override
    public StatusParam updateDescAction(String processDefDescGuid, String actionTypeGuid, String sectionTypeGuid, String processDefDescActionGuid, UpdateProcessDefDescActionRequest updateProcessDefDescActionRequest) {
        try{
        ProcessDefDesc processDefDesc = processDefDescRepository.findByProcessDefDescGuid(processDefDescGuid)
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Process Definition Description not found with Guid : " + processDefDescGuid);
                });
        MstActionType mstActionType = mstActionTypeRepository.findByActionTypeGuid(actionTypeGuid)
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Action Type not found with Guid : " + actionTypeGuid);
                });
        MstSectionType mstSectionType = mstSectionRepository.findBySectionTypeGuid(sectionTypeGuid)
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Section Type not found with Guid : " + sectionTypeGuid);
                });
        ProcessDefDescAction processDefDescAction = processDefDescActionRepository.findByProcessDefDescActionGuid(processDefDescActionGuid)
                .orElseThrow(() -> {
                    return new ResourceNotFoundException("Process Definition Description Action not found with Guid : " + processDefDescActionGuid);
                });
        modelMapper.map(updateProcessDefDescActionRequest, processDefDescAction);

        processDefDescAction.setModifiedBy("SYSTEM");
        processDefDescAction.setModifiedDate(LocalDateTime.now());
        processDefDescAction.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
        processDefDescAction.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
        processDefDescAction.setModifiedUri(httpServletRequest.getRequestURI());

        processDefDesc.setModifiedBy("SYSTEM");
        processDefDesc.setModifiedDate(LocalDateTime.now());
        processDefDesc.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
        processDefDesc.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
        processDefDesc.setModifiedUri(httpServletRequest.getRequestURI());

//            processDefDescAction.setProcessDefDesc(processDefDesc);
            processDefDescAction.setActionType(mstActionType);
            processDefDescAction.setSectionType(mstSectionType);
        processDefDescActionRepository.save(processDefDescAction);
        logger.info("Process Definition Description Action updated successfully.");
        return new StatusParam(true, "Process Definition Description Action updated successfully.");
    }catch (IllegalArgumentException ex) {
        logger.error("Validation error while updating Process Definition Description Action. Request: {}", updateProcessDefDescActionRequest, ex);
        throw new RuntimeException("Error while updating Process Definition Description Action : " + ex.getMessage(), ex);
    } catch (Exception ex) {
        logger.error("Error while updating Process Definition Description Action. Request: {}", updateProcessDefDescActionRequest, ex);
        throw new RuntimeException("Error while updating Process Definition Description Action : " + ex.getMessage(), ex);
    }
    }



}
