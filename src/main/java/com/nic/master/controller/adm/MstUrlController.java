package com.nic.master.controller.adm;

import com.nic.master.param.StatusParam;
import com.nic.master.request.adm.msturlrequest.AddMstUrlRequest;
import com.nic.master.service.admservice.MstUrlService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mstUrl")
public class MstUrlController {

    private final MstUrlService mstUrlService;

    public MstUrlController(MstUrlService mstUrlService) {
        this.mstUrlService = mstUrlService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addUrl(@RequestBody AddMstUrlRequest addMstUrlRequest, HttpServletRequest httpServletRequest){
        try{
            StatusParam addResponse = mstUrlService.addMstUrl(addMstUrlRequest);
            return ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
        }catch(Exception ex){
            return  ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(),ex.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllUrl(HttpServletRequest httpServletRequest){
        try{
            return ResponseEntity.ok(mstUrlService.getAllUrl());
        }catch (Exception ex){
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }
}
