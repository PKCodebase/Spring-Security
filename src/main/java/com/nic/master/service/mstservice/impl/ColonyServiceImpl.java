package com.nic.master.service.mstservice.impl;

import com.nic.master.entity.mst.Colony;
import com.nic.master.entity.mst.Ward;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.mst.ColonyRepository;
import com.nic.master.repository.mst.WardRepository;
import com.nic.master.request.mst.colonyrequest.ColonyAddRequest;
import com.nic.master.request.mst.colonyrequest.ColonyUpdateRequest;
import com.nic.master.response.colonyresponse.ColonyResponse;
import com.nic.master.service.mstservice.ColonyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
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

    public ColonyServiceImpl(ColonyRepository colonyRepository, WardRepository wardRepository, HttpServletRequest httpServletRequest, ModelMapper modelMapper) {
        this.colonyRepository = colonyRepository;
        this.wardRepository = wardRepository;
        this.httpServletRequest = httpServletRequest;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SelectOptionParam> fetchColonyMaster() {
        return colonyRepository.findByIsActive(true)
                .stream()
                .map(colony -> modelMapper.map(colony,SelectOptionParam.class))
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
    public StatusParam addColony(String wardGuid, ColonyAddRequest colonyAddRequest) {
        logger.info("Adding Colony for wardGuid: {}", wardGuid);

        try {
            // Initialize status response
            StatusParam  status = new StatusParam();

            //Check if ward exists
            Ward ward = wardRepository.findById(wardGuid).orElse(null);
            if (ward == null) {
                return new StatusParam(false, "Ward not found with GUID: " + wardGuid);
            }

            // Check if colony code already exists
            if (colonyRepository.existsByColonyCodeIgnoreCase(colonyAddRequest.getColonyCode())) {
                return new StatusParam(false, "Colony code already exists: " + colonyAddRequest.getColonyCode());
            }

            // Map request to entity
            Colony colony = modelMapper.map(colonyAddRequest, Colony.class);

            colony.setColonyGuid(UUID.randomUUID().toString());
            colony.setCreatedDate(LocalDate.now());
            colony.setCreatedIpAddr(getClientIp());
            colony.setWard(ward);
            colony.setCreatedBy("SYSTEM");

            //Save colony to database
            colonyRepository.save(colony);
            logger.info("Colony added successfully with GUID: {}", colony.getColonyGuid());
           return new StatusParam(true, "Colony added successfully with GUID: " + colony.getColonyGuid());
        } catch (Exception e) {
            logger.error("Error while adding colony: {}", e.getMessage(), e);
            throw new RuntimeException("Error while adding colony : ");
        }

    }

    //Fetching Colony By Guid
    @Override
    public ColonyResponse getColonyByGuid(String colonyGuid) {
        logger.info("Fetching Colony By Guid:{}",colonyGuid);
       return colonyRepository.findById(colonyGuid)
               .map(colony -> modelMapper.map(colony, ColonyResponse.class))
                .orElseThrow(() -> new RuntimeException("Colony not found with GUID: " + colonyGuid));

    }

    //Fetching All colonies
    @Override
    public List<ColonyResponse> getAllColonies() {
        logger.info("Fetching All Colonies...");
        return colonyRepository.findAll()
                .stream()
                .map(colony ->  modelMapper.map(colony, ColonyResponse.class))
                .toList();
    }

    //Update Colony
    @Override
    public StatusParam updateColonyByGuid(String wardGuid, String colonyGuid, ColonyUpdateRequest colonyUpdateRequest) {
        logger.info("Updating colony with GUID: {}", colonyGuid);


        try {

            //Check if ward exists
            Ward ward = wardRepository.findByWardGuid(wardGuid).orElse(null);
            if (ward == null) {
                return new StatusParam(false, "Ward not found with GUID: " + wardGuid);
            }

            //Check if colony exists
            Colony colony = colonyRepository.findById(colonyGuid).orElse(null);
            if (colony == null) {
               return new StatusParam(false, "Colony not found with GUID: " + colonyGuid);
            }

            // Check for duplicate colony code if it's being changed
            if (colonyUpdateRequest.getColonyCode() != null &&
                    !colonyUpdateRequest.getColonyCode().equalsIgnoreCase(colony.getColonyCode()) &&
                    colonyRepository.existsByColonyCodeIgnoreCase(colonyUpdateRequest.getColonyCode())) {
                return new StatusParam(false, "Colony code already exists: " + colonyUpdateRequest.getColonyCode());
            }

            modelMapper.map( colonyUpdateRequest,colony); // Map request to existing entity
            colony.setModifiedIpAddr(getClientIp());

            colony.setModifiedDate(LocalDate.now()); // Always update modified date
            colony.setWard(ward); // Always reassign ward in case of association change
            colony.setModifiedBy("SYSTEM");

            // Save the updated colony
            colonyRepository.save(colony);
            return new StatusParam(true, "Colony updated successfully with GUID: " + colony.getColonyGuid());
        } catch (Exception e) {
            logger.error("Error while updating colony: {}", e.getMessage(), e);
            throw new RuntimeException("Error while updating colony : " );
        }

    }

    private String getClientIp(){
        String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getRemoteAddr();
        }
        return clientIp;
    }

}
