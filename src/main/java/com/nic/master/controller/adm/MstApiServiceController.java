package com.nic.master.controller.adm;

import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.adm.apiservicerequest.ApiServiceRequestMapper;
import com.nic.master.service.admservice.MstApiServices;
import com.nic.master.util.ResponseBuilder;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/apiService")
public class MstApiServiceController {

    private  final MstApiServices mstApiServices;

    public MstApiServiceController(MstApiServices mstApiServices) {
        this.mstApiServices = mstApiServices;
    }

//    @PostMapping("/add")
//    public ResponseEntity<Object> addApiService(@Valid @RequestBody AddApiServiceRequest addApiServiceRequest, HttpServletRequest httpServletRequest){
//        try {
//            StatusParam responseDTO = mstApiServices.addApiService(addApiServiceRequest);
//            return ResponseBuilder.buildOk(responseDTO,responseDTO,httpServletRequest);
//        } catch (Exception e) {
//            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(),e.getMessage());
//        }
//
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<Object> getAllApiService(HttpServletRequest httpServletRequest){
//        try {
//            return ResponseEntity.ok(mstApiServices.getAllApiServices());
//        } catch (Exception e) {
//           return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(),e.getMessage());
//        }
//
//    }
//
//    @GetMapping("/getByGuid/{apiServiceGuid}")
//    public ResponseEntity<Object> getApiServiceByGuid(@PathVariable  String apiServiceGuid, HttpServletRequest httpServletRequest){
//        try{
//            return ResponseEntity.ok(mstApiServices.getApiServiceByGuid(apiServiceGuid));
//        }catch (Exception ex){
//            return  ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(),ex.getMessage());
//        }
//    }
//
//    @GetMapping("/getByCode/{apiServiceCode}")
//    public ResponseEntity<Object> getApiServiceByCode(@PathVariable String apiServiceCode,HttpServletRequest httpServletRequest){
//        try{
//            return  ResponseEntity.ok(mstApiServices.getApiServiceByCode(apiServiceCode));
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }
//
//    @PutMapping("update/{apiServiceGuid}")
//    public ResponseEntity<Object> updateApiServiceByGuid(@PathVariable String apiServiceGuid , @RequestBody UpdateApiServiceRequest updateApiServiceRequest,HttpServletRequest httpServletRequest){
//        try{
//          StatusParam responseDTO = mstApiServices.updateApiService(apiServiceGuid,updateApiServiceRequest);
//          return ResponseBuilder.buildOk(responseDTO,responseDTO,httpServletRequest);
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }


    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public ResponseEntity<Object> handleApiServiceAction(
            @Valid @RequestBody(required = false) ApiServiceRequestMapper apiServiceRequestMapper,
            @RequestParam (value = "operation",required = false)String operation,
            @RequestParam (value = "apiServiceGuid",required=false)String apiServiceGuid,
            @RequestParam (value = "apiServiceCode",required = false)String apiServiceCode,
            HttpServletRequest httpServletRequest) {

        String apiServiceOperation = null;

        if(apiServiceRequestMapper != null && apiServiceRequestMapper.getOperation() != null){
            apiServiceOperation = apiServiceRequestMapper.getOperation();
        } else if (operation != null) {
            apiServiceOperation = operation;

        }


        if (apiServiceOperation == null ) {
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }

        return switch (apiServiceOperation.toUpperCase().trim()) {
            case "ADD" -> {
                StatusParam addResponse = null;
                if (apiServiceRequestMapper != null) {
                    addResponse = mstApiServices.addApiService(apiServiceRequestMapper.getAddApiServiceRequest());
                }
                yield ResponseBuilder.buildOk(addResponse, addResponse, httpServletRequest);
            }
            case "GETALL" -> ResponseEntity.ok(mstApiServices.getAllApiServices());
            case "GETBYCODE" -> ResponseEntity.ok(mstApiServices.getApiServiceByCode(apiServiceCode));
            case "GETBYGUID" -> ResponseEntity.ok(mstApiServices.getApiServiceByGuid(apiServiceGuid));
            case "UPDATE" -> {
                StatusParam updateResponse = null;
                if (apiServiceRequestMapper != null) {
                    updateResponse = mstApiServices.updateApiService(
                           apiServiceGuid,
                            apiServiceRequestMapper.getUpdateApiServiceRequest()
                    );
                }
                yield ResponseBuilder.buildOk(updateResponse, updateResponse, httpServletRequest);
            }
            default -> ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid operation : " + apiServiceOperation
            );
        };
    }

}
