package com.nic.master.service.impl;

import com.nic.master.entity.Zone;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.ZoneRepository;
import com.nic.master.request.zonerequest.ZoneAddRequest;
import com.nic.master.request.zonerequest.ZoneUpdateRequest;
import com.nic.master.service.ZoneService;
import jakarta.servlet.http.HttpServletRequest;
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

    private final HttpServletRequest httpServletRequest;


    public ZoneServiceImpl(ZoneRepository zoneRepository, HttpServletRequest httpServletRequest) {
        this.zoneRepository = zoneRepository;
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public List<SelectOptionParam> fetchZoneMaster() {
        return zoneRepository.findByIsActive(true)
                .stream()
                .map(z -> new SelectOptionParam(z.getZoneGuid(), z.getZoneCode(), z.getZoneNameEn()))
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
                return new StatusParam(false, "Zone already exists: " + zoneAddRequest.getZoneCode());
            }
            String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
            if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
                clientIp = httpServletRequest.getRemoteAddr();
            }

            Zone zone = new Zone();
            zone.setZoneGuid(UUID.randomUUID().toString());
            zone.setZoneCode(zoneAddRequest.getZoneCode());
            zone.setZoneNameEn(zoneAddRequest.getZoneNameEn());
            zone.setZoneNameHi(zoneAddRequest.getZoneNameHi());
            zone.setZoneNameRl(zoneAddRequest.getZoneNameRl());
            zone.setZoneDescription(zoneAddRequest.getZoneDescription());
            zone.setWrapperCode(zoneAddRequest.getWrapperCode());

            zone.setCreatedBy(zoneAddRequest.getCreatedBy());
            zone.setCreatedDate(LocalDate.now());
            zone.setCreatedIpAddr(clientIp);
            zone.setCreatedMacAddr(zoneAddRequest.getCreatedMacAddr());
            zone.setCreatedRemarks(zoneAddRequest.getCreatedRemarks());
            zone.setCreatedUri(zoneAddRequest.getCreatedUri());

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

            StatusParam status = new StatusParam();

            Zone zone = zoneRepository.findById(guid).orElse(null);
            if (zone == null) {
                status.setStatus(false);
                status.setMessage("Zone not found with GUID: " + guid);
                return status;
            }
            String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
            if (clientIp == null || clientIp.isEmpty() || "Unknown".equalsIgnoreCase(clientIp)) {
                clientIp = httpServletRequest.getRemoteAddr();
            }

            if (zoneUpdateRequest.getZoneCode() != null
                    && !zoneUpdateRequest.getZoneCode().equalsIgnoreCase(zone.getZoneCode())
                    && zoneRepository.existsByZoneCodeIgnoreCase(zoneUpdateRequest.getZoneCode())) {
                status.setStatus(false);
                status.setMessage("Zone code already exists: " + zoneUpdateRequest.getZoneCode());
                return status;
            }
            // Only update fields if non-null (to prevent overwriting existing values)
            if (zoneUpdateRequest.getZoneCode() != null) {
                zone.setZoneCode(zoneUpdateRequest.getZoneCode());
            }

            if (zoneUpdateRequest.getZoneNameEn() != null) {
                zone.setZoneNameEn(zoneUpdateRequest.getZoneNameEn());
            }

            if (zoneUpdateRequest.getZoneNameHi() != null) {
                zone.setZoneNameHi(zoneUpdateRequest.getZoneNameHi());
            }
            if (zoneUpdateRequest.getZoneNameRl() != null) {
                zone.setZoneNameRl(zoneUpdateRequest.getZoneNameRl());
            }
            if (zoneUpdateRequest.getZoneDescription() != null) {
                zone.setZoneDescription(zoneUpdateRequest.getZoneDescription());
            }
            if (zoneUpdateRequest.getWrapperCode() != null) {
                zone.setWrapperCode(zoneUpdateRequest.getWrapperCode());
            }
            if (zoneUpdateRequest.getModifiedBy() != null) {
                zone.setModifiedBy(zoneUpdateRequest.getModifiedBy());
            }

            //Setting Modified Ip address
            zone.setModifiedIpAddr(clientIp);

            //Setting Modified Mac address
            zone.setModifiedMacAddr(clientIp);

            if (zoneUpdateRequest.getModifiedRemarks() != null) {
                zone.setModifiedRemarks(zoneUpdateRequest.getModifiedRemarks());
            }

            if (zoneUpdateRequest.getModifiedUri() != null) {
                zone.setModifiedUri(zoneUpdateRequest.getModifiedUri());
            }

            zone.setModifiedDate(LocalDate.now());

            zoneRepository.save(zone);

            logger.info("Zone updated successfully for GUID: {}", guid);
            status.setStatus(true);
            status.setMessage("Record Updated Successfully");
            return status;
        } catch (Exception ex) {
            logger.error("Error while Updating zone: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error while Updating zone");
        }

    }
}





