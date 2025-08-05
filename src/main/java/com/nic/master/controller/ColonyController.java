package com.nic.master.controller;

import com.nic.master.param.SelectOptionParam;
import com.nic.master.request.colonyrequest.ColonyAddRequest;
import com.nic.master.request.colonyrequest.ColonyUpdateRequest;
import com.nic.master.response.colonyresponse.ColonyAddResponse;
import com.nic.master.response.colonyresponse.ColonyResponse;
import com.nic.master.response.colonyresponse.ColonyUpdateResponse;
import com.nic.master.service.ColonyService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/colony")

public class ColonyController {

    private final ColonyService colonyService;

    public ColonyController(ColonyService colonyService) {
        this.colonyService = colonyService;
    }


    @GetMapping("/fetchColonyByCode")
    public ResponseEntity<Object> fetchColonyByCode(@RequestParam String colonyCode, HttpServletRequest httpServletRequest) {
        try {
            SelectOptionParam selectOptionParam = colonyService.fetchColonyMasterByCode(colonyCode);
            return ResponseEntity.ok(selectOptionParam);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }



    @PostMapping("/add")
    public ResponseEntity<Object> addColony(@RequestParam String wardGuid, @RequestBody @Valid ColonyAddRequest colonyAddRequest, HttpServletRequest httpServletRequest) {
        try {
            ColonyAddResponse colonyAddResponse = colonyService.addColony(wardGuid, colonyAddRequest);
            return ResponseBuilder.buildCreated(colonyAddResponse.getStatus(), colonyAddResponse, httpServletRequest);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @PutMapping("/update/{wardGuid}/{colonyGuid}")
    public ResponseEntity<Object> updateColony(@PathVariable String wardGuid, @PathVariable String colonyGuid, @RequestBody @Valid ColonyUpdateRequest colonyUpdateRequest, HttpServletRequest httpServletRequest) {
        try {
            ColonyUpdateResponse colonyUpdateResponse = colonyService.updateColonyByGuid(wardGuid, colonyGuid, colonyUpdateRequest);
            return ResponseBuilder.buildOk(colonyUpdateResponse.getStatus(), colonyUpdateResponse, httpServletRequest);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllColonies(HttpServletRequest httpServletRequest) {
        try {
            return ResponseEntity.ok(colonyService.getAllColonies());
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.INTERNAL_SERVER_ERROR, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @GetMapping("/colonyGuid/{colonyGuid}")
    public ResponseEntity<Object> getByGuid(@PathVariable String colonyGuid, HttpServletRequest httpServletRequest) {
        try {
            ColonyResponse colonyResponse = colonyService.getColonyByGuid(colonyGuid);
            return ResponseEntity.ok(colonyResponse);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }
}
