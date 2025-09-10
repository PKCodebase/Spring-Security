package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.request.process.processdefconfigrequest.ProcessDefConfigRequestMapper;
import com.nic.master.service.process.ProcessDefConfigService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processDefConfig")
public class ProcessDefConfigController {

    private final ProcessDefConfigService processDefConfigService;

    public ProcessDefConfigController(ProcessDefConfigService processDefConfigService) {
        this.processDefConfigService = processDefConfigService;
    }

    @RequestMapping(
            value = "/action",
            method ={RequestMethod.GET,RequestMethod.POST}
    )
    public ResponseEntity<Object> handleProcessDefConfigOperation(
            @Valid @RequestBody (required = false)ProcessDefConfigRequestMapper processDefConfigRequestMapper,
            @RequestParam (value="/operation",required = false) String operation,
            @RequestParam(value="processDefConfigGuid",required = false) String processDefConfigGuid,
            @RequestParam(value="processDefGuid",required = false) String processDefGuid,
            HttpServletRequest httpServletRequest
    ){
         String processDefConfigOperation = null;
         if(processDefConfigRequestMapper != null && processDefConfigRequestMapper.getOperation() != null){
             processDefConfigOperation = processDefConfigRequestMapper.getOperation();
         } else if (operation != null) {
             processDefConfigOperation = operation;
         }
         if(processDefConfigOperation == null){
             return ResponseBuilder.buildError(
                     HttpStatus.BAD_REQUEST,
                     httpServletRequest.getRequestURI(),
                     "Operation is required"
             );
         }
         return switch (processDefConfigOperation.toUpperCase().trim()){
             case "ADD" ->{
                 StatusParam addResponse = null;
                 if(processDefConfigOperation != null){
                     addResponse = processDefConfigService.addProcessDefConfig(
                             processDefGuid,
                             processDefConfigRequestMapper.getAddProcessDefConfigRequest()
                     );
                 }
                 yield ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
             }
             case "GETALL"->ResponseEntity.ok(processDefConfigService.getAllProcessDefConfig());
             case "GETBYGUID"->ResponseEntity.ok(processDefConfigService.getProcessDefConfigByGuid(processDefConfigGuid));
             case "UPDATE"->{
                 StatusParam updateResponse = null;
                 if(processDefConfigOperation != null){
                        updateResponse = processDefConfigService.updateProcessDefConfig(
                                processDefGuid,
                                processDefConfigGuid,
                                processDefConfigRequestMapper.getUpdateProcessDefConfigRequest()
                        );
                 }
                 yield ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);
             }
             default -> ResponseBuilder.buildError(
                     HttpStatus.BAD_REQUEST,
                     httpServletRequest.getRequestURI(),
                     "Invalid Operation : " + processDefConfigOperation
             );
         };
    }

}
