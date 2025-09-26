package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.mstprocesstype.ProcessTypeRequestMapper;
import com.nic.master.service.process.MstProcessTypeService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mstProcess")
public class MstProcessTypeController {

    private final MstProcessTypeService mstProcessTypeService;

    public MstProcessTypeController(MstProcessTypeService mstProcessTypeService) {
        this.mstProcessTypeService = mstProcessTypeService;
    }

    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET,RequestMethod.POST}
    )
    public ResponseEntity<Object> handleProcessOperation(
            @Valid @RequestBody(required = false)ProcessTypeRequestMapper processTypeRequestMapper,
            @RequestParam(value = "operation",required = false) String operation,
            @RequestParam(value = "processTypeGuid",required = false) String processTypeGuid,
            @RequestParam(value = "processTypeCode",required = false) String processTypeCode,
            HttpServletRequest httpServletRequest
            ) {

        String processOperation = null;
        if (processTypeRequestMapper != null && processTypeRequestMapper.getOperation() != null) {
            processOperation = processTypeRequestMapper.getOperation();
        } else if (operation != null) {
            processOperation = operation;

        }

        if(processOperation == null){
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }
        return switch (processOperation.toUpperCase().trim()){
            case "ADD" ->{
                StatusParam addResponse = null;
                if(processTypeRequestMapper != null){
                    addResponse = mstProcessTypeService.addMstProcess(processTypeRequestMapper.getAddMstProcessTypeRequest());
                }
                yield ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
            }
            case "GETALL"-> ResponseEntity.ok(mstProcessTypeService.getAllMstProcess());
            case "GETBYGUID" -> ResponseEntity.ok(mstProcessTypeService.getByProcessTypeGuid(processTypeGuid));
            case "GETBYCODE" -> ResponseEntity.ok(mstProcessTypeService.getByProcessTypeCode(processTypeCode));

            case "UPDATE" -> {
                StatusParam updateResponse = null;
                if(processTypeRequestMapper != null) {
                    updateResponse = mstProcessTypeService.updateMstProcess(
                            processTypeGuid,
                            processTypeRequestMapper.getUpdateMstProcessTypeRequest()
                    );
                }
                    yield ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);
                }
                default -> ResponseBuilder.buildError(
                        HttpStatus.BAD_REQUEST,
                        httpServletRequest.getRequestURI(),
                        "Invalid Operation : " + processOperation
                );
            };

    }
}
