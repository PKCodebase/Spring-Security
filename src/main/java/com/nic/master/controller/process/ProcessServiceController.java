package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.request.process.processservice.ProcessServiceRequestMapper;
import com.nic.master.service.process.ProcessServices;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processService")
public class ProcessServiceController {

    private final ProcessServices processServices;

    public ProcessServiceController(ProcessServices processServices) {
        this.processServices = processServices;
    }

    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET,RequestMethod.POST}
    )
    public ResponseEntity<Object> handleProcessServiceOperation(
            @Valid @RequestBody (required = false)ProcessServiceRequestMapper processServiceRequestMapper,
            @RequestParam (value = "operation", required = false) String operation,
            @RequestParam (value = "processDefGuid", required = false) String processDefGuid,
            @RequestParam (value = "processServiceGuid", required = false) String processServiceGuid,
            HttpServletRequest httpServletRequest
            ) {

        String processServiceOperation  = (processServiceRequestMapper != null && processServiceRequestMapper.getOperation() != null)
                ? processServiceRequestMapper.getOperation()
                : operation;

        if(processServiceOperation == null){
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }

        return switch (processServiceOperation.toUpperCase().trim()){
            case "ADD" ->{
                StatusParam addResponse = null;
                if(processServiceRequestMapper != null && processServiceRequestMapper.getAddProcessServiceRequest() != null){
                    addResponse = processServices.addProcessService(
                            processDefGuid,
                            processServiceRequestMapper.getAddProcessServiceRequest()
                    );
                }
                yield ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
            }
            case "GETALL"->
                ResponseEntity.ok(processServices.getAllProcessServices());
            case "GETBYGUID" ->
                ResponseEntity.ok(processServices.getProcessServiceByGuid(processServiceGuid));
            case "UPDATE" ->{
                StatusParam updateResponse = null;
                if(processServiceRequestMapper != null && processServiceRequestMapper.getUpdateProcessServiceRequest()!= null){
                    updateResponse = processServices.updateProcessService(
                            processDefGuid,
                            processServiceGuid,
                            processServiceRequestMapper.getUpdateProcessServiceRequest()
                    );
                }
                yield ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);
            }
            default -> ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid Operation : " + processServiceOperation
            );
        };

    }
}
