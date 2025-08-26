package com.nic.master.service.admservice.impl;

import com.nic.master.entity.adm.MstModule;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.adm.MstModuleRepository;
import com.nic.master.request.adm.modulerequest.MstModuleAddRequest;
import com.nic.master.request.adm.modulerequest.MstModuleUpdateRequest;
import com.nic.master.service.admservice.MstModuleService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MstModuleServiceImpl implements MstModuleService {

    private static final Logger logger = LoggerFactory.getLogger(MstModuleServiceImpl.class);
    private  final MstModuleRepository mstModuleRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;

    public MstModuleServiceImpl(MstModuleRepository mstModuleRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest) {
        this.mstModuleRepository = mstModuleRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public StatusParam addModule(MstModuleAddRequest modelAddRequest) {
        logger.info("  Adding Module  {}",modelAddRequest);

        try {
            if (mstModuleRepository.existsByModuleCodeIgnoreCase(modelAddRequest.getModuleCode())){
                logger.warn("Module Code already exists: {}", modelAddRequest.getModuleCode());
               return new StatusParam(false, "Module Code Already Exists");
               }
            MstModule mstModule = modelMapper.map(modelAddRequest, MstModule.class);
            mstModule.setModuleGuid(UUID.randomUUID().toString());
            mstModule.setCreatedDate(LocalDateTime.now());
            mstModule.setCreatedIpAddr(getClientIp());
            mstModule.setIsActive(true);
            mstModule.setCreatedBy("SYSTEM");
            mstModuleRepository.save(mstModule);
            logger.info("Added Module Successfully {}", mstModule);
            return new StatusParam(true, "Module Added Successfully");
        } catch (Exception ex) {
            logger.error("Error while adding module: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error while adding module: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<MstModule> getAllModules() {
        logger.info("Fetching all modules...");
        return mstModuleRepository.findAll();
    }

    @Override
    public MstModule getModuleByGuid(String moduleGuid) {
        logger.info("Fetching Module by GUID: {}", moduleGuid);
        return mstModuleRepository.findByModuleGuid(moduleGuid)
                .orElseThrow(() -> new RuntimeException("Module not found with GUID: " + moduleGuid));
    }

    @Override
    public SelectOptionParam getModuleByCode(String moduleCode) {
        logger.info("Fetching Module by Code: {}", moduleCode);
        return mstModuleRepository.findByModuleCode(moduleCode)
                .map(module -> new SelectOptionParam(
                        module.getModuleGuid(),
                        module.getModuleCode(),
                        module.getModuleName()
                ))
                .orElseThrow(() -> new RuntimeException("Module not found with code: " + moduleCode));

    }

    @Override
    public StatusParam updateModuleByGuid(String moduleGuid, MstModuleUpdateRequest mstModuleUpdateRequest) {
        logger.info("Updating Module with GUID: {}", moduleGuid);
        try {
            MstModule mstModule = mstModuleRepository.findById(moduleGuid)
                    .orElseThrow(() -> new RuntimeException("Module not found with GUID: " + moduleGuid));

            if (mstModuleUpdateRequest.getModuleCode() != null &&
                    !mstModuleUpdateRequest.getModuleCode().equalsIgnoreCase(mstModule.getModuleCode()) &&
                    mstModuleRepository.existsByModuleCodeIgnoreCase(mstModuleUpdateRequest.getModuleCode())) {
                return new StatusParam(false, "Module Code already exists: " + mstModuleUpdateRequest.getModuleCode());
            }
            modelMapper.map(mstModuleUpdateRequest, mstModule);
            mstModule.setModifiedDate(LocalDateTime.now());
            mstModule.setModifiedIpAddr(getClientIp());
            mstModule.setModifiedBy("SYSTEM");
            mstModuleRepository.save(mstModule);
            logger.info("Module updated successfully for GUID: {}", moduleGuid);
            return new StatusParam(true, "Module Updated Successfully");
        } catch (Exception ex) {
            logger.error("Error while updating module: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error while updating module: " + ex.getMessage(), ex);
        }
    }
    private String getClientIp() {
        String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getRemoteAddr();
        }
        return clientIp;
    }
}
