package com.nic.master.service.mstservice.impl;

import com.nic.master.entity.mst.Ward;
import com.nic.master.entity.mst.Zone;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.mst.WardRepository;
import com.nic.master.repository.mst.ZoneRepository;
import com.nic.master.request.mst.wardrequest.WardAddRequest;
import com.nic.master.request.mst.wardrequest.WardUpdateRequest;
import com.nic.master.response.wardresponse.WardResponse;
import com.nic.master.service.mstservice.WardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class WardServiceImpl implements WardService {

    private static final Logger logger = LoggerFactory.getLogger(WardServiceImpl.class);

    private final WardRepository wardRepository;
    private final ZoneRepository zoneRepository;
    private  final HttpServletRequest httpServletRequest;
    private final ModelMapper modelMapper;

    public WardServiceImpl(WardRepository wardRepository, ZoneRepository zoneRepository,ModelMapper modelMapper, HttpServletRequest httpServletRequest) {
        this.wardRepository = wardRepository;
        this.zoneRepository = zoneRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public List<SelectOptionParam> fetchWardMaster() {
        logger.info("Fetching active wards for dropdown");
        return wardRepository.findByIsActive(true)
                .stream()
                .map(ward -> modelMapper.map(ward,SelectOptionParam.class))
                .toList();
    }

    @Override
    public SelectOptionParam fetchWardMasterByCode(String wardCode) {
        logger.info("Fetching ward by code: {}", wardCode);
        return wardRepository.findByWardCode(wardCode)
                .map(ward->new SelectOptionParam(
                        ward.getWardGuid(),
                        ward.getWardCode(),
                        ward.getWardNameEn()))
                .orElseThrow(()-> new RuntimeException("Ward not found with code : "+ wardCode));
    }

    @Override
    public StatusParam addWard(String zoneGuid, WardAddRequest wardAddRequest) {
        logger.info("Adding new ward in zone: {}", zoneGuid);

        try {
            // Check if zone exists
            Optional<Zone> existingZone = zoneRepository.findById(zoneGuid);
            if (existingZone.isEmpty()) {
               return new StatusParam(false, "Zone not found with id: " + zoneGuid);
            }
            Zone zone = existingZone.get();

            // Check if ward code already exists
            if (wardRepository.existsByWardCodeIgnoreCase(wardAddRequest.getWardCode())) {
               return new StatusParam(false, "Ward Code already exists: " + wardAddRequest.getWardCode());
            }

            // Map request to entity
            logger.info("Mapping WardAddRequest to Ward entity");
            Ward ward = modelMapper.map(wardAddRequest,Ward.class);
            ward.setWardGuid(UUID.randomUUID().toString());
            ward.setCreatedDate(LocalDate.now());
            ward.setCreatedIpAddr(getClientIp());
            ward.setZone(zone);
            ward.setCreatedBy("SYSTEM");

            //Save the ward
            wardRepository.save(ward);
            logger.info("Ward added successfully with GUID: {}", ward.getWardGuid());
            return  new StatusParam(true, "Ward added successfully with GUID: " + ward.getWardGuid());
        } catch (Exception e) {
            logger.error("Error while adding ward: {}", e.getMessage(), e);
            throw new RuntimeException("Error while adding ward: ");
        }

    }

    //Get Ward By Guid
    @Override
    public WardResponse getWardByGuid(String wardGuid) {
        logger.info("Fetching ward with GUID: {}", wardGuid);
        return wardRepository.findById(wardGuid)
                .map(ward -> modelMapper.map(ward, WardResponse.class))
                .orElseThrow(() -> new RuntimeException("Ward not found with guid: " + wardGuid));
    }


    //Get All Wards
    @Override
    public List<WardResponse> getAllWards() {
        logger.info("Fetching all wards");
        List<Ward> wards = wardRepository.findByIsActive(true);
        return wards.stream()
                .map(ward->modelMapper.map(ward,WardResponse.class))
                .toList();
    }

//    private WardResponse mapToResponse(Ward ward) {
//        WardResponse response = new WardResponse();
//        response.setWardId(ward.getWardId());
//        response.setWardGuid(ward.getWardGuid());
//        response.setWardCode(ward.getWardCode());
//        response.setWardNameEn(ward.getWardNameEn());
//        response.setWardNameHi(ward.getWardNameHi());
//        response.setWardNameRl(ward.getWardNameRl());
//        response.setWardDescription(ward.getWardDescription());
//        response.setOrgUnitCode(ward.getOrgUnitCode());
//        response.setCreatedBy(ward.getCreatedBy());
//        response.setCreatedIpAddr(ward.getCreatedIpAddr());
//        response.setCreatedMacAddr(ward.getCreatedMacAddr());
//        response.setCreatedRemarks(ward.getCreatedRemarks());
//        response.setCreatedUri(ward.getCreatedUri());
//        response.setModifiedBy(ward.getModifiedBy());
//        response.setModifiedIpAddr(ward.getModifiedIpAddr());
//        response.setModifiedMacAddr(ward.getModifiedMacAddr());
//        response.setModifiedRemarks(ward.getModifiedRemarks());
//        response.setModifiedUri(ward.getModifiedUri());
//        response.setIsActive(true);
//
//        if (ward.getZone() != null) {
//            response.setZoneGuid(ward.getZone().getZoneGuid());
//        }
//        return response;
//    }
    //Updating Ward By Guid
    @Override
    public StatusParam updateWardByGuid(String zoneGuid, String wardGuid, WardUpdateRequest wardUpdateRequest) {
        logger.info("Updating ward with GUID: {}", wardGuid);

        try {
            //Check if zone exists
            Zone zone = zoneRepository.findById(zoneGuid).orElse(null);
            if (zone == null) {
               return  new StatusParam(false, "Zone not found with GUID: " + zoneGuid);
            }

            //Check if ward exists
            Ward ward = wardRepository.findById(wardGuid).orElse(null);
            if (ward == null) {
               return new StatusParam(false, "Ward not found with GUID: " + wardGuid);
            }

            //Check if ward code already exists
            if (wardUpdateRequest.getWardCode() != null &&
                    !wardUpdateRequest.getWardCode().isBlank() &&
                    !wardUpdateRequest.getWardCode().equalsIgnoreCase(ward.getWardCode()) &&
                    wardRepository.existsByWardCodeIgnoreCase(wardUpdateRequest.getWardCode())) {
                return new StatusParam(false, "Ward code already exists: " + wardUpdateRequest.getWardCode());
            }

            // Map request to entity
            modelMapper.map(wardUpdateRequest,ward);
            ward.setModifiedIpAddr(getClientIp());
            ward.setModifiedMacAddr(getClientIp());
            ward.setModifiedDate(LocalDate.now());
            ward.setZone(zone);
            ward.setModifiedBy("SYSTEM");

            // Save updated ward
            wardRepository.save(ward);
            logger.info("Ward updated successfully with GUID: {}", ward.getWardGuid());
            return new StatusParam(true, "Ward updated successfully with GUID: " + ward.getWardGuid());
        } catch (Exception e) {
            logger.error("Error while updating ward: {}", e.getMessage(), e);
            throw new RuntimeException("Error while updating ward: ");
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
