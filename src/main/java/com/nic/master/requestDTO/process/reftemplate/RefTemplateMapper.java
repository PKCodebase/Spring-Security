package com.nic.master.requestDTO.process.reftemplate;


import jakarta.validation.Valid;
import lombok.Data;

@Data
public class RefTemplateMapper {

    private String operation; // ADD, GETALL, GETBYGUID

    private String refTemplateGuid;

    private String refTemplateCode;


    @Valid
    private AddRefTemplateRequest addRefTemplateRequest;

    @Valid
    private  UpdateRefTemplateRequest updateRefTemplateRequest;
    // Getters & Setters
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRefTemplateGuid() {
        return refTemplateGuid;
    }

    public void setRefTemplateGuid(String refTemplateGuid) {
        this.refTemplateGuid = refTemplateGuid;
    }

    public AddRefTemplateRequest getAddRefTemplateRequest() {
        return addRefTemplateRequest;
    }

    public void setAddRefTemplateRequest(AddRefTemplateRequest addRefTemplateRequest) {
        this.addRefTemplateRequest = addRefTemplateRequest;
    }

    public String getRefTemplateCode() {
        return refTemplateCode;
    }

    public void setRefTemplateCode(String refTemplateCode) {
        this.refTemplateCode = refTemplateCode;
    }

    public UpdateRefTemplateRequest getUpdateRefTemplateRequest() {
        return updateRefTemplateRequest;
    }

    public void setUpdateRefTemplateRequest(UpdateRefTemplateRequest updateRefTemplateRequest) {
        this.updateRefTemplateRequest = updateRefTemplateRequest;
    }
}
