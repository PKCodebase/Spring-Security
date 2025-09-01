package com.nic.master.controller.adm;

import com.nic.master.param.StatusParam;
import com.nic.master.request.adm.msturlrequest.AddMstUrlRequest;
import com.nic.master.request.adm.msturlrequest.MstUrlRequestMapper;
import com.nic.master.request.adm.msturlrequest.UpdateMstUrlRequest;
import com.nic.master.service.admservice.MstUrlService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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

//    @PostMapping("/add")
//    public ResponseEntity<Object> addUrl(@RequestBody AddMstUrlRequest addMstUrlRequest, HttpServletRequest httpServletRequest){
//        try{
//            StatusParam addResponse = mstUrlService.addMstUrl(addMstUrlRequest);
//            return ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
//        }catch(Exception ex){
//            return  ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(),ex.getMessage());
//        }
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<Object> getAllUrl(HttpServletRequest httpServletRequest){
//        try{
//            return ResponseEntity.ok(mstUrlService.getAllUrl());
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }
//
//    @GetMapping("/getByGuid/{urlGuid}")
//    public ResponseEntity<Object> getUrlByGuid(@PathVariable String urlGuid,HttpServletRequest httpServletRequest){
//        try {
//            return  ResponseEntity.ok(mstUrlService.getApiUrlByGuid(urlGuid));
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(),ex.getMessage());
//        }
//    }
//
//    @GetMapping("/getByCode/{urlCode}")
//    public ResponseEntity<Object> getUrlByCode(@PathVariable String urlCode,HttpServletRequest httpServletRequest){
//        try{
//            return ResponseEntity.ok(mstUrlService.getApiUrlByCode(urlCode));
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }
//
//    @PutMapping("/update/{urlGuid}")
//    public  ResponseEntity<Object> updateByUrlGuid(@PathVariable String urlGuid, @Valid @RequestBody UpdateMstUrlRequest updateMstUrlRequest,HttpServletRequest httpServletRequest){
//        try{
//            StatusParam updateResponse = mstUrlService.updateMstUrlByGuid(urlGuid,updateMstUrlRequest);
//            return ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }

    @PostMapping("/action")
    public ResponseEntity<Object> handleMstUrlAction(@Valid @RequestBody MstUrlRequestMapper mstUrlRequestMapper,HttpServletRequest httpServletRequest){
        switch (mstUrlRequestMapper.getOperation().toUpperCase().trim()){
            case "ADD" :
                StatusParam addResponse = mstUrlService.addMstUrl(mstUrlRequestMapper.getAddMstUrlRequest());
                return ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
            case "GETALL" :
                return ResponseEntity.ok(mstUrlService.getAllUrl());
            case "GETBYCODE":
                return ResponseEntity.ok(mstUrlService.getApiUrlByCode(mstUrlRequestMapper.getUrlTypeCode()));

            case "GETBYGUID":
                return ResponseEntity.ok(mstUrlService.getApiUrlByGuid(mstUrlRequestMapper.getUrlTypeGuid()));
            case "UPDATE" :
                StatusParam updateResponse = mstUrlService.updateMstUrlByGuid(mstUrlRequestMapper.getUrlTypeGuid(),mstUrlRequestMapper.getUpdateMstUrlRequest());
                return ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);
            default:
                return ResponseBuilder.buildError(
                        HttpStatus.BAD_REQUEST,
                        httpServletRequest.getRequestURI(),
                        "Invalid Operation : " + mstUrlRequestMapper.getOperation()
                );
        }
    }
}
