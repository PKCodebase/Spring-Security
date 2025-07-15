package com.nic.master.controller;

import com.nic.master.entity.Zone;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.request.zonerequest.ZoneAddRequest;
import com.nic.master.request.zonerequest.ZoneUpdateRequest;
import com.nic.master.response.zoneresponse.ZoneAddResponse;
import com.nic.master.response.zoneresponse.ZoneUpdateResponse;
import com.nic.master.service.ZoneService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zone")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;



    //Fetch Zone By Code
    @GetMapping("/fetchZoneByCode")
    public ResponseEntity<Object> fetchZoneByCode(@RequestParam String zoneCode,HttpServletRequest httpServletRequest){
        try{
            SelectOptionParam selectOptionParam = zoneService.fetchZoneMasterByCode(zoneCode);
            return ResponseEntity.ok(selectOptionParam);
        }catch (Exception ex){
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    //Add Zone
    @PostMapping("/add")
    public ResponseEntity<Object> addZone(@Valid  @RequestBody ZoneAddRequest zoneAddRequest, HttpServletRequest httpServletRequest) {
        ZoneAddResponse response = zoneService.addZone(zoneAddRequest);
        return ResponseBuilder.buildCreated(response.getStatus(), response,httpServletRequest);
    }

    //Update Zone by Zone Guid
    @PutMapping("/update/{zoneGuid}")
    public ResponseEntity<Object> updateZone(@PathVariable String zoneGuid, @RequestBody @Valid ZoneUpdateRequest zoneUpdateRequest,HttpServletRequest httpServletRequest) {
        ZoneUpdateResponse response = zoneService.updateZoneByGuid(zoneGuid, zoneUpdateRequest);
        return ResponseBuilder.buildOk(response.getStatus(), response,httpServletRequest);
    }

    //Fetch All Zones
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllZones() {
        return ResponseEntity.ok(zoneService.getAllZones());
    }

    //Get Zone by ZoneGuid
    @GetMapping("/getBy/{zoneGuid}")
    public ResponseEntity<Object> getByGuid(@PathVariable String zoneGuid,HttpServletRequest httpServletRequest) {
        try{
            Zone zone = zoneService.getZoneByGuid(zoneGuid);
            return  ResponseEntity.ok(zone);
        }
        catch(Exception ex){
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(),ex.getMessage());
        }
    }
}
