package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.request.process.mstactiontype.MstActionRequestMapper;
import com.nic.master.service.process.MstActionService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mstAction")
public class MstActionTypeController {

    private final MstActionService mstActionService;

    public MstActionTypeController(MstActionService mstActionService) {
        this.mstActionService = mstActionService;
    }

    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET,RequestMethod.POST}
    )

    public ResponseEntity<Object> handleMstAction(
            @Valid @RequestBody(required = false)MstActionRequestMapper mstActionRequestMapper,
            @RequestParam(value = "operation",required = false) String operation,
            @RequestParam(value = "actionTypeCode",required = false) String actionTypeCode,
            @RequestParam(value = "actionTypeGuid",required = false) String actionTypeGuid,
            HttpServletRequest httpServletRequest){


        String mstActionOperation = null;

        if(mstActionRequestMapper != null && mstActionRequestMapper.getOperation() != null){
            mstActionOperation = mstActionRequestMapper.getOperation();
        }else if(operation != null){
            mstActionOperation= operation;
        }

        if(mstActionOperation == null){
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }

        return switch (mstActionOperation.toUpperCase().trim()){
            case "ADD" ->{
                StatusParam addResponse = null;
                if(mstActionRequestMapper != null  ){
                    addResponse = mstActionService.addMstAction(mstActionRequestMapper.getAddMstActionRequest());
                }
                yield  ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
            }
            case "GETALL" -> ResponseEntity.ok(mstActionService.getAllMstActions());
            case "GETBYCODE" -> ResponseEntity.ok(mstActionService.getMstActionByCode(actionTypeCode));
            case "GETBYGUID" -> ResponseEntity.ok(mstActionService.getMstActionByGuid(actionTypeGuid));
            case "UPDATE" -> {
                StatusParam updateResponse = null;
                if(mstActionRequestMapper != null){
                    updateResponse = mstActionService.updateMstAction(
                            mstActionRequestMapper.getActionTypeGuid(),
                            mstActionRequestMapper.getUpdateMstActionRequest()
                    );
                }
                yield ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);
            }
            default ->  ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid Operation : " + mstActionOperation
            );
        };


    }
}
