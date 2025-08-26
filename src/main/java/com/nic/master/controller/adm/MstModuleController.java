package com.nic.master.controller.adm;

import com.nic.master.param.StatusParam;
import com.nic.master.request.adm.modulerequest.MstModuleAddRequest;
import com.nic.master.request.adm.modulerequest.MstModuleUpdateRequest;
import com.nic.master.service.admservice.MstModuleService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modules")
public class MstModuleController {

    private final MstModuleService mstModuleService;

    public MstModuleController(MstModuleService mstModuleService) {
        this.mstModuleService = mstModuleService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createModule(@Valid @RequestBody MstModuleAddRequest mstModuleAddRequest, HttpServletRequest httpServletRequest) {
        try{
            StatusParam response = mstModuleService.addModule(mstModuleAddRequest);
            return ResponseBuilder.buildOk(response,response,httpServletRequest);
        }catch (Exception e){
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(),e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllModules(HttpServletRequest httpServletRequest){
        try{
            return ResponseEntity.ok(mstModuleService.getAllModules());
        }catch (Exception e){
            return ResponseBuilder.buildError(HttpStatus.INTERNAL_SERVER_ERROR,httpServletRequest.getRequestURI(),e.getMessage());
        }
    }

    @GetMapping("/getByGuid/{moduleGuid}")
    public ResponseEntity<Object>  getByGuid(@PathVariable String moduleGuid,HttpServletRequest httpServletRequest){
        try{
            return ResponseEntity.ok(mstModuleService.getModuleByGuid(moduleGuid));
        }catch (Exception e){
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(),e.getMessage());
        }
    }

    @GetMapping("/getByCode/{moduleCode}")
    public ResponseEntity<Object> getByCode(@PathVariable String moduleCode,HttpServletRequest httpServletRequest){
        try{
            return ResponseEntity.ok(mstModuleService.getModuleByCode(moduleCode));
        }catch (Exception e){
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(),e.getMessage());
        }
    }
    @PutMapping("/update/{moduleGuid}")
    public ResponseEntity<Object> updateModule(@PathVariable String moduleGuid, @Valid @RequestBody MstModuleUpdateRequest mstModuleUpdateRequest, HttpServletRequest httpServletRequest) {
        try {
            StatusParam response = mstModuleService.updateModuleByGuid(moduleGuid, mstModuleUpdateRequest);
            return ResponseBuilder.buildOk(response, response, httpServletRequest);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }
}
