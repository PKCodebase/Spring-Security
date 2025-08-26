package com.nic.master.controller.mst;

import com.nic.master.entity.mst.Zone;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.mst.zonerequest.ZoneAddRequest;
import com.nic.master.request.mst.zonerequest.ZoneUpdateRequest;
import com.nic.master.service.mstservice.ZoneService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zone")

public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }


    @GetMapping("/fetchZoneByCode")
    public ResponseEntity<Object> fetchZoneByCode(@RequestParam String zoneCode, HttpServletRequest httpServletRequest) {
        try {
            SelectOptionParam selectOptionParam = zoneService.fetchZoneMasterByCode(zoneCode);
            return ResponseEntity.ok(selectOptionParam);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }



    @PostMapping("/add")
    public ResponseEntity<Object> addZone(@Valid @RequestBody ZoneAddRequest zoneAddRequest, HttpServletRequest httpServletRequest) {
        try {
            StatusParam response = zoneService.addZone(zoneAddRequest);
            return ResponseBuilder.buildOk(response, response, httpServletRequest);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }



    @PutMapping("/update/{zoneGuid}")
    public ResponseEntity<Object> updateZone(@PathVariable String zoneGuid, @RequestBody @Valid ZoneUpdateRequest zoneUpdateRequest, HttpServletRequest httpServletRequest) {
        try {
            StatusParam response = zoneService.updateZoneByGuid(zoneGuid, zoneUpdateRequest);
            return ResponseBuilder.buildOk(response, response, httpServletRequest);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllZones(HttpServletRequest httpServletRequest) {
        try {
            return ResponseEntity.ok(zoneService.getAllZones());
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.INTERNAL_SERVER_ERROR, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @GetMapping("/getBy/{zoneGuid}")
    public ResponseEntity<Object> getByGuid(@PathVariable String zoneGuid, HttpServletRequest httpServletRequest) {
        try {
            Zone zone = zoneService.getZoneByGuid(zoneGuid);
            return ResponseEntity.ok(zone);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }
    //parameter 
    //operation=add/edit/view  i want to pass the parameter is add view get with the single method
//    public ResponseEntity<Object> methodName() {
//
//
//
//    	if(operation.equalsignore)
//
//
//    }
}
