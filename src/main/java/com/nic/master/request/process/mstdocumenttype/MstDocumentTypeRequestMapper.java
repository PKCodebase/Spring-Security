package com.nic.master.request.process.mstdocumenttype;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class MstDocumentTypeRequestMapper {

    private String operation;

    private String documentTypeGuid;

    private  String documentTypeCode;

    @Valid
    private AddMstDocumentTypeRequest addMstDocumentTypeRequest;

    @Valid
    private UpdateMstDocumentTypeRequest updateMstDocumentTypeRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDocumentTypeGuid() {
        return documentTypeGuid;
    }

    public void setDocumentTypeGuid(String documentTypeGuid) {
        this.documentTypeGuid = documentTypeGuid;
    }

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    public AddMstDocumentTypeRequest getAddMstDocumentTypeRequest() {
        return addMstDocumentTypeRequest;
    }

    public void setAddMstDocumentTypeRequest(AddMstDocumentTypeRequest addMstDocumentTypeRequest) {
        this.addMstDocumentTypeRequest = addMstDocumentTypeRequest;
    }

    public UpdateMstDocumentTypeRequest getUpdateMstDocumentTypeRequest() {
        return updateMstDocumentTypeRequest;
    }

    public void setUpdateMstDocumentTypeRequest(UpdateMstDocumentTypeRequest updateMstDocumentTypeRequest) {
        this.updateMstDocumentTypeRequest = updateMstDocumentTypeRequest;
    }
}
