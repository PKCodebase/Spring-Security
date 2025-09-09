package com.nic.master.controller.process;

import com.nic.master.param.StatusParam;
import com.nic.master.request.process.mstdocumenttype.MstDocumentTypeRequestMapper;
import com.nic.master.service.mstservice.DocumentService;
import com.nic.master.service.process.MstDocumentTypeService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mstDocument")
public class MstDocumentController {

    private final MstDocumentTypeService mstDocumentTypeService;

    public MstDocumentController(MstDocumentTypeService mstDocumentTypeService) {
        this.mstDocumentTypeService = mstDocumentTypeService;
    }

    @RequestMapping(
            value="/action",
            method = {RequestMethod.GET,RequestMethod.POST}
    )
    public ResponseEntity<Object> handleDocumentOperation(
            @Valid @RequestBody(required = false)MstDocumentTypeRequestMapper mstDocumentTypeRequestMapper,
            @RequestParam (value = "operation",required = false) String operation,
            @RequestParam(value = "documentTypeGuid",required = false) String documentTypeGuid,
            @RequestParam(value = "documentTypeCode",required = false) String documentTypeCode,
            HttpServletRequest httpServletRequest
            ){
        String mstDocumentOperation = null;

        if(mstDocumentTypeRequestMapper != null && mstDocumentTypeRequestMapper.getOperation() != null){
            mstDocumentOperation = mstDocumentTypeRequestMapper.getOperation();
        } else if (operation != null) {
            mstDocumentOperation = operation;

        }

        if(mstDocumentOperation == null){
            return ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Operation is required"
            );
        }
        return switch (mstDocumentOperation.toUpperCase().trim()){
            case "ADD" ->{
                StatusParam addResponse = null;
                if(mstDocumentTypeRequestMapper != null){
                    addResponse = mstDocumentTypeService.addMstDocument(mstDocumentTypeRequestMapper.getAddMstDocumentTypeRequest());
                }
                yield ResponseBuilder.buildOk(addResponse,addResponse,httpServletRequest);
            }
            case "GETALL" -> ResponseEntity.ok(mstDocumentTypeService.getAllDocuments());
            case "GETBYGUID" -> ResponseEntity.ok(mstDocumentTypeService.getDocumentTypeByGuid(documentTypeGuid));
            case "GETBYCODE" -> ResponseEntity.ok(mstDocumentTypeService.getDocumentTypeByCode(documentTypeCode));
            case "UPDATE" -> {
                StatusParam updateResponse = null;
                if(mstDocumentTypeRequestMapper != null){
                    updateResponse = mstDocumentTypeService.updateDocument(
                            documentTypeGuid,
                            mstDocumentTypeRequestMapper.getUpdateMstDocumentTypeRequest()
                    );
                }
                yield ResponseBuilder.buildOk(updateResponse,updateResponse,httpServletRequest);
            }
            default -> ResponseBuilder.buildError(
                    HttpStatus.BAD_REQUEST,
                    httpServletRequest.getRequestURI(),
                    "Invalid Operation : "+mstDocumentOperation
            );
        };
    }
}
