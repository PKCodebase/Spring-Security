package com.nic.master.controller.mst;

import com.nic.master.entity.mst.DocumentType;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.mst.documentrequest.DocumentAddRequest;
import com.nic.master.request.mst.documentrequest.DocumentUpdateRequest;
import com.nic.master.service.mstservice.DocumentService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document")

public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/fetchDocumentByCode")
    public ResponseEntity<Object> fetchDocumentByCode(@RequestParam String documentCode, HttpServletRequest httpServletRequest) {
        try {
            SelectOptionParam selectOptionParam = documentService.fetchDocumentMasterByCode(documentCode);
            return ResponseEntity.ok(selectOptionParam);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addDocument(@RequestBody @Valid DocumentAddRequest documentAddRequest, HttpServletRequest httpServletRequest) {
        try {
            StatusParam response = documentService.addDocument(documentAddRequest);
            return ResponseBuilder.buildOk(response, response, httpServletRequest);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @PutMapping("/update/{documentGuid}")
    public ResponseEntity<Object> updateDocument(@PathVariable String documentGuid, @RequestBody @Valid DocumentUpdateRequest documentUpdateRequest, HttpServletRequest httpServletRequest) {
        try {
            StatusParam response = documentService.updateDocumentByGuid(documentGuid, documentUpdateRequest);
            return ResponseBuilder.buildOk(response, response, httpServletRequest);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @GetMapping("/getAllDocuments")
    public ResponseEntity<Object> getAllDocuments(HttpServletRequest httpServletRequest) {
        try {
            return ResponseEntity.ok(documentService.getAllDocuments());
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.INTERNAL_SERVER_ERROR, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

    @GetMapping("/getByGuid/{documentGuid}")
    public ResponseEntity<Object> getByGuid(@PathVariable String documentGuid, HttpServletRequest httpServletRequest) {
        try {
            DocumentType document = documentService.getDocumentByGuid(documentGuid);
            return ResponseEntity.ok(document);
        } catch (Exception ex) {
            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND, httpServletRequest.getRequestURI(), ex.getMessage());
        }
    }

}