package com.nic.master.controller.adm;

import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.adm.msturlrequest.MstUrlRequestMapper;
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
@RequestMapping(
        value = "/action",
        method = {RequestMethod.GET, RequestMethod.POST}
)
public ResponseEntity<Object> handleUrlActions(
        @Valid @RequestBody(required = false) MstUrlRequestMapper requestWrapper,
        @RequestParam(value = "operation", required = false) String operation,
        @RequestParam(value = "urlTypeCode", required = false) String urlTypeCode,
        @RequestParam(value = "urlTypeGuid", required = false) String urlTypeGuid,
        HttpServletRequest httpServletRequest) {

    String urlOperation = null;

    // Priority: requestDTO body > requestDTO param
    if (requestWrapper != null && requestWrapper.getOperation() != null) {
        urlOperation = requestWrapper.getOperation();
    } else if (operation != null) {
        urlOperation = operation;
    }

    if (urlOperation == null) {
        return ResponseBuilder.buildError(
                HttpStatus.BAD_REQUEST,
                httpServletRequest.getRequestURI(),
                "Operation is required"
        );
    }

    return switch (urlOperation.toUpperCase().trim()) {
        case "ADD" -> {
            StatusParam addResponse = null;
            if (requestWrapper != null) {
                addResponse = mstUrlService.addMstUrl(requestWrapper.getAddMstUrlRequest());
            }
            yield ResponseBuilder.buildOk(addResponse, addResponse, httpServletRequest);
        }
        case "GETALL" -> ResponseEntity.ok(mstUrlService.getAllUrl());
        case "GETBYCODE" -> ResponseEntity.ok(mstUrlService.getApiUrlByCode(urlTypeCode));
        case "GETBYGUID" -> ResponseEntity.ok(mstUrlService.getApiUrlByGuid(urlTypeGuid));
        case "UPDATE" -> {
            StatusParam updateResponse = null;
            if (requestWrapper != null) {
                updateResponse = mstUrlService.updateMstUrlByGuid(
                        urlTypeGuid,
                        requestWrapper.getUpdateMstUrlRequest()
                );
            }
            yield ResponseBuilder.buildOk(updateResponse, updateResponse, httpServletRequest);
        }
        default -> ResponseBuilder.buildError(
                HttpStatus.BAD_REQUEST,
                httpServletRequest.getRequestURI(),
                "Invalid operation: " + urlOperation
        );
    };
}

}
