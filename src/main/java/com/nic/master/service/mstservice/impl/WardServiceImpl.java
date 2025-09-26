package com.nic.master.service.mstservice.impl;

import com.nic.master.entity.mst.Ward;
import com.nic.master.entity.mst.Zone;
import com.nic.master.exception.IllegalArgumentException;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.mst.WardRepository;
import com.nic.master.repository.mst.ZoneRepository;
import com.nic.master.requestDTO.mst.wardrequest.WardAddRequest;
import com.nic.master.requestDTO.mst.wardrequest.WardUpdateRequest;
import com.nic.master.responseDTO.wardresponse.WardResponse;
import com.nic.master.service.mstservice.WardService;
import com.nic.master.util.IpAddressGenerator;
import com.nic.master.util.MacAddressGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class WardServiceImpl implements WardService {

    private static final Logger logger = LoggerFactory.getLogger(WardServiceImpl.class);

    private final WardRepository wardRepository;
    private final ZoneRepository zoneRepository;
    private final HttpServletRequest httpServletRequest;
    private final ModelMapper modelMapper;
    private final IpAddressGenerator ipAddressGenerator;
    private final MacAddressGenerator macAddressGenerator;

    public WardServiceImpl(WardRepository wardRepository, ZoneRepository zoneRepository, HttpServletRequest httpServletRequest, ModelMapper modelMapper, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator) {
        this.wardRepository = wardRepository;
        this.zoneRepository = zoneRepository;
        this.httpServletRequest = httpServletRequest;
        this.modelMapper = modelMapper;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
    }


    @Override
    public List<SelectOptionParam> fetchWardMaster() {
        logger.info("Fetching active wards for dropdown...");
        List<SelectOptionParam> wards = wardRepository.findByIsActive(true)
                .stream()
                .map(ward -> modelMapper.map(ward, SelectOptionParam.class))
                .toList();
        logger.debug("Fetched {} active wards", wards.size());
        return wards;
    }

    @Override
    public SelectOptionParam fetchWardMasterByCode(String wardCode) {
        logger.info("Fetching ward by code: {}", wardCode);
        return wardRepository.findByWardCodeIgnoreCase(wardCode.trim())
                .map(ward -> new SelectOptionParam(
                        ward.getWardGuid(),
                        ward.getWardCode(),
                        ward.getWardNameEn()))
                .orElseThrow(() -> {
                    logger.error("Ward not found with code: {}", wardCode);
                    return new ResourceNotFoundException("Ward not found with code: " + wardCode);
                });
    }

    @Override
    public StatusParam addWard(String zoneGuid, WardAddRequest wardAddRequest) {
        logger.info("Adding new ward under zone GUID: {}", zoneGuid);
        logger.debug("WardAddRequest payload: {}", wardAddRequest);

        try {
            Optional<Zone> existingZone = zoneRepository.findById(zoneGuid.trim());
            if (existingZone.isEmpty()) {
                logger.warn("Zone not found with GUID: {}", zoneGuid);
                return new StatusParam(false, "Zone not found with id: " + zoneGuid);
            }
            Zone zone = existingZone.get();

            if (wardRepository.existsByWardCodeIgnoreCase(wardAddRequest.getWardCode().trim())) {
                logger.warn("Duplicate ward code detected: {}", wardAddRequest.getWardCode());
                return new StatusParam(false, "Ward Code already exists: " + wardAddRequest.getWardCode());
            }

            Ward ward = modelMapper.map(wardAddRequest, Ward.class);
            ward.setWardGuid(UUID.randomUUID().toString());
            ward.setCreatedDate(LocalDateTime.now());
            ward.setCreatedUri(httpServletRequest.getRequestURI());
            ward.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            ward.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            ward.setZone(zone);
            ward.setCreatedBy("SYSTEM");

            wardRepository.save(ward);
            logger.info("Ward added successfully. GUID: {}, Code: {}", ward.getWardGuid(), ward.getWardCode());
            return new StatusParam(true, "Ward added successfully with GUID: " + ward.getWardGuid());
        }catch (IllegalArgumentException ex){
            logger.error("Validation faile while adding ward: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error while adding ward: " + ex.getMessage(), ex);
        }
        catch (Exception e) {
            logger.error("Unexpected error while adding ward: {}", e.getMessage(), e);
            throw new RuntimeException("Error while adding ward: ", e);
        }
    }

    @Override
    public WardResponse getWardByGuid(String wardGuid) {
        logger.info("Fetching ward by GUID: {}", wardGuid);
        return wardRepository.findById(wardGuid.trim())
                .map(ward -> {
                    WardResponse response = modelMapper.map(ward, WardResponse.class);
                    logger.debug("Ward found: {}", response);
                    return response;
                })
                .orElseThrow(() -> {
                    logger.error("Ward not found with GUID: {}", wardGuid);
                    return new ResourceNotFoundException("Ward not found with guid: " + wardGuid);
                });
    }

    @Override
    public List<WardResponse> getAllWards() {
        logger.info("Fetching all active wards...");
        List<Ward> wards = wardRepository.findByIsActive(true);
        logger.debug("Total wards fetched: {}", wards.size());
        return wards.stream()
                .map(ward -> modelMapper.map(ward, WardResponse.class))
                .toList();
    }

    @Override
    public StatusParam updateWardByGuid(String zoneGuid, String wardGuid, WardUpdateRequest wardUpdateRequest) {
        logger.info("Updating ward. Zone GUID: {}, Ward GUID: {}", zoneGuid, wardGuid);
        logger.debug("WardUpdateRequest payload: {}", wardUpdateRequest);

        try {
            Zone zone = zoneRepository.findById(zoneGuid.trim())
                    .orElseThrow(() -> {
                        logger.error("Zone not found with GUID: {}", zoneGuid);
                        return new ResourceNotFoundException("Zone not found with GUID: " + zoneGuid);
                    });

            Ward ward = wardRepository.findById(wardGuid.trim())
                    .orElseThrow(() -> {
                        logger.error("Ward not found with GUID: {}", wardGuid);
                        return new ResourceNotFoundException("Ward not found with GUID: " + wardGuid);
                    });

            if (wardUpdateRequest.getWardCode() != null &&
                    !wardUpdateRequest.getWardCode().isBlank() &&
                    !wardUpdateRequest.getWardCode().equalsIgnoreCase(ward.getWardCode().trim()) &&
                    wardRepository.existsByWardCodeIgnoreCase(wardUpdateRequest.getWardCode())) {
                logger.warn("Duplicate ward code attempted during update: {}", wardUpdateRequest.getWardCode());
                return new StatusParam(false, "Ward code already exists: " + wardUpdateRequest.getWardCode());
            }

            modelMapper.map(wardUpdateRequest, ward);
            ward.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            ward.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
            ward.setModifiedUri(httpServletRequest.getRequestURI());
            ward.setModifiedDate(LocalDateTime.now());
            ward.setZone(zone);
            ward.setModifiedBy("SYSTEM");

            wardRepository.save(ward);
            logger.info("Ward updated successfully. GUID: {}, Code: {}", ward.getWardGuid(), ward.getWardCode());
            return new StatusParam(true, "Ward updated successfully with GUID: " + ward.getWardGuid());
        } catch (IllegalArgumentException e) {
            logger.error("Validation error while updating ward. GUID={}, Request={}", zoneGuid,wardGuid, wardUpdateRequest, e);
            throw new RuntimeException("Error while updating ward: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error while updating ward: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error while updating ward: ", e);
        }
    }

}
