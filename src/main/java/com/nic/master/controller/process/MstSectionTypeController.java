package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.request.process.mstsectiontype.SectionTypeRequestMapper;
import com.nic.master.service.process.MstSectionTypeService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mstSection")
public class MstSectionTypeController {

    private final MstSectionTypeService mstSectionTypeService;

    public MstSectionTypeController(MstSectionTypeService mstSectionTypeService) {
        this.mstSectionTypeService = mstSectionTypeService;
    }

    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public ResponseEntity<Object> handleSectionOperation(
            @Valid @RequestBody(required = false) SectionTypeRequestMapper sectionTypeRequestMapper,
            @RequestParam(value = "operation", required = false) String operation,
            @RequestParam(value = "sectionTypeGuid", required = false) String sectionTypeGuid,
            @RequestParam(value = "sectionTypeCode", required = false) String sectionTypeCode,
            HttpServletRequest httpServletRequest
    ) {
        String sectionOperation = null;

        if (sectionTypeRequestMapper != null && sectionTypeRequestMapper.getOperation() != null) {
            sectionOperation = sectionTypeRequestMapper.getOperation();
        } else if (operation != null) {
            sectionOperation = operation;
        }

        if (sectionOperation == null) {
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }

        return switch (sectionOperation.toUpperCase().trim()) {
            case "ADD" -> {
                StatusParam addResponse = null;
                if (sectionTypeRequestMapper != null) {
                    addResponse = mstSectionTypeService.addMstSection(
                            sectionTypeRequestMapper.getAddMstSectionTypeRequest()
                    );
                }
                yield ResponseBuilder.buildOk(addResponse, addResponse, httpServletRequest);
            }
            case "GETALL" -> ResponseEntity.ok(mstSectionTypeService.getAllMstSections());

            case "GETBYGUID" -> ResponseEntity.ok(mstSectionTypeService.getSectionByGuid(sectionTypeGuid));

            case "GETBYCODE" -> ResponseEntity.ok(mstSectionTypeService.getSectionByCode(sectionTypeCode));

            case "UPDATE" -> {
                StatusParam updateResponse = null;
                if (sectionTypeRequestMapper != null) {
                    updateResponse = mstSectionTypeService.updateSection(
                            sectionTypeGuid,
                            sectionTypeRequestMapper.getUpdateMstSectionTypeRequest()
                    );
                }
                yield ResponseBuilder.buildOk(updateResponse, updateResponse, httpServletRequest);
            }
            default -> ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid Operation : " + sectionOperation
            );
        };
    }
}
