package com.nic.master.service.mstservice.impl;

import com.nic.master.entity.mst.Colony;
import com.nic.master.entity.mst.Ward;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.mst.ColonyRepository;
import com.nic.master.repository.mst.WardRepository;
import com.nic.master.requestDTO.mst.colonyrequest.ColonyAddRequest;
import com.nic.master.requestDTO.mst.colonyrequest.ColonyUpdateRequest;
import com.nic.master.responseDTO.colonyresponse.ColonyResponse;
import com.nic.master.service.mstservice.ColonyService;
import com.nic.master.util.IpAddressGenerator;
import com.nic.master.util.MacAddressGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ColonyServiceImpl implements ColonyService {

    private static final Logger logger = LoggerFactory.getLogger(ColonyServiceImpl.class);

    private final ColonyRepository colonyRepository;
    private final WardRepository wardRepository;
    private final HttpServletRequest httpServletRequest;
    private final ModelMapper modelMapper;
    private final IpAddressGenerator ipAddressGenerator;
    private final MacAddressGenerator macAddressGenerator;

    public ColonyServiceImpl(ColonyRepository colonyRepository, WardRepository wardRepository,
                             HttpServletRequest httpServletRequest, ModelMapper modelMapper, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator) {
        this.colonyRepository = colonyRepository;
        this.wardRepository = wardRepository;
        this.httpServletRequest = httpServletRequest;
        this.modelMapper = modelMapper;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
    }

    @Override
    public List<SelectOptionParam> fetchColonyMaster() {
        logger.info("Fetching active colonies for dropdown...");
        return colonyRepository.findByIsActive(true)
                .stream()
                .map(colony -> modelMapper.map(colony, SelectOptionParam.class))
                .toList();
    }

    @Override
    public SelectOptionParam fetchColonyMasterByCode(String colonyCode) {
        logger.info("Fetching colony by code: {}", colonyCode);
        return colonyRepository.findByColonyCodeIgnoreCase(colonyCode.trim())
                .map(colony -> new SelectOptionParam(
                        colony.getColonyGuid(),
                        colony.getColonyCode(),
                        colony.getColonyNameEn()
                ))
                .orElseThrow(() -> {
                    logger.warn("Colony not found with code: {}", colonyCode);
                    return new RuntimeException("Colony not found with guid:" + colonyCode);
                });
    }

    @Override
    @Transactional
    public StatusParam addColony(String wardGuid, ColonyAddRequest colonyAddRequest) {
        logger.info("Adding colony under ward GUID: {}", wardGuid);

        try {
            Ward ward = wardRepository.findById(wardGuid.trim()).orElse(null);
            if (ward == null) {
                logger.warn("Ward not found with GUID: {}", wardGuid);
                return new StatusParam(false, "Ward not found with GUID: " + wardGuid);
            }

            if (colonyRepository.existsByColonyCodeIgnoreCase(colonyAddRequest.getColonyCode().trim())) {
                logger.warn("Duplicate colony code found: {}", colonyAddRequest.getColonyCode());
                return new StatusParam(false, "Colony code already exists: " + colonyAddRequest.getColonyCode());
            }

            Colony colony = modelMapper.map(colonyAddRequest, Colony.class);
            colony.setColonyGuid(UUID.randomUUID().toString());
            colony.setCreatedDate(LocalDate.now());
            colony.setCreatedUri(httpServletRequest.getRequestURI());
            colony.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            colony.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            colony.setWard(ward);
            colony.setCreatedBy("SYSTEM");

            colonyRepository.save(colony);
            logger.info("Colony added successfully with GUID: {}", colony.getColonyGuid());
            return new StatusParam(true, "Colony added successfully with GUID: " + colony.getColonyGuid());
        }catch (IllegalArgumentException ex){
            logger.error("Validation error while adding colony. WardGuid={}, Request={}", wardGuid, colonyAddRequest, ex);
            throw new RuntimeException("Error while adding colony: " + ex.getMessage(), ex);
        }
        catch (Exception e) {
            logger.error("Error while adding colony. WardGuid={}, Request={}", wardGuid, colonyAddRequest, e);
            throw new RuntimeException("Error while adding colony : " + e.getMessage(), e);
        }
    }

    @Override
    public ColonyResponse getColonyByGuid(String colonyGuid) {
        logger.info("Fetching colony by GUID: {}", colonyGuid);
        return colonyRepository.findById(colonyGuid.trim())
                .map(colony -> modelMapper.map(colony, ColonyResponse.class))
                .orElseThrow(() -> {
                    logger.warn("Colony not found with GUID: {}", colonyGuid);
                    return new RuntimeException("Colony not found with GUID: " + colonyGuid);
                });
    }

    @Override
    public List<ColonyResponse> getAllColonies() {
        logger.info("Fetching all colonies...");
        return colonyRepository.findAll()
                .stream()
                .map(colony -> modelMapper.map(colony, ColonyResponse.class))
                .toList();
    }

    @Override
    public StatusParam updateColonyByGuid(String wardGuid, String colonyGuid, ColonyUpdateRequest colonyUpdateRequest) {
        logger.info("Updating colony with GUID: {} under ward GUID: {}", colonyGuid, wardGuid);

        try {
            Ward ward = wardRepository.findByWardGuid(wardGuid.trim()).orElse(null);
            if (ward == null) {
                logger.warn("Ward not found with GUID: {}", wardGuid);
                return new StatusParam(false, "Ward not found with GUID: " + wardGuid);
            }

            Colony colony = colonyRepository.findById(colonyGuid.trim()).orElse(null);
            if (colony == null) {
                logger.warn("Colony not found with GUID: {}", colonyGuid);
                return new StatusParam(false, "Colony not found with GUID: " + colonyGuid);
            }

            if (colonyUpdateRequest.getColonyCode() != null &&
                    !colonyUpdateRequest.getColonyCode().equalsIgnoreCase(colony.getColonyCode()) &&
                    colonyRepository.existsByColonyCodeIgnoreCase(colonyUpdateRequest.getColonyCode())) {
                logger.warn("Duplicate colony code attempted in update: {}", colonyUpdateRequest.getColonyCode());
                return new StatusParam(false, "Colony code already exists: " + colonyUpdateRequest.getColonyCode());
            }

            modelMapper.map(colonyUpdateRequest, colony);
            colony.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            colony.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
            colony.setModifiedUri(httpServletRequest.getRequestURI());
            colony.setModifiedDate(LocalDate.now());
            colony.setWard(ward);
            colony.setModifiedBy("SYSTEM");

            colonyRepository.save(colony);
            logger.info("Colony updated successfully with GUID: {}", colony.getColonyGuid());
            return new StatusParam(true, "Colony updated successfully with GUID: " + colony.getColonyGuid());
        }catch (IllegalArgumentException ex){
            logger.error("Validation error while updating colony. WardGuid={}, ColonyGuid={}, Request={}", wardGuid, colonyGuid, colonyUpdateRequest, ex);
            throw new RuntimeException("Error while updating colony: " + ex.getMessage(), ex);
        }
        catch (Exception e) {
            logger.error("Error while updating colony. WardGuid={}, ColonyGuid={}, Request={}", wardGuid, colonyGuid, colonyUpdateRequest, e);
            throw new RuntimeException("Error while updating colony");
        }
    }

}
