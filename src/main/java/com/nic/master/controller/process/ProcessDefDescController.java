package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.request.process.processdefdescrequest.ProcessDefDescRequestMapper;
import com.nic.master.service.process.ProcessDefDescService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processDefDesc")
public class ProcessDefDescController {

    private final ProcessDefDescService processDefDescService;

    public ProcessDefDescController(ProcessDefDescService processDefDescService) {
        this.processDefDescService = processDefDescService;
    }

    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET,RequestMethod.POST}
    )
    public ResponseEntity<Object> handleProcessDefDescriptionOperation(
            @Valid @RequestBody ProcessDefDescRequestMapper processDefDescRequestMapper,
            @RequestParam (value = "operation", required = false) String operation,
            @RequestParam (value = "processDefDescGuid", required = false) String processDefDescGuid,
            @RequestParam (value = "processDefGuid", required = false) String processDefGuid,
            @RequestParam (value = "sectionTypeGuid", required = false) String sectionTypeGuid,
            @RequestParam(value = "roleCode", required = false) String roleCode,
            HttpServletRequest httpServletRequest
            ){
        String processDefDescOperation = null;
        if(processDefDescRequestMapper != null && processDefDescRequestMapper.getOperation() != null){
            processDefDescOperation = processDefDescRequestMapper.getOperation();
        } else if (operation != null) {
            processDefDescOperation = operation;
        }
        if(processDefDescOperation == null){
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }
        return switch (processDefDescOperation.toUpperCase().trim()){
            case "ADD"-> {
                StatusParam addResponse = null;
                if (processDefDescRequestMapper != null) {
                    addResponse = processDefDescService.addProcessDefDesc(
                            processDefGuid,
                            sectionTypeGuid,
                            processDefDescRequestMapper.getAddProcessDefDescRequest()
                    );
                }
                yield ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
            }
            case "GETALL"-> ResponseEntity.ok(processDefDescService.getAllProcessDefDesc());
            case "GETBYGUID"-> ResponseEntity.ok(processDefDescService.getProcessDefDescByGuid(processDefDescGuid));
//            case "GETBYCODE"-> ResponseEntity.ok(processDefDescService.getByRoleCode(roleCode));
            case "UPDATE"-> {
                StatusParam updateResponse = null;
                if (processDefDescRequestMapper != null) {
                    updateResponse = processDefDescService.updateProcessDefDesc(
                            processDefDescGuid,
                            sectionTypeGuid,
                            processDefGuid,
                            processDefDescRequestMapper.getUpdateProcessDefDescRequest()
                    );
                }
                yield ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);
            }
            default -> ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid Operation" +processDefDescOperation
            );
        };

    }
}
