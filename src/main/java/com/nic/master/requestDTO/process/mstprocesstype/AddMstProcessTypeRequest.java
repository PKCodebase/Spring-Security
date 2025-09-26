package com.nic.master.requestDTO.process.mstprocesstype;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddMstProcessTypeRequest {

    @NotBlank(message = "ProcessType Code is required")
    private String processTypeCode;

    @NotBlank(message = "ProcessType English Name is required")
    private String processTypeNameEn;



    private String processTypeNameHi;


    private String processTypeNameRl;


    private String processTypeDescription;

    private String createdRemarks;

    private String createdUri;

    public String getProcessTypeCode() {
        return processTypeCode;
    }

    public void setProcessTypeCode(String processTypeCode) {
        this.processTypeCode = processTypeCode;
    }

    public String getProcessTypeNameEn() {
        return processTypeNameEn;
    }

    public void setProcessTypeNameEn(String processTypeNameEn) {
        this.processTypeNameEn = processTypeNameEn;
    }

    public String getProcessTypeNameHi() {
        return processTypeNameHi;
    }

    public void setProcessTypeNameHi(String processTypeNameHi) {
        this.processTypeNameHi = processTypeNameHi;
    }

    public String getProcessTypeNameRl() {
        return processTypeNameRl;
    }

    public void setProcessTypeNameRl(String processTypeNameRl) {
        this.processTypeNameRl = processTypeNameRl;
    }

    public String getProcessTypeDescription() {
        return processTypeDescription;
    }

    public void setProcessTypeDescription(String processTypeDescription) {
        this.processTypeDescription = processTypeDescription;
    }

    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
    }

    public String getCreatedUri() {
        return createdUri;
    }

    public void setCreatedUri(String createdUri) {
        this.createdUri = createdUri;
    }
}
