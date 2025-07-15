package com.nic.master.service.impl;

import com.nic.master.entity.Zone;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.ZoneRepository;
import com.nic.master.request.zonerequest.ZoneAddRequest;
import com.nic.master.request.zonerequest.ZoneUpdateRequest;
import com.nic.master.response.zoneresponse.ZoneAddResponse;
import com.nic.master.response.zoneresponse.ZoneUpdateResponse;
import com.nic.master.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    private static  final Logger logger = LoggerFactory.getLogger(ZoneServiceImpl.class);


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
                .map(zone-> new SelectOptionParam(
                        zone.getZoneGuid(),
                        zone.getZoneCode(),
                        zone.getZoneNameEn()
                ))
                .orElseThrow(()->new RuntimeException("Zone Not found with:"+zoneCode));
    }


    //Add Zone
    @Override
    public ZoneAddResponse addZone(ZoneAddRequest zoneAddRequest) {


        logger.info("Adding New  Zone: {}",zoneAddRequest);

        try {

            if (zoneRepository.existsByZoneCodeIgnoreCase(zoneAddRequest.getZoneCode())) {
                logger.warn("Duplicate document  Code : {}", zoneAddRequest.getZoneCode());
                ZoneAddResponse response = new ZoneAddResponse();
                response.setStatus(new StatusParam(false, "Zone already exists: " + zoneAddRequest.getZoneCode()));
                return response;
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
            zone.setCreatedIpAddr(zoneAddRequest.getCreatedIpAddr());
            zone.setCreatedMacAddr(zoneAddRequest.getCreatedMacAddr());
            zone.setCreatedRemarks(zoneAddRequest.getCreatedRemarks());
            zone.setCreatedUri(zoneAddRequest.getCreatedUri());
            zoneRepository.save(zone);
            logger.info("Added Zone: {}", zoneAddRequest);

            ZoneAddResponse response = toAddResponse(zone);
            response.setStatus(new StatusParam(true, "Record Added"));
            return response;
        }catch(Exception ex){
            logger.error("Error while adding zone: {}", ex.getMessage(),ex);
            throw  new RuntimeException("Error while adding document");
        }
    }

    private ZoneAddResponse toAddResponse(Zone zone) {

        ZoneAddResponse zoneAddResponse = new ZoneAddResponse();

        zoneAddResponse.setZoneGuid(zone.getZoneGuid());
        zoneAddResponse.setZoneCode(zone.getZoneCode());
        zoneAddResponse.setZoneNameEn(zone.getZoneNameEn());
        zoneAddResponse.setZoneNameHi(zone.getZoneNameHi());
        zoneAddResponse.setZoneNameRl(zone.getZoneNameRl());
        zoneAddResponse.setZoneDescription(zone.getZoneDescription());
        zoneAddResponse.setWrapperCode(zone.getWrapperCode());

        zoneAddResponse.setCreatedBy(zone.getCreatedBy());
        zoneAddResponse.setCreatedIpAddr(zone.getCreatedIpAddr());
        zoneAddResponse.setCreatedMacAddr(zone.getCreatedMacAddr());
        zoneAddResponse.setCreatedRemarks(zone.getCreatedRemarks());
        zoneAddResponse.setCreatedUri(zone.getCreatedUri());
        zoneAddResponse.setIsActive(true);
        return zoneAddResponse;
    }

    //Get Zone By Guid
    @Override
    public Zone getZoneByGuid(String zoneGuid) {
        logger.info("Fetching ward By Guid:{}",zoneGuid);
        return zoneRepository.findById(zoneGuid)
                .orElseThrow(() -> new RuntimeException("Zone not found with guid: " + zoneGuid));
    }

    @Override
    public List<Zone> getAllZones() {
        logger.info("Fetching All Zones....");
        return zoneRepository.findAll();
    }

    //Update Zone By Guid
    @Override
    public ZoneUpdateResponse updateZoneByGuid(String guid, ZoneUpdateRequest zoneUpdateRequest) {
        logger.info("Updating Zone with  guid:{}",guid);
    	ZoneUpdateResponse zoneUpdateResponse = new ZoneUpdateResponse();

        Zone zone = zoneRepository.findById(guid).orElse(null);
        if(zone == null){
            zoneUpdateResponse.setStatus(new StatusParam(false, "Zone not found with guid: " + guid));
            return  zoneUpdateResponse;
        }

        if(zoneUpdateRequest.getZoneCode() != null && !zoneUpdateRequest.getZoneCode().equalsIgnoreCase(zone.getZoneCode()) && zoneRepository.existsByZoneCodeIgnoreCase(zoneUpdateRequest.getZoneCode())){
            zoneUpdateResponse.setStatus(new StatusParam(false, "Zone already exists: " + zoneUpdateRequest.getZoneCode()));
            return zoneUpdateResponse;
        }

        zone.setZoneCode(zoneUpdateRequest.getZoneCode());	
        zone.setZoneNameEn(zoneUpdateRequest.getZoneNameEn());
        zone.setZoneNameHi(zoneUpdateRequest.getZoneNameHi());
        zone.setZoneNameRl(zoneUpdateRequest.getZoneNameRl());
        zone.setZoneDescription(zoneUpdateRequest.getZoneDescription());
        zone.setWrapperCode(zoneUpdateRequest.getWrapperCode());



        zone.setModifiedBy(zoneUpdateRequest.getModifiedBy());
        zone.setModifiedDate(LocalDate.now());
        zone.setModifiedIpAddr(zoneUpdateRequest.getModifiedIpAddr());
        zone.setModifiedMacAddr(zoneUpdateRequest.getModifiedMacAddr());
        zone.setModifiedRemarks(zoneUpdateRequest.getModifiedRemarks());
        zone.setModifiedUri(zoneUpdateRequest.getModifiedUri());


        zoneRepository.save(zone);
        return toUpdateResponse(zone);
    }

    private ZoneUpdateResponse toUpdateResponse(Zone zone) {
        ZoneUpdateResponse zoneUpdateResponse = new ZoneUpdateResponse();
        zoneUpdateResponse.setZoneId(zone.getZoneId());
        zoneUpdateResponse.setZoneGuid(zone.getZoneGuid());
        zoneUpdateResponse.setZoneCode(zone.getZoneCode());
        zoneUpdateResponse.setZoneNameEn(zone.getZoneNameEn());
        zoneUpdateResponse.setZoneNameHi(zone.getZoneNameHi());
        zoneUpdateResponse.setZoneNameRl(zone.getZoneNameRl());
        zoneUpdateResponse.setZoneDescription(zone.getZoneDescription());
        zoneUpdateResponse.setWrapperCode(zone.getWrapperCode());

        zoneUpdateResponse.setCreatedBy(zone.getCreatedBy());
        zoneUpdateResponse.setCreatedIpAddr(zone.getCreatedIpAddr());
        zoneUpdateResponse.setCreatedMacAddr(zone.getCreatedMacAddr());
        zoneUpdateResponse.setCreatedRemarks(zone.getCreatedRemarks());
        zoneUpdateResponse.setCreatedUri(zone.getCreatedUri());

        zoneUpdateResponse.setModifiedBy(zone.getModifiedBy());
        zoneUpdateResponse.setModifiedIpAddr(zone.getModifiedIpAddr());
        zoneUpdateResponse.setModifiedMacAddr(zone.getModifiedMacAddr());
        zoneUpdateResponse.setModifiedRemarks(zone.getModifiedRemarks());
        zoneUpdateResponse.setModifiedUri(zone.getModifiedUri());

        zoneUpdateResponse.setStatus(new StatusParam(true, "Record Updated"));
        return zoneUpdateResponse;
    }

}





