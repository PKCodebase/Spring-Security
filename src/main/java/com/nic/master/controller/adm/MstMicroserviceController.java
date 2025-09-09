package com.nic.master.controller.adm;

import com.nic.master.param.StatusParam;
import com.nic.master.request.adm.mstmicroservicerequest.MicroserviceAddRequest;
import com.nic.master.request.adm.mstmicroservicerequest.MicroserviceRequestMapper;
import com.nic.master.request.adm.mstmicroservicerequest.MicroserviceUpdateRequest;
import com.nic.master.service.admservice.MstMicroserviceService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/microservice")
public class MstMicroserviceController {

    private final MstMicroserviceService mstMicroserviceService;


    public MstMicroserviceController(MstMicroserviceService mstMicroserviceService) {
        this.mstMicroserviceService = mstMicroserviceService;
    }

//    @PostMapping("/add")
//    public ResponseEntity<Object> addMicroservice(@Valid @RequestBody MicroserviceAddRequest microserviceAddRequest, HttpServletRequest httpServletRequest){
//        try{
//            StatusParam response = mstMicroserviceService.addMicroservice(microserviceAddRequest);
//            return ResponseBuilder.buildOk(response,response,httpServletRequest);
//        }catch (Exception ex){
//            return  ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(),ex.getMessage());
//        }
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<Object> getAllMicroservices(HttpServletRequest httpServletRequest){
//        try{
//            return ResponseEntity.ok(mstMicroserviceService.getAllMicroservices());
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }
//
//        @GetMapping("/getByGuid/{microserviceGuid}")
//    public ResponseEntity<Object> getMicroserviceByGuid(@PathVariable String microserviceGuid,HttpServletRequest httpServletRequest){
//        try{
//            return ResponseEntity.ok(mstMicroserviceService.getMicroserviceByGuid(microserviceGuid));
//        }catch (Exception ex){
//            return  ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }
//
//    @GetMapping("/getByCode/{microserviceCode}")
//    public ResponseEntity<Object> getMicroserviceByCode(@PathVariable String microserviceCode,HttpServletRequest httpServletRequest){
//        try{
//            return ResponseEntity.ok(mstMicroserviceService.getMicroserviceByCode(microserviceCode));
//        }catch (Exception ex){
//            return  ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }
//
//    @PutMapping("/update/{microserviceGuid}")
//    public ResponseEntity<Object> updateMicroservice(@Valid @PathVariable String microserviceGuid, @RequestBody MicroserviceUpdateRequest microserviceUpdateRequest,HttpServletRequest httpServletRequest){
//        try {
//            StatusParam response = mstMicroserviceService.updateMicroServiceByGuid(microserviceGuid,microserviceUpdateRequest);
//            return ResponseBuilder.buildOk(response,response,httpServletRequest);
//        }catch (Exception ex){
//            return  ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(),ex.getMessage());
//        }
//    }
@RequestMapping(
        value = "/action",
        method = {RequestMethod.GET, RequestMethod.POST}
)
public ResponseEntity<Object> handleMicroserviceAction(
        @Valid @RequestBody(required = false) MicroserviceRequestMapper microserviceRequestMapper,
        @RequestParam(value = "operation",required = false) String operation,
        @RequestParam(value = "microserviceGuid",required = false) String microserviceGuid,
        @RequestParam(value = "microserviceCode",required = false) String microserviceCode,
        HttpServletRequest httpServletRequest) {

        String microserviceOperation = null;
        if(microserviceRequestMapper != null && microserviceRequestMapper.getOperation() != null){
            microserviceOperation = microserviceRequestMapper.getOperation();
        } else if (operation != null) {
            microserviceOperation = operation;

        }

    if (microserviceOperation == null) {
        return ResponseBuilder.buildError(
                HttpStatus.BAD_REQUEST,
                httpServletRequest.getRequestURI(),
                "Operation is required"
        );
    }

    return switch (microserviceOperation.toUpperCase().trim()) {
        case "ADD" -> {
            StatusParam addResponse = null;
            if(microserviceRequestMapper != null) {
                addResponse = mstMicroserviceService.addMicroservice(microserviceRequestMapper.getMicroserviceAddRequest());
            }
            yield ResponseBuilder.buildOk(addResponse, addResponse, httpServletRequest);
        }
        case "GETALL" -> ResponseEntity.ok(mstMicroserviceService.getAllMicroservices());
        case "GETBYGUID" -> ResponseEntity.ok(mstMicroserviceService.getMicroserviceByGuid(microserviceGuid));
        case "GETBYCODE" -> ResponseEntity.ok(mstMicroserviceService.getMicroserviceByCode(microserviceCode));
        case "UPDATE" -> {
            StatusParam updateResponse = null;
            if(microserviceRequestMapper != null) {
                updateResponse = mstMicroserviceService.updateMicroServiceByGuid(
                       microserviceGuid,
                        microserviceRequestMapper.getMicroserviceUpdateRequest()
                );
            }
            yield ResponseBuilder.buildOk(updateResponse, updateResponse, httpServletRequest);
        }
        default -> ResponseBuilder.buildError(
                HttpStatus.BAD_REQUEST,
                httpServletRequest.getRequestURI(),
                "Invalid Operation : " + microserviceRequestMapper.getOperation()
        );
    };
}

}
