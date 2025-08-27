package com.nic.master.controller.adm;

import com.nic.master.entity.adm.MstApiService;
import com.nic.master.param.StatusParam;
import com.nic.master.request.adm.apiservice.AddApiServiceRequest;
import com.nic.master.request.adm.apiservice.ApiServiceRequestMapper;
import com.nic.master.request.adm.apiservice.UpdateApiServiceRequest;
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
//            StatusParam response = mstApiServices.addApiService(addApiServiceRequest);
//            return ResponseBuilder.buildOk(response,response,httpServletRequest);
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
//          StatusParam response = mstApiServices.updateApiService(apiServiceGuid,updateApiServiceRequest);
//          return ResponseBuilder.buildOk(response,response,httpServletRequest);
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }


    @PostMapping("/action")
    public ResponseEntity<Object> handleApiServiceAction(@Valid @RequestBody ApiServiceRequestMapper apiServiceRequestMapper,HttpServletRequest httpServletRequest){
        switch (apiServiceRequestMapper.getOperation().toUpperCase().trim()){
            case "ADD" :
                StatusParam addResponse = mstApiServices.addApiService(apiServiceRequestMapper.getAddApiServiceRequest());
                return ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
            case "GETALL":
                return ResponseEntity.ok(mstApiServices.getAllApiServices());
            case "GETBYCODE":
                return ResponseEntity.ok(mstApiServices.getApiServiceByCode(apiServiceRequestMapper.getApiServiceCode()));
            case "GETBYGUID" :
                return ResponseEntity.ok(mstApiServices.getApiServiceByGuid(apiServiceRequestMapper.getApiServiceGuid()));
            case "UPDATE":
                StatusParam updateResponse = mstApiServices.updateApiService(apiServiceRequestMapper.getApiServiceGuid(),apiServiceRequestMapper.getUpdateApiServiceRequest());
                return ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);

            default:
                return ResponseBuilder.buildError(
                        HttpStatus.BAD_REQUEST,
                        httpServletRequest.getRequestURI(),
                        "Invalid operation : " + apiServiceRequestMapper.getOperation()
                );
        }
    }
}
