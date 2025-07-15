package com.nic.master.service.impl;

import com.nic.master.entity.Colony;
import com.nic.master.entity.Ward;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.ColonyRepository;
import com.nic.master.repository.WardRepository;
import com.nic.master.request.colonyrequest.ColonyAddRequest;
import com.nic.master.request.colonyrequest.ColonyUpdateRequest;
import com.nic.master.response.colonyresponse.ColonyAddResponse;
import com.nic.master.response.colonyresponse.ColonyResponse;
import com.nic.master.response.colonyresponse.ColonyUpdateResponse;
import com.nic.master.service.ColonyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ColonyServiceImpl implements ColonyService {

    private static  final Logger logger = LoggerFactory.getLogger(ColonyServiceImpl.class);

    private final ColonyRepository colonyRepository;
    private final WardRepository wardRepository;

    @Override
    public List<SelectOptionParam> fetchColonyMaster() {
        return colonyRepository.findByIsActive(true).stream()
                .map(colony -> new SelectOptionParam(colony.getColonyGuid(), colony.getColonyCode(), colony.getColonyNameEn()))
                .toList();
    }
    
	@Override
	public SelectOptionParam fetchColonyMasterByCode(String colonyCode) {
		return colonyRepository.findByColonyCode(colonyCode)
				.map(colony-> new SelectOptionParam(
				 colony.getColonyGuid(),
				 colony.getColonyCode(),
				 colony.getColonyNameEn()
				 ))
				.orElseThrow(()-> new RuntimeException("Colony not found with guid:"+colonyCode));
	}

    @Override
    @Transactional
    public ColonyAddResponse addColony(String wardGuid, ColonyAddRequest request) {
        logger.info("Adding Colony for wardGuid: {}", wardGuid);

        ColonyAddResponse colonyAddResponse = new ColonyAddResponse();

        Ward ward = wardRepository.findById(wardGuid).orElse(null);
        if (ward == null) {
            colonyAddResponse.setStatus(new StatusParam(false, "Ward not found with guid: " + wardGuid));
            return colonyAddResponse;
        }

        if (colonyRepository.existsByColonyCodeIgnoreCase(request.getColonyCode())) {
            colonyAddResponse.setStatus(new StatusParam(false, "Colony code already exists: " + request.getColonyCode()));
            return colonyAddResponse;
        }

        Colony colony = new Colony();
        colony.setColonyGuid(UUID.randomUUID().toString());
        colony.setColonyCode(request.getColonyCode());
        colony.setColonyNameHi(request.getColonyNameHi());
        colony.setColonyNameRl(request.getColonyNameRl());
        colony.setColonyNameEn(request.getColonyNameEn());
        colony.setColonyDescription(request.getColonyDescription());
        colony.setCreatedBy(request.getCreatedBy());
        colony.setCreatedDate(LocalDate.now());
        colony.setCreatedIpAddr(request.getCreatedIpAddr());
        colony.setCreatedMacAddr(request.getCreatedMacAddr());
        colony.setCreatedRemarks(request.getCreatedRemarks());
        colony.setCreatedUri(request.getCreatedUri());
        colony.setWard(ward);

        colonyRepository.save(colony);

        colonyAddResponse = toAddResponse(colony);
        colonyAddResponse.setStatus(new StatusParam(true, "Colony added successfully"));
        return colonyAddResponse;
    }

    private ColonyAddResponse toAddResponse(Colony colony) {
        ColonyAddResponse colonyAddResponse = new ColonyAddResponse();
        colonyAddResponse.setColonyGuid(colony.getColonyGuid());
        colonyAddResponse.setColonyCode(colony.getColonyCode());
        colonyAddResponse.setColonyNameEn(colony.getColonyNameEn());
        colonyAddResponse.setColonyNameHi(colony.getColonyNameHi());
        colonyAddResponse.setColonyNameRl(colony.getColonyNameRl());
        colonyAddResponse.setColonyDescription(colony.getColonyDescription());
        colonyAddResponse.setCreatedBy(colony.getCreatedBy());
        colonyAddResponse.setCreatedIpAddr(colony.getCreatedIpAddr());
        colonyAddResponse.setCreatedMacAddr(colony.getCreatedMacAddr());
        colonyAddResponse.setCreatedRemarks(colony.getCreatedRemarks());
        colonyAddResponse.setCreatedUri(colony.getCreatedUri());
        colonyAddResponse.setIsActive(true);

        if (colony.getWard() != null) {
            colonyAddResponse.setWardGuid(colony.getWard().getWardGuid());
        }

        return colonyAddResponse;
    }

    //Fetching Colony By Guid
    @Override
    public ColonyResponse getColonyByGuid(String colonyGuid) {
        logger.info("Fetching Colony By Guid:{}",colonyGuid);
        Colony colony = colonyRepository.findById(colonyGuid)
                .orElseThrow(() -> new RuntimeException("Colony not found with guid: " + colonyGuid));
        return mapToColonyResponse(colony);
    }


    //Fetching All colonies
    @Override
    public List<ColonyResponse> getAllColonies() {
        logger.info("Fetching All Colonies...");
        return colonyRepository.findAll()
                .stream()
                .map(this::mapToColonyResponse)
                .toList();
    }
    private ColonyResponse mapToColonyResponse(Colony colony){
        ColonyResponse colonyResponse = new ColonyResponse();
        colonyResponse.setColonyGuid(colony.getColonyGuid());
        colonyResponse.setColonyId(colony.getColonyId());
        colonyResponse.setColonyCode(colony.getColonyCode());
        colonyResponse.setColonyNameEn(colony.getColonyNameEn());
        colonyResponse.setColonyNameHi(colony.getColonyNameHi());
        colonyResponse.setColonyNameRl(colony.getColonyNameRl());
        colonyResponse.setColonyDescription(colony.getColonyDescription());
        colonyResponse.setCreatedBy(colony.getCreatedBy());
        colonyResponse.setCreatedIpAddr(colony.getCreatedIpAddr());
        colonyResponse.setCreatedMacAddr(colony.getCreatedMacAddr());
        colonyResponse.setCreatedRemarks(colony.getCreatedRemarks());
        colonyResponse.setModifiedBy(colonyResponse.getModifiedBy());
        colonyResponse.setModifiedIpAddr(colonyResponse.getModifiedIpAddr());
        colonyResponse.setModifiedMacAddr(colonyResponse.getModifiedMacAddr());
        colonyResponse.setModifiedRemarks(colonyResponse.getModifiedRemarks());
        colonyResponse.setModifiedUri(colony.getModifiedUri());
        colony.setIsActive(true);

        if(colony.getWard() != null){
            colonyResponse.setWardGuid(colony.getWard().getWardGuid());
        }
        return colonyResponse;
    }


    //Update Colony
    @Override
    public ColonyUpdateResponse updateColonyByGuid(String wardGuid, String colonyGuid, ColonyUpdateRequest colonyUpdateRequest) {
        logger.info("Updating colony with GUID: {}", colonyGuid);

        ColonyUpdateResponse colonyUpdatedResponse = new ColonyUpdateResponse();

        Ward ward = wardRepository.findByWardGuid(wardGuid).orElse(null);
        if (ward == null) {
            colonyUpdatedResponse.setStatus(new StatusParam(false, "Ward not found with guid: " + wardGuid));
            return colonyUpdatedResponse;
        }

        Colony colony = colonyRepository.findById(colonyGuid).orElse(null);
        if (colony == null) {
            colonyUpdatedResponse.setStatus(new StatusParam(false, "Colony not found with guid: " + colonyGuid));
            return colonyUpdatedResponse;
        }

        if (colonyUpdateRequest.getColonyCode() != null && !colonyUpdateRequest.getColonyCode().equals(colony.getColonyCode()) && colonyRepository.existsByColonyCodeIgnoreCase(colonyUpdateRequest.getColonyCode())) {
            colonyUpdatedResponse.setStatus(new StatusParam(false, "Colony code already exists: " + colonyUpdateRequest.getColonyCode()));
            return colonyUpdatedResponse;
        }

        colony.setColonyCode(colonyUpdateRequest.getColonyCode());
        colony.setColonyNameHi(colonyUpdateRequest.getColonyNameHi());
        colony.setColonyNameRl(colonyUpdateRequest.getColonyNameRl());
        colony.setColonyNameEn(colonyUpdateRequest.getColonyNameEn());
        colony.setColonyDescription(colonyUpdateRequest.getColonyDescription());

        colony.setModifiedBy(colonyUpdateRequest.getModifiedBy());
        colony.setModifiedDate(LocalDate.now());
        colony.setModifiedIpAddr(colonyUpdateRequest.getModifiedIpAddr());
        colony.setModifiedMacAddr(colonyUpdateRequest.getModifiedMacAddr());
        colony.setModifiedRemarks(colonyUpdateRequest.getModifiedRemarks());
        colony.setModifiedUri(colonyUpdateRequest.getModifiedUri());

        colony.setWard(ward);

        colonyRepository.save(colony);

        colonyUpdatedResponse = toUpdateResponse(colony);
        colonyUpdatedResponse.setStatus(new StatusParam(true, "Record updated successfully"));
        return colonyUpdatedResponse;
    }


    private ColonyUpdateResponse toUpdateResponse(Colony colony) {

        ColonyUpdateResponse colonyUpdatedResponse = new ColonyUpdateResponse();

        colonyUpdatedResponse.setColonyGuid(colony.getColonyGuid());
        colonyUpdatedResponse.setColonyId(colony.getColonyId());
        colonyUpdatedResponse.setColonyCode(colony.getColonyCode());
        colonyUpdatedResponse.setColonyNameEn(colony.getColonyNameEn());
        colonyUpdatedResponse.setColonyNameHi(colony.getColonyNameHi());
        colonyUpdatedResponse.setColonyNameRl(colony.getColonyNameRl());
        colonyUpdatedResponse.setColonyDescription(colony.getColonyDescription());

        colonyUpdatedResponse.setCreatedBy(colony.getCreatedBy());
        colonyUpdatedResponse.setCreatedIpAddr(colony.getCreatedIpAddr());
        colonyUpdatedResponse.setCreatedMacAddr(colony.getCreatedMacAddr());
        colonyUpdatedResponse.setCreatedRemarks(colony.getCreatedRemarks());
        colonyUpdatedResponse.setCreatedUri(colony.getCreatedUri());

        colonyUpdatedResponse.setModifiedBy(colony.getModifiedBy());
        colonyUpdatedResponse.setModifiedIpAddr(colony.getModifiedIpAddr());
        colonyUpdatedResponse.setModifiedMacAddr(colony.getModifiedMacAddr());
        colonyUpdatedResponse.setModifiedRemarks(colony.getModifiedRemarks());
        colonyUpdatedResponse.setModifiedUri(colony.getModifiedUri());

        if (colony.getWard() != null) {
            colonyUpdatedResponse.setWardGuid(colony.getWard().getWardGuid());
        }

        return colonyUpdatedResponse;
    }


}
