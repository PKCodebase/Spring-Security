package com.nic.master.service.mstservice.impl;

import com.nic.master.entity.mst.Zone;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.mst.ZoneRepository;
import com.nic.master.request.mst.zonerequest.ZoneAddRequest;
import com.nic.master.request.mst.zonerequest.ZoneUpdateRequest;
import com.nic.master.service.mstservice.ZoneService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ZoneServiceImpl implements ZoneService {

    private static final Logger logger = LoggerFactory.getLogger(ZoneServiceImpl.class);
    private final ZoneRepository zoneRepository;
    private final ModelMapper modelMapper;

    private final HttpServletRequest httpServletRequest;


    public ZoneServiceImpl(ZoneRepository zoneRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest) {
        this.zoneRepository = zoneRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public List<SelectOptionParam> fetchZoneMaster(){
        return zoneRepository.findByIsActive(true)
                .stream()
                .map(zone -> modelMapper.map(zone, SelectOptionParam.class))
                .toList();
    }

    @Override
    public SelectOptionParam fetchZoneMasterByCode(String zoneCode) {
        return zoneRepository.findByZoneCode(zoneCode)
                .map(zone -> new SelectOptionParam(
                        zone.getZoneGuid(),
                        zone.getZoneCode(),
                        zone.getZoneNameEn()
                ))
                .orElseThrow(() -> new RuntimeException("Zone Not found with:" + zoneCode));
    }
    @Override
    public StatusParam addZone(ZoneAddRequest zoneAddRequest) {
        logger.info("Adding New Zone: {}", zoneAddRequest);
        try {
            if (zoneRepository.existsByZoneCodeIgnoreCase(zoneAddRequest.getZoneCode())) {
                logger.warn("Duplicate zone Code: {}", zoneAddRequest.getZoneCode());
                return new StatusParam(false, "Zone already exists with code : " + zoneAddRequest.getZoneCode());
            }
            Zone zone = modelMapper.map(zoneAddRequest, Zone.class);
            zone.setZoneGuid(UUID.randomUUID().toString());
            zone.setCreatedDate(LocalDate.now());
            zone.setCreatedIpAddr(getClientIp());
            zone.setIsActive(true);
            zone.setCreatedBy("SYSTEM");

            zoneRepository.save(zone);
            logger.info("Added Zone: {}", zoneAddRequest);
            return new StatusParam(true, "Saved Successfully");
        } catch (Exception ex) {
            logger.error("Error while adding zone: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error while adding document");
        }
    }

    //Get Zone By Guid
    @Override
    public Zone getZoneByGuid(String zoneGuid) {
        logger.info("Fetching ward By Guid:{}", zoneGuid);
        return zoneRepository.findById(zoneGuid)
                .orElseThrow(() -> new RuntimeException("Zone not found with guid: " + zoneGuid));
    }

    @Override
    public List<Zone> getAllZones() {
        logger.info("Fetching All Zones....");
        return zoneRepository.findAll();
    }

    @Override
    public StatusParam updateZoneByGuid(String guid, ZoneUpdateRequest zoneUpdateRequest) {
        logger.info("Updating Zone with GUID: {}", guid);
        try {
            // Fetch the existing zone by GUID
            Zone zone = zoneRepository.findById(guid)
                    .orElseThrow(() -> new RuntimeException("Zone not found with GUID: " + guid));

            // Check if the zone code is being updated and if it already exists
            if (zoneUpdateRequest.getZoneCode() != null
                    && !zoneUpdateRequest.getZoneCode().equalsIgnoreCase(zone.getZoneCode())
                    && zoneRepository.existsByZoneCodeIgnoreCase(zoneUpdateRequest.getZoneCode())) {
                return new StatusParam(false, "Zone code already exists: " + zoneUpdateRequest.getZoneCode());
            }

            // Map only non-null fields from request to entity
            modelMapper.map(zoneUpdateRequest, zone);
            zone.setModifiedDate(LocalDate.now());
            zone.setModifiedIpAddr(getClientIp());
            zone.setModifiedMacAddr(getClientIp());
            zone.setModifiedBy("SYSTEM");

            zoneRepository.save(zone);
            logger.info("Zone updated successfully for GUID: {}", guid);
            return new StatusParam(true, "Zone Updated Successfully");
        } catch (Exception ex) {
            logger.error("Error while Updating zone: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error while Updating zone");
        }
    }

    // Helper method to get client IP address
    private String getClientIp() {
        String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getRemoteAddr();
        }
        return clientIp;
    }

}





