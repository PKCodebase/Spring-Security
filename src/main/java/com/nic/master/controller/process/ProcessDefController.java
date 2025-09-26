package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.processdefrequest.ProcessDefRequestMapper;
import com.nic.master.service.process.ProcessDefService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processDef")
public class ProcessDefController {

    private final ProcessDefService processDefService;

    public ProcessDefController(ProcessDefService processDefService) {
        this.processDefService = processDefService;
    }

    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public ResponseEntity<Object> handleProcessDefOperation(
            @Valid @RequestBody(required = false) ProcessDefRequestMapper processDefRequestMapper,
            @RequestParam(value = "operation", required = false) String operation,
            @RequestParam(value = "processTypeGuid", required = false) String processTypeGuid,
            @RequestParam(value = "processDefGuid", required = false) String processDefGuid,
            @RequestParam(value = "processDefCode", required = false) String processDefCode,
            HttpServletRequest httpServletRequest
    ) {
        String processDefOperation = null;

        if (processDefRequestMapper != null && processDefRequestMapper.getOperation() != null) {
            processDefOperation = processDefRequestMapper.getOperation();
        } else if (operation != null) {
            processDefOperation = operation;
        }

        if (processDefOperation == null) {
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }

        return switch (processDefOperation.toUpperCase().trim()) {
            case "ADD" -> {
                StatusParam addResponse = null;
                if (processDefRequestMapper != null) {
                    addResponse = processDefService.addProcessDef(
                            processTypeGuid,
                            processDefRequestMapper.getAddProcessDefRequest()
                    );
                }
                yield ResponseBuilder.buildOk(addResponse, addResponse, httpServletRequest);
            }
            case "GETALL" -> ResponseEntity.ok(processDefService.getAllProcessDef());
            case "GETBYGUID" -> ResponseEntity.ok(processDefService.getProcessByGuid(processDefGuid));
            case "GETBYCODE" -> ResponseEntity.ok(processDefService.getProcessDefByCode(processDefCode));
            case "UPDATE" -> {
                StatusParam updateResponse = null;
                if (processDefRequestMapper != null) {
                    updateResponse = processDefService.updateProcessDef(
                            processTypeGuid,
                            processDefGuid,
                            processDefRequestMapper.getUpdateProcessDefRequest()
                    );
                }
                yield ResponseBuilder.buildOk(updateResponse, updateResponse, httpServletRequest);
            }
            default -> ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid Operation : " + processDefOperation
            );
        };
    }
}
