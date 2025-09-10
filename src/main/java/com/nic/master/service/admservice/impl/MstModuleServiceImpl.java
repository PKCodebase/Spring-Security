package com.nic.master.service.admservice.impl;

import com.nic.master.entity.adm.MstModule;
import com.nic.master.exception.IllegalArgumentException;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.adm.MstModuleRepository;
import com.nic.master.request.adm.modulerequest.MstModuleAddRequest;
import com.nic.master.request.adm.modulerequest.MstModuleUpdateRequest;
import com.nic.master.service.admservice.MstModuleService;
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
public class MstModuleServiceImpl implements MstModuleService {

    private static final Logger logger = LoggerFactory.getLogger(MstModuleServiceImpl.class);
    private final MstModuleRepository mstModuleRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;


    public MstModuleServiceImpl(MstModuleRepository mstModuleRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest, IpAddressGenerator ipAddressGenerator) {
        this.mstModuleRepository = mstModuleRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
    }

    @Override
    public StatusParam addModule(MstModuleAddRequest modelAddRequest) {
        logger.info("Adding new Module. Request: {}", modelAddRequest);
        try {
            if (mstModuleRepository.existsByModuleCodeIgnoreCase(modelAddRequest.getModuleCode().trim())) {
                logger.warn("Duplicate Module Code detected: {}", modelAddRequest.getModuleCode());
                return new StatusParam(false, "Module Code Already Exists : " + modelAddRequest.getModuleCode());
            }

            MstModule mstModule = modelMapper.map(modelAddRequest, MstModule.class);
            mstModule.setModuleGuid(UUID.randomUUID().toString());
            mstModule.setCreatedDate(LocalDateTime.now());
            mstModule.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
//            mstModule.setIsActive(true);
            mstModule.setCreatedBy("SYSTEM");

            mstModuleRepository.save(mstModule);
            logger.info("Module added successfully with GUID: {}", mstModule.getModuleGuid());
            return new StatusParam(true, "Module Added Successfully");
        } catch (IllegalArgumentException ex) {
            logger.error("Validation error while adding module. Request: {}", modelAddRequest, ex);
            throw new RuntimeException("Error while adding module: " + ex.getMessage(), ex);
        }
        catch (Exception ex) {
            logger.error("Error while adding module. Request: {}", modelAddRequest, ex);
            throw new RuntimeException("Error while adding module: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<MstModule> getAllModules() {
        logger.info("Fetching all modules...");
        List<MstModule> modules = mstModuleRepository.findAll();
        logger.info("Total modules fetched: {}", modules.size());
        return modules;
    }

    @Override
    public MstModule getModuleByGuid(String moduleGuid) {
        logger.info("Fetching Module by GUID: {}", moduleGuid);
        return mstModuleRepository.findByModuleGuid(moduleGuid.trim())
                .orElseThrow(() -> {
                    logger.warn("Module not found with GUID: {}", moduleGuid);
                    return new ResourceNotFoundException("Module not found with GUID: " + moduleGuid);
                });
    }

    @Override
    public SelectOptionParam getModuleByCode(String moduleCode) {
        logger.info("Fetching Module by Code: {}", moduleCode);
        return mstModuleRepository.findByModuleCodeIgnoreCase(moduleCode.trim())
                .map(module -> {
                    logger.info("Module found with Code: {}", moduleCode);
                    return new SelectOptionParam(
                            module.getModuleGuid(),
                            module.getModuleCode(),
                            module.getModuleName()
                    );
                })
                .orElseThrow(() -> {
                    logger.warn("Module not found with Code: {}", moduleCode);
                    return new ResourceNotFoundException("Module not found with code: " + moduleCode);
                });
    }

    @Override
    public StatusParam updateModuleByGuid(String moduleGuid, MstModuleUpdateRequest mstModuleUpdateRequest) {
        logger.info("Updating Module with GUID: {}. Request: {}", moduleGuid, mstModuleUpdateRequest);
        try {
            MstModule mstModule = mstModuleRepository.findById(moduleGuid.trim())
                    .orElseThrow(() -> {
                        logger.warn("Module not found with GUID: {}", moduleGuid);
                        return new ResourceNotFoundException("Module not found with GUID: " + moduleGuid);
                    });

            if (mstModuleUpdateRequest.getModuleCode() != null &&
                    !mstModuleUpdateRequest.getModuleCode().equalsIgnoreCase(mstModule.getModuleCode()) &&
                    mstModuleRepository.existsByModuleCodeIgnoreCase(mstModuleUpdateRequest.getModuleCode())) {
                logger.warn("Duplicate Module Code detected during update: {}", mstModuleUpdateRequest.getModuleCode());
                return new StatusParam(false, "Module Code already exists: " + mstModuleUpdateRequest.getModuleCode());
            }

            modelMapper.map(mstModuleUpdateRequest, mstModule);
            mstModule.setModifiedDate(LocalDateTime.now());
            mstModule.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstModule.setModifiedBy("SYSTEM");

            mstModuleRepository.save(mstModule);
            logger.info("Module updated successfully with GUID: {}", mstModule.getModuleGuid());
            return new StatusParam(true, "Module Updated Successfully");
        } catch (IllegalArgumentException ex) {
            logger.error("Validation error while updating module. GUID={}, Request={}", moduleGuid, mstModuleUpdateRequest, ex);
            throw new RuntimeException("Error while updating module: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error("Unexpected error while updating module. GUID={}, Request={}", moduleGuid, mstModuleUpdateRequest, ex);
            throw new RuntimeException("Error while updating module: " + ex.getMessage(), ex);
        }
    }

}


