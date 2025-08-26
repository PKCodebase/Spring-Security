package com.nic.master.service.admservice.impl;

import com.nic.master.entity.adm.MstRole;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.adm.MstRoleRepository;
import com.nic.master.request.adm.rolerequest.RoleAddRequest;
import com.nic.master.request.adm.rolerequest.RoleUpdateRequest;
import com.nic.master.service.admservice.MstRoleService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MstRoleServiceImpl implements MstRoleService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MstRoleServiceImpl.class);
    private final MstRoleRepository mstRoleRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;

    public MstRoleServiceImpl(MstRoleRepository mstRoleRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest) {
        this.mstRoleRepository = mstRoleRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public StatusParam addRole(RoleAddRequest roleAddRequest) {
        logger.info("Adding Role: {}", roleAddRequest);
         try{
             if(mstRoleRepository.existsByRoleCodeIgnoreCase(roleAddRequest.getRoleCode())){
                 logger.warn("Role Code already exists: {}", roleAddRequest.getRoleCode());
                 return new StatusParam(false,"Role Code Already Exists : " + roleAddRequest.getRoleCode());
             }
             MstRole mstRole = modelMapper.map(roleAddRequest, MstRole.class);
             mstRole.setRoleGuid(java.util.UUID.randomUUID().toString());
             mstRole.setCreatedDate(LocalDateTime.now());
             mstRole.setCreatedIpAddr(httpServletRequest.getRemoteAddr());
             mstRole.setIsActive(true);
                mstRole.setCreatedBy("SYSTEM");
                mstRoleRepository.save(mstRole);
                logger.info("Role added successfully: {}", mstRole);
                return new StatusParam(true, "Role Added Successfully");
         }catch(IllegalArgumentException ex){
             logger.error("Error while adding role: {}", ex.getMessage(), ex);
             throw new RuntimeException("Error while adding role: " + ex.getMessage(), ex);
         }
    }

    @Override
    public List<MstRole> getAllRoles() {
        logger.info("Fetching all roles...");
        return mstRoleRepository.findAll();
    }

    @Override
    public MstRole getRoleByCode(String roleCode) {
        logger.info("Fetching role by code: {}", roleCode);
        return mstRoleRepository.findByRoleCode(roleCode)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with code: " + roleCode));
    }

    @Override
    public SelectOptionParam getRoleByGuid(String roleGuid) {
        logger.info("Fetching role by GUID: {}", roleGuid);
        return mstRoleRepository.findByRoleGuid(roleGuid)
                .map(role -> new SelectOptionParam(
                        role.getRoleGuid(),
                        role.getRoleCode(),
                        role.getRoleName()
                ))
                .orElseThrow(()-> new ResourceNotFoundException("Role not found with GUID: " + roleGuid));

    }

    @Override
    public StatusParam updateRoleByGuid(String roleGuid, RoleUpdateRequest roleUpdateRequest) {
        logger.info("Updating role with GUID: {}", roleGuid);
        try{
            MstRole mstRole = mstRoleRepository.findByRoleGuid(roleGuid)
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found with GUID: " + roleGuid));

            if(roleUpdateRequest.getRoleCode() != null &&
                    !roleUpdateRequest.getRoleCode().equalsIgnoreCase(mstRole.getRoleCode())
                    && mstRoleRepository.existsByRoleCodeIgnoreCase(roleUpdateRequest.getRoleCode())){
                    return  new StatusParam(false,"Role Code already exists: " + roleUpdateRequest.getRoleCode());
            }
            modelMapper.map(roleUpdateRequest, mstRole);
            mstRole.setModifiedDate(LocalDateTime.now());
            mstRole.setModifiedIpAddr(getClientIp());
            mstRole.setModifiedBy("SYSTEM");
            mstRoleRepository.save(mstRole);
            logger.info("Role updated successfully: {}", mstRole);
            return new StatusParam(true, "Role Updated Successfully");
        }catch (IllegalArgumentException ex){
            logger.error("Error while updating role: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error while updating role: " + ex.getMessage(), ex);
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
