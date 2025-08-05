package com.nic.master.controller;

import java.util.Date;
import java.util.List;



import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.error.ApiError;
import com.nic.master.service.CommonMasterService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/common")
@Tag(name = "Common Master API", description = "APIs for managing common master data")
public class CommonMasterController {

    private final CommonMasterService commonMasterService;

    public CommonMasterController(CommonMasterService commonMasterService) {
        this.commonMasterService = commonMasterService;
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getCommonMasters(@RequestParam("tcode") String tableCode, HttpServletRequest httpServletRequest) {
        try {
            List<SelectOptionParam> masters = commonMasterService.fetchCommonMaster(tableCode);
            return ResponseEntity.ok(masters);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ApiError(new Date(), String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()),
                            HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), httpServletRequest.getRequestURI(), "", ""));
        }
    }


    @GetMapping("/getByCode")
    public ResponseEntity<Object> getCommonMasterByCode(@RequestParam("tcode") String tableCode,
                                                        @RequestParam("codeVal") String codeVal,
                                                        HttpServletRequest httpServletRequest) {
        try {
            SelectOptionParam master = commonMasterService.fetchCommonMasterByCode(tableCode, codeVal);
            return ResponseEntity.ok(master);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ApiError(new Date(), String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()),
                            HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), httpServletRequest.getRequestURI(), "", ""));
        }
    }

}
