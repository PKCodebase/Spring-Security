package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.request.process.refquery.RefQueryRequestMapper;
import com.nic.master.service.process.RefQueryService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refQuery")
public class RefQueryController {

    private final RefQueryService refQueryService;

    public RefQueryController(RefQueryService refQueryService) {
        this.refQueryService = refQueryService;
    }

    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public ResponseEntity<Object> handleRefQueryOperation(
            @RequestParam(value = "processDefGuid", required = false) String processDefGuid,
            @RequestParam(value = "operation", required = false) String operation,
            @RequestParam(value = "processDefDescGuid", required = false) String processDefDescGuid,
            @RequestParam(value = "sectionTypeGuid", required = false) String sectionTypeGuid,
            @Valid @RequestBody RefQueryRequestMapper refQueryRequestMapper,
            HttpServletRequest httpServletRequest
    ) {
        String refQueryOperation = (refQueryRequestMapper != null && refQueryRequestMapper.getOperation() != null)
                ? refQueryRequestMapper.getOperation()
                : operation;

        if (refQueryOperation == null) {
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }
        switch (refQueryOperation.toUpperCase().trim()) {
            case "ADD" -> {
                StatusParam addResponse = null;
                if (refQueryRequestMapper != null) {
                    addResponse = refQueryService.addRefQuery(
                            processDefGuid,
                            processDefDescGuid,
                            sectionTypeGuid,
                            refQueryRequestMapper.getRefQuery()
                    );
                }
                return ResponseBuilder.buildOk(addResponse, addResponse, httpServletRequest);
            }
            default -> {
                return ResponseBuilder.buildError(
                        HttpStatus.BAD_REQUEST,
                        httpServletRequest.getRequestURI(),
                        "Invalid operation: " + refQueryOperation
                );
            }
        }

    }
}
