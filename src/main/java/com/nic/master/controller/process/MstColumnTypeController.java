package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.mstcolumntype.MstColumnRequestMapper;
import com.nic.master.service.process.MstColumnService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mstColumn")
public class MstColumnTypeController {

    private final MstColumnService mstColumnService;

    public MstColumnTypeController(MstColumnService mstColumnService) {
        this.mstColumnService = mstColumnService;
    }

    @RequestMapping(
            value = "/action",
            method  = {RequestMethod.GET,RequestMethod.POST}
    )

    public ResponseEntity<Object> handleMstColumn(
            @Valid @RequestBody (required = false)MstColumnRequestMapper mstColumnRequestMapper,
            @RequestParam(value = "operation",required = false) String operation,
            @RequestParam(value = "columnTypeGuid",required = false) String columnTypeGuid,
            @RequestParam(value = "columnTypeCode",required = false) String columnTypeCode,
            HttpServletRequest httpServletRequest
            ){
        String  mstColumnOperation = null;

        if(mstColumnRequestMapper != null && mstColumnRequestMapper.getOperation() != null){
            mstColumnOperation = mstColumnRequestMapper.getOperation();
        } else if (operation != null) {
            mstColumnOperation = operation;

        }
        if(mstColumnOperation == null){
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }
        return switch (mstColumnOperation.toUpperCase().trim()){
            case "ADD" ->{
                StatusParam addResponse = null;
                if(mstColumnRequestMapper != null){
                    addResponse = mstColumnService.addMstColumn(mstColumnRequestMapper.getAddMstColumnTypeRequest());
                }
                yield ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
            }
            case "GETALL" -> ResponseEntity.ok(mstColumnService.getAllMstColumns());
            case "GETBYGUID" -> ResponseEntity.ok(mstColumnService.getMstColumnByGuid(columnTypeGuid));
            case "GETBYCODE" -> ResponseEntity.ok(mstColumnService.getMstColumnByCode(columnTypeCode));

            case "UPDATE" -> {
                StatusParam updateResponse = null;
                if(mstColumnRequestMapper != null){
                    updateResponse = mstColumnService.updateMstColumn(
                            columnTypeGuid,
                            mstColumnRequestMapper.getUpdateMstColumnTypeRequest()
                    );

                }
                yield ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);
            }
            default -> ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid Operation : " + mstColumnOperation
            );
        };
    }
}
