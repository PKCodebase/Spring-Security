package com.nic.master.controller.process;

import com.nic.master.entity.process.ProcessDefDescAction;
import com.nic.master.param.StatusParam;
import com.nic.master.request.process.processdefdescriptionaction.ProcessDefDescActionRequestMapper;
import com.nic.master.service.process.ProcessDefDescActionService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processDefDescAction")
public class ProcessDefDescActionController {

    private final ProcessDefDescActionService processDefDescActionService;

    public ProcessDefDescActionController(ProcessDefDescActionService processDefDescActionService) {
        this.processDefDescActionService = processDefDescActionService;
    }

    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET,RequestMethod.POST}
    )
    public ResponseEntity<Object> handleProcessDefDescAction(
            @Valid @RequestBody (required = false) ProcessDefDescActionRequestMapper  processDefDescActionRequestMapper,
            @RequestParam (value = "operation",required = false) String operation,
            @RequestParam (value = "processDefDescActionGuid",required = false) String processDefDescActionGuid,
            @RequestParam (value = "processDefDescGuid",required = false) String processDefDescGuid,
            @RequestParam (value = "actionTypeGuid",required = false) String actionTypeGuid,
            @RequestParam (value = "sectionTypeGuid",required = false) String sectionTypeGuid,
            HttpServletRequest httpServletRequest
    ){
       String processDefDescActionOperation = (processDefDescActionRequestMapper != null && processDefDescActionRequestMapper.getOperation() != null)
               ? processDefDescActionRequestMapper.getOperation()
                : operation;

        if(processDefDescActionOperation == null){
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }
        return switch (processDefDescActionOperation.toUpperCase().trim()){
            case "ADD" ->{
                StatusParam addResponse = null;
                if(processDefDescActionRequestMapper != null && processDefDescActionRequestMapper.getAddProcessDefDescActionRequest() != null){
                    addResponse = processDefDescActionService.addDescAction(
                            processDefDescGuid,
                            actionTypeGuid,
                            sectionTypeGuid,
                            processDefDescActionRequestMapper.getAddProcessDefDescActionRequest()
                    );
                }
                yield ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
            }
            case "GETALL"-> ResponseEntity.ok(processDefDescActionService.getAllDescActions());
            case "GETBYGUID"-> ResponseEntity.ok(processDefDescActionService.getDescActionByGuid(processDefDescActionGuid));
            case "UPDATE"->{
                StatusParam updateResponse = null;
                if(processDefDescActionRequestMapper != null && processDefDescActionRequestMapper.getUpdateProcessDefDescActionRequest() != null){
                    updateResponse = processDefDescActionService.updateDescAction(
                            processDefDescGuid,
                            actionTypeGuid,
                            sectionTypeGuid,
                            processDefDescActionGuid,
                            processDefDescActionRequestMapper.getUpdateProcessDefDescActionRequest()
                    );
                }
                yield ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);
            }
            default -> ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid Operation"

            );
        };

    }
}
