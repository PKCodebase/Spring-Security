package com.nic.master.controller;

import com.nic.master.param.SelectOptionParam;
import com.nic.master.request.wardrequest.WardAddRequest;
import com.nic.master.request.wardrequest.WardUpdateRequest;
import com.nic.master.response.wardresponse.WardAddResponse;
import com.nic.master.response.wardresponse.WardResponse;
import com.nic.master.response.wardresponse.WardUpdateResponse;
import com.nic.master.service.WardService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ward")
public class WardController {

    private final WardService wardService;

    public WardController(WardService wardService) {
        this.wardService = wardService;
    }


    @GetMapping("/fetchWardByCode")
    public ResponseEntity<Object> fetchWardByCode(@RequestParam String wardCode, HttpServletRequest httpServletRequest) {
        try {
            SelectOptionParam selectOptionParam = wardService.fetchWardMasterByCode(wardCode);
            return ResponseEntity.ok(selectOptionParam);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @PostMapping("/addWard")
    public ResponseEntity<Object> addWard(@RequestParam String zoneGuid, @Valid @RequestBody WardAddRequest request, HttpServletRequest httpServletRequest) {
        try {
            WardAddResponse wardAddResponse = wardService.addWard(zoneGuid, request);
            return ResponseBuilder.buildCreated(wardAddResponse.getStatus(), wardAddResponse, httpServletRequest);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @PutMapping("/update/{zoneGuid}/{wardGuid}")
    public ResponseEntity<Object> updateWard(@PathVariable String zoneGuid, @PathVariable String wardGuid, @RequestBody @Valid WardUpdateRequest wardUpdateRequest, HttpServletRequest httpServletRequest) {
        try {
            WardUpdateResponse wardUpdateResponse = wardService.updateWardByGuid(zoneGuid, wardGuid, wardUpdateRequest);
            return ResponseBuilder.buildOk(wardUpdateResponse.getStatus(), wardUpdateResponse, httpServletRequest);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllWards(HttpServletRequest httpServletRequest) {
        try {
            return ResponseEntity.ok(wardService.getAllWards());
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.INTERNAL_SERVER_ERROR, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @GetMapping("/getBy/{wardGuid}")
    public ResponseEntity<Object> getByGuid(@PathVariable String wardGuid, HttpServletRequest httpServletRequest) {
        try {
            WardResponse wardResponse = wardService.getWardByGuid(wardGuid);
            return ResponseEntity.ok(wardResponse);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }
}