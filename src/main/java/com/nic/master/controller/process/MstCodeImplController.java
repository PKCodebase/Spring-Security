package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.mstcodeimpl.MstCodeImplRequestMapper;
import com.nic.master.service.process.MstCodeService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mstCodeImpl")
public class MstCodeImplController {

    private final MstCodeService mstCodeService;

    public MstCodeImplController(MstCodeService mstCodeService) {
        this.mstCodeService = mstCodeService;
    }

    @RequestMapping(
            value = "/action",
            method = {RequestMethod.GET,RequestMethod.POST}
    )
    public ResponseEntity<Object> handleMstCodeImpl(
            @Valid @RequestBody(required = false)MstCodeImplRequestMapper mstCodeImplRequestMapper,
            @RequestParam(value = "operation",required = false) String operation,
            @RequestParam(value ="codeImplGuid",required = false) String codeImplGuid,
            HttpServletRequest httpServletRequest){

        String mstCodeImplOperation = null;

        if(mstCodeImplRequestMapper != null && mstCodeImplRequestMapper.getOperation() != null){
            mstCodeImplOperation = mstCodeImplRequestMapper.getOperation();
        } else if (operation != null) {
            mstCodeImplOperation = operation;

        }

        if(mstCodeImplOperation == null){
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }


        return switch (mstCodeImplOperation.toUpperCase().trim()){
            case "ADD" ->{
                StatusParam addResponse = null;
                if(mstCodeImplRequestMapper != null){
                    addResponse = mstCodeService.addMstCode(mstCodeImplRequestMapper.getAddMstCodeImplRequest());
                }
                yield ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
            }
            case "GETALL" -> ResponseEntity.ok(mstCodeService.getAllMstCodeImpl());
            case "GETBYGUID" -> ResponseEntity.ok(mstCodeService.getByGuid(codeImplGuid));
            case "UPDATE"  -> {
                StatusParam updateResponse = null;
                if(mstCodeImplRequestMapper != null){
                    updateResponse = mstCodeService.updateMstCodeImpl(
                            codeImplGuid,
                    mstCodeImplRequestMapper.getUpdateMstCodeImplRequest()
                            );
                }
                yield ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);
            }
            default -> ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid Operation : " + mstCodeImplOperation
            );
        };
    }
}
