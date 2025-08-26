package com.nic.master.controller.mst;

import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.mst.colonyrequest.ColonyAddRequest;
import com.nic.master.request.mst.colonyrequest.ColonyUpdateRequest;
import com.nic.master.response.colonyresponse.ColonyResponse;
import com.nic.master.service.mstservice.ColonyService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
            StatusParam statusResponse = colonyService.addColony(wardGuid, colonyAddRequest);
            return ResponseBuilder.buildOk(statusResponse, statusResponse, httpServletRequest);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @PutMapping("/update/{wardGuid}/{colonyGuid}")
    public ResponseEntity<Object> updateColony(@PathVariable String wardGuid, @PathVariable String colonyGuid, @RequestBody @Valid ColonyUpdateRequest colonyUpdateRequest, HttpServletRequest httpServletRequest) {
        try {
            StatusParam statusResponse = colonyService.updateColonyByGuid(wardGuid, colonyGuid, colonyUpdateRequest);
            return ResponseBuilder.buildOk(statusResponse, statusResponse, httpServletRequest);
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
