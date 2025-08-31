package com.nic.master.controller.adm;

import com.nic.master.param.StatusParam;
import com.nic.master.request.adm.apiRequest.AddMstApiRequest;
import com.nic.master.service.admservice.MstApiService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mstApi")
public class MstApiController {

    private final MstApiService mstApiService;

    public MstApiController(MstApiService mstApiService) {
        this.mstApiService = mstApiService;
    }

    @PostMapping("/add/{microserviceGuid}/{urlTypeGuid}")
    public ResponseEntity<Object> addMstApi(@PathVariable String microserviceGuid,@PathVariable String urlTypeGuid, @Valid @RequestBody AddMstApiRequest addMstApiRequest, HttpServletRequest httpServletRequest){
        try{
            StatusParam addResponse =  mstApiService.addMstApi(microserviceGuid,urlTypeGuid,addMstApiRequest);
            return ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
        }catch (Exception ex){
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(),ex.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllApi(HttpServletRequest httpServletRequest){
        try{
            return ResponseEntity.ok(mstApiService.getAllMstApi());
        }catch (Exception ex){
            return  ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(),ex.getMessage());
        }
    }
}
