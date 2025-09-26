package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.processalertrequest.ProcessAlertRequestMapper;
import com.nic.master.service.process.ProcessAlertService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processAlert")
public class ProcessAlertController {

    private final ProcessAlertService processAlertService;

    public ProcessAlertController(ProcessAlertService processAlertService) {
        this.processAlertService = processAlertService;
    }

    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public ResponseEntity<Object> handleProcessAlertOperation(
            @Valid @RequestBody(required = false) ProcessAlertRequestMapper processAlertRequestMapper,
            @RequestParam(value = "operation", required = false) String operation,
            @RequestParam(value = "processAlertGuid", required = false) String processAlertGuid,
            @RequestParam(value = "processDefGuid", required = false) String processDefGuid,
            @RequestParam(value = "actionTypeGuid", required = false) String actionTypeGuid,
            HttpServletRequest httpServletRequest
    ) {
        String processAlertOperation = null;

        if (processAlertRequestMapper != null && processAlertRequestMapper.getOperation() != null) {
            processAlertOperation = processAlertRequestMapper.getOperation();
        } else if (operation != null) {
            processAlertOperation = operation;
        }

        if (processAlertOperation == null) {
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }

        return switch (processAlertOperation.toUpperCase().trim()) {
            case "ADD" -> {
                StatusParam addResponse = null;
                if (processAlertRequestMapper != null) {
                    addResponse = processAlertService.addProcessAlert(
                            processDefGuid,
                            actionTypeGuid,
                            processAlertRequestMapper.getAddProcessAlertRequest()
                    );
                }
                yield ResponseBuilder.buildOk(addResponse, addResponse, httpServletRequest);
            }
            case "GETALL" -> ResponseEntity.ok(processAlertService.getAllProcessAlert());
            case "GETBYGUID" -> ResponseEntity.ok(processAlertService.getProcessAlertByGuid(processAlertGuid));
            case "UPDATE" -> {
                StatusParam updateResponse = null;
                if (processAlertRequestMapper != null) {
                    updateResponse = processAlertService.updateProcessAlert(
                            processDefGuid,
                            actionTypeGuid,
                            processAlertGuid,
                            processAlertRequestMapper.getUpdateProcessAlertRequest()
                    );
                }
                yield ResponseBuilder.buildOk(updateResponse, updateResponse, httpServletRequest);
            }
            default -> ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid Operation " + processAlertOperation
            );
        };
    }
}
