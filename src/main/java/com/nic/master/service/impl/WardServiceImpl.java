package com.nic.master.service.impl;

import com.nic.master.entity.Ward;
import com.nic.master.entity.Zone;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.WardRepository;
import com.nic.master.repository.ZoneRepository;
import com.nic.master.request.wardrequest.WardAddRequest;
import com.nic.master.request.wardrequest.WardUpdateRequest;
import com.nic.master.response.wardresponse.WardAddResponse;
import com.nic.master.response.wardresponse.WardResponse;
import com.nic.master.response.wardresponse.WardUpdateResponse;
import com.nic.master.service.WardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class WardServiceImpl implements WardService {

    private static final Logger logger = LoggerFactory.getLogger(WardServiceImpl.class);

    private final WardRepository wardRepository;
    private final ZoneRepository zoneRepository;

    @Override
    public List<SelectOptionParam> fetchWardMaster() {
        logger.info("Fetching active wards for dropdown");
        return wardRepository.findByIsActive(true).stream()
                .map(w -> new SelectOptionParam(w.getWardGuid(), w.getWardCode(), w.getWardNameEn()))
                .toList();
    }

    @Override
    public SelectOptionParam fetchWardMasterByCode(String wardCode) {
        return wardRepository.findByWardCode(wardCode)
                .map(ward->new SelectOptionParam(ward.getWardGuid(),ward.getWardCode(),ward.getWardNameEn()))
                .orElseThrow(()-> new RuntimeException("Ward not found with code "+ wardCode));
    }



    //Add Ward
    @Override
    public WardAddResponse addWard(String zoneGuid, WardAddRequest wardAddRequest) {
        logger.info("Adding new ward in zone: {}", zoneGuid);

        WardAddResponse wardAddResponse = new WardAddResponse();

        Optional<Zone> existingZone = zoneRepository.findById(zoneGuid);
        if (existingZone.isEmpty()) {
            wardAddResponse.setStatus(new StatusParam(false, "Zone not found with id: " + zoneGuid));
            return wardAddResponse;
        }
        Zone zone = existingZone.get();

        if (wardRepository.existsByWardCodeIgnoreCase(wardAddRequest.getWardCode())) {
            wardAddResponse.setStatus(new StatusParam(false, "Ward Code already exists: " + wardAddRequest.getWardCode()));
            return wardAddResponse;
        }

        Ward ward = new Ward();

        ward.setWardGuid(UUID.randomUUID().toString());
        ward.setWardCode(wardAddRequest.getWardCode());
        ward.setWardNameEn(wardAddRequest.getWardNameEn());
        ward.setWardNameHi(wardAddRequest.getWardNameHi());
        ward.setWardNameRl(wardAddRequest.getWardNameRl());
        ward.setWardDescription(wardAddRequest.getWardDescription());
        ward.setOrgUnitCode(wardAddRequest.getOrgUnitCode());

        ward.setCreatedBy(wardAddRequest.getCreatedBy());
        ward.setCreatedDate(LocalDate.now());
        ward.setCreatedIpAddr(wardAddRequest.getCreatedIpAddr());
        ward.setCreatedMacAddr(wardAddRequest.getCreatedMacAddr());
        ward.setCreatedRemarks(wardAddRequest.getCreatedRemarks());
        ward.setCreatedUri(wardAddRequest.getCreatedUri());

        ward.setZone(zone);

        wardRepository.save(ward);

        wardAddResponse = toAddResponse(ward);
        wardAddResponse.setStatus(new StatusParam(true, "Record Added"));
        return wardAddResponse;
    }


    // Manual mapping methods
    private WardAddResponse toAddResponse(Ward ward) {
        WardAddResponse wardAddResponse = new WardAddResponse();
        wardAddResponse.setWardGuid(ward.getWardGuid());
        wardAddResponse.setWardCode(ward.getWardCode());
        wardAddResponse.setWardNameEn(ward.getWardNameEn());
        wardAddResponse.setWardNameHi(ward.getWardNameHi());
        wardAddResponse.setWardNameRl(ward.getWardNameRl());
        wardAddResponse.setWardDescription(ward.getWardDescription());
        wardAddResponse.setOrgUnitCode(ward.getOrgUnitCode());
        wardAddResponse.setCreatedBy(ward.getCreatedBy());
        wardAddResponse.setCreatedIpAddr(ward.getCreatedIpAddr());
        wardAddResponse.setCreatedMacAddr(ward.getCreatedMacAddr());
        wardAddResponse.setCreatedRemarks(ward.getCreatedRemarks());
        wardAddResponse.setCreatedUri(ward.getCreatedUri());
        wardAddResponse.setIsActive(true);

        if (ward.getZone() != null) {
            wardAddResponse.setZoneGuid(ward.getZone().getZoneGuid());
        }

        return wardAddResponse;
    }


    //Get Ward By Guid
    @Override
    public WardResponse getWardByGuid(String wardGuid) {
        logger.info("Fetching ward with GUID: {}", wardGuid);
        Ward ward = wardRepository.findById(wardGuid)
                .orElseThrow(() -> new RuntimeException("Ward not found with guid: " + wardGuid));
        return mapToResponse(ward);
    }

    //Get All Wards
    @Override
    public List<WardResponse> getAllWards() {
        logger.info("Fetching all wards");
        List<Ward> wards = wardRepository.findByIsActive(true);
        return wards.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private WardResponse mapToResponse(Ward ward) {
        WardResponse response = new WardResponse();
        response.setWardId(ward.getWardId());
        response.setWardGuid(ward.getWardGuid());
        response.setWardCode(ward.getWardCode());
        response.setWardNameEn(ward.getWardNameEn());
        response.setWardNameHi(ward.getWardNameHi());
        response.setWardNameRl(ward.getWardNameRl());
        response.setWardDescription(ward.getWardDescription());
        response.setOrgUnitCode(ward.getOrgUnitCode());
        response.setCreatedBy(ward.getCreatedBy());
        response.setCreatedIpAddr(ward.getCreatedIpAddr());
        response.setCreatedMacAddr(ward.getCreatedMacAddr());
        response.setCreatedRemarks(ward.getCreatedRemarks());
        response.setCreatedUri(ward.getCreatedUri());
        response.setModifiedBy(ward.getModifiedBy());
        response.setModifiedIpAddr(ward.getModifiedIpAddr());
        response.setModifiedMacAddr(ward.getModifiedMacAddr());
        response.setModifiedRemarks(ward.getModifiedRemarks());
        response.setModifiedUri(ward.getModifiedUri());
        response.setIsActive(true);

        if (ward.getZone() != null) {
            response.setZoneGuid(ward.getZone().getZoneGuid());
        }
        return response;
    }


    //Updating Ward By Guid
    @Override
    public WardUpdateResponse updateWardByGuid(String zoneGuid, String wardGuid, WardUpdateRequest wardUpdateRequest) {
        logger.info("Updating ward with GUID: {}", wardGuid);

        WardUpdateResponse wardUpdateResponse = new WardUpdateResponse();

        Zone zone = zoneRepository.findById(zoneGuid).orElse(null);
        if (zone == null) {
            wardUpdateResponse.setStatus(new StatusParam(false, "Zone not found with id: " + zoneGuid));
            return wardUpdateResponse;
        }

        Ward ward = wardRepository.findById(wardGuid).orElse(null);
        if (ward == null) {
            wardUpdateResponse.setStatus(new StatusParam(false, "Ward not found with id: " + wardGuid));
            return wardUpdateResponse;
        }
        if(wardUpdateRequest.getWardCode() != null && !wardUpdateRequest.getWardCode().equalsIgnoreCase(ward.getWardCode()) && wardRepository.existsByWardCodeIgnoreCase(wardUpdateRequest.getWardCode())){
            wardUpdateResponse.setStatus(new StatusParam(false, "Ward code already exists: " + wardUpdateRequest.getWardCode()));
                return wardUpdateResponse;
            }




        // update entity
        ward.setWardCode(wardUpdateRequest.getWardCode());
        ward.setWardNameEn(wardUpdateRequest.getWardNameEn());
        ward.setWardNameHi(wardUpdateRequest.getWardNameHi());
        ward.setWardNameRl(wardUpdateRequest.getWardNameRl());
        ward.setWardDescription(wardUpdateRequest.getWardDescription());
        ward.setOrgUnitCode(wardUpdateRequest.getOrgUnitCode());

        ward.setModifiedBy(wardUpdateRequest.getModifiedBy());
        ward.setModifiedDate(LocalDate.now());
        ward.setModifiedIpAddr(wardUpdateRequest.getModifiedIpAddr());
        ward.setModifiedMacAddr(wardUpdateRequest.getModifiedMacAddr());
        ward.setModifiedRemarks(wardUpdateRequest.getModifiedRemarks());
        ward.setModifiedUri(wardUpdateRequest.getModifiedUri());
        ward.setZone(zone);

        wardRepository.save(ward);

        // map to response
        wardUpdateResponse = toUpdateResponse(ward);
        wardUpdateResponse.setStatus(new StatusParam(true, "Record updated successfully"));
        return wardUpdateResponse;
    }



    private WardUpdateResponse toUpdateResponse(Ward ward) {
        WardUpdateResponse response = new WardUpdateResponse();

        response.setWardGuid(ward.getWardGuid());
        response.setWardCode(ward.getWardCode());
        response.setWardNameEn(ward.getWardNameEn());
        response.setWardNameHi(ward.getWardNameHi());
        response.setWardNameRl(ward.getWardNameRl());
        response.setWardDescription(ward.getWardDescription());
        response.setOrgUnitCode(ward.getOrgUnitCode());

        response.setCreatedBy(ward.getCreatedBy());
        response.setCreatedIpAddr(ward.getCreatedIpAddr());
        response.setCreatedMacAddr(ward.getCreatedMacAddr());
        response.setCreatedRemarks(ward.getCreatedRemarks());
        response.setCreatedUri(ward.getCreatedUri());

        response.setModifiedBy(ward.getModifiedBy());
        response.setModifiedIpAddr(ward.getModifiedIpAddr());
        response.setModifiedMacAddr(ward.getModifiedMacAddr());
        response.setModifiedRemarks(ward.getModifiedRemarks());
        response.setModifiedUri(ward.getModifiedUri());

        if (ward.getZone() != null) {
            response.setZoneGuid(ward.getZone().getZoneGuid());
        }
        return response;
    }

}
