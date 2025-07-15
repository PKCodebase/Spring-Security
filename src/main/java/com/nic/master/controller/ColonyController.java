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
@RequiredArgsConstructor
public class ColonyController {

    private final ColonyService colonyService;


    //Fetch Colony By Colony Code
    @GetMapping("/fetchColonyByCode")
    public ResponseEntity<Object> getDocumentByCode(@RequestParam String colonyCode,HttpServletRequest httpServletRequest){
        try {
            SelectOptionParam selectOptionParam = colonyService.fetchColonyMasterByCode(colonyCode);
            return ResponseEntity.ok(selectOptionParam);
        } catch (Exception e) {
           return ResponseBuilder.buildError(HttpStatus.NOT_FOUND, httpServletRequest.getRequestURI(), e.getMessage());
        }
    }



    //Add Colony
    @PostMapping("/add")
    public ResponseEntity<Object> addColony(@RequestParam String wardGuid, @RequestBody @Valid ColonyAddRequest colonyAddRequest, HttpServletRequest httpServletRequest) {
        ColonyAddResponse colonyAddResponse = colonyService.addColony(wardGuid, colonyAddRequest);
        return ResponseBuilder.buildCreated(colonyAddResponse.getStatus(), colonyAddResponse,httpServletRequest);
    }

    //Update Colony By colonyGuid
    @PutMapping("/update/{wardGuid}/{colonyGuid}")
    public ResponseEntity<Object> updateColony(@PathVariable String wardGuid,@PathVariable String colonyGuid,@RequestBody @Valid ColonyUpdateRequest colonyUpdateRequest,HttpServletRequest httpServletRequest) {
        ColonyUpdateResponse colonyUpdateResponse = colonyService.updateColonyByGuid(wardGuid, colonyGuid, colonyUpdateRequest);
        return ResponseBuilder.buildOk(colonyUpdateResponse.getStatus(), colonyUpdateResponse,httpServletRequest);
    }

    //Fetch All
    @GetMapping("/all")
    public ResponseEntity<Object> getAllColonies(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(colonyService.getAllColonies());
    }

    //Fetch Colony By ColonyGuid
    @GetMapping("/colonyGuid/{colonyGuid}")
    public ResponseEntity<Object> getByGuid(@PathVariable String colonyGuid,HttpServletRequest httpServletRequest) {
        try{
            ColonyResponse colonyResponse = colonyService.getColonyByGuid(colonyGuid);
            return ResponseEntity.ok(colonyResponse);
        }catch (Exception ex){
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(),ex.getMessage());
        }
    }
}
