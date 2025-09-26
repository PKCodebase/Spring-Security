package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.reftemplate.RefTemplateMapper;
import com.nic.master.service.process.RefTemplateService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refTemplate")
public class RefTemplateController {

    private final RefTemplateService refTemplateService;

    public RefTemplateController(RefTemplateService refTemplateService) {
        this.refTemplateService = refTemplateService;
    }

    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public ResponseEntity<Object> handleRefTemplateOperation(
            @RequestParam(value = "processDefGuid", required = false) String processDefGuid,
            @RequestParam(value = "sectionTypeGuid", required = false) String sectionTypeGuid,
            @RequestParam(value = "actionTypeGuid", required = false) String actionTypeGuid,
            @Valid @RequestBody(required = false) RefTemplateMapper refTemplateMapper,
            @RequestParam(value = "operation", required = false) String operation,
            @RequestParam(value = "refTemplateGuid", required = false) String refTemplateGuid,
            @RequestParam(value = "refTemplateCode", required = false) String refTemplateCode,
            HttpServletRequest httpServletRequest
    ) {

        // Determine operation
        String refTemplateOperation = (refTemplateMapper != null && refTemplateMapper.getOperation() != null)
                ? refTemplateMapper.getOperation()
                : operation;

        if (refTemplateOperation == null) {
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }

        switch (refTemplateOperation.toUpperCase().trim()) {
            case "ADD" -> {
                StatusParam addResponse = null;
                if (refTemplateMapper != null && refTemplateMapper.getAddRefTemplateRequest() != null) {
                    addResponse = refTemplateService.addRefTemplate(
                            processDefGuid,
                            sectionTypeGuid,
                            actionTypeGuid,
                            refTemplateMapper.getAddRefTemplateRequest()
                    );
                }
                return ResponseBuilder.buildOk(addResponse, addResponse, httpServletRequest);
            }

            case "GETALL" -> {
                return ResponseEntity.ok(refTemplateService.getAllRefTemplates());
            }

            case "GETBYGUID" -> {
                return ResponseEntity.ok(refTemplateService.getRefTemplateByGuid(refTemplateGuid));
            }
            case "GETBYCODE"->{
                return ResponseEntity.ok(refTemplateService.getRefTemplateByCode(refTemplateCode));
            }case "UPDATE" -> {
                StatusParam updateResponse = null;
                if (refTemplateMapper != null && refTemplateMapper.getUpdateRefTemplateRequest() != null) {
                    updateResponse = refTemplateService.updateRefTemplate(
                            processDefGuid,
                            sectionTypeGuid,
                            actionTypeGuid,
                            refTemplateGuid,
                            refTemplateMapper.getUpdateRefTemplateRequest()
                    );
                }
                return ResponseBuilder.buildOk(updateResponse, updateResponse, httpServletRequest);
            }

            default -> {
                return ResponseBuilder.buildError(
                        HttpStatus.BAD_REQUEST,
                        httpServletRequest.getRequestURI(),
                        "Invalid operation: " + refTemplateOperation
                );
            }
        }

    }
}
