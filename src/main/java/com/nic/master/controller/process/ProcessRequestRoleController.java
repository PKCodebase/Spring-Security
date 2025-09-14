package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.request.process.processedrequestrole.ProcessRequestRoleMapper;
import com.nic.master.service.process.ProcessRequestRoleService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processRequestRole")
public class ProcessRequestRoleController {

    private final ProcessRequestRoleService processRequestRoleService;

    public ProcessRequestRoleController(ProcessRequestRoleService processRequestRoleService) {
        this.processRequestRoleService = processRequestRoleService;
    }

    @RequestMapping(
            value = "/action",
            method ={RequestMethod.GET,RequestMethod.POST}
    )
    public ResponseEntity<Object> handleProcessRequestRoleOperation(
            @Valid @RequestBody (required = false)ProcessRequestRoleMapper processRequestRoleMapper,
            @RequestParam(value = "operation",required = false) String operation,
            @RequestParam(value = "processDefGuid",required = false) String processDefGuid,
            @RequestParam(value = "processedRequestRoleGuid",required = false) String processedRequestRoleGuid,
            @RequestParam(value = "approveProcessedRoleCode",required = false) String approveProcessedRoleCode,
            @RequestParam(value = "rejectProcessedRoleCode",required = false) String rejectProcessedRoleCode,
            @RequestParam(value = "closeProcessedRoleCode",required = false) String closeProcessedRoleCode,
            @RequestParam(value = "processUserRoleCode",required = false) String processUserRoleCode,
            HttpServletRequest httpServletRequest
            ) {
        String processRequestRoleOperation = null;
        if (processRequestRoleMapper != null && processRequestRoleMapper.getOperation() != null) {
            processRequestRoleOperation = processRequestRoleMapper.getOperation();
        } else if (operation != null) {
            processRequestRoleOperation = operation;
        }

        if (processRequestRoleOperation == null) {
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }
        return switch (processRequestRoleOperation.toUpperCase().trim()) {
            case "ADD" -> {
                StatusParam addResponse = null;
                if (processRequestRoleMapper != null) {
                    addResponse = processRequestRoleService.addProcessRequestRole(
                            processDefGuid,
                            processRequestRoleMapper.getAddProcessedRoleRequest()
                    );
                }
                yield ResponseBuilder.buildOk(addResponse, addResponse, httpServletRequest);
            }
            case "GETALL" -> ResponseEntity.ok(processRequestRoleService.getAllProcessRequestRoles());
            case "GETBYGUID"-> ResponseEntity.ok(processRequestRoleService.getProcessedRequestRoleByGuid(processedRequestRoleGuid));

            default -> ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid operation: " + processRequestRoleOperation
            );
        };
    }
}
