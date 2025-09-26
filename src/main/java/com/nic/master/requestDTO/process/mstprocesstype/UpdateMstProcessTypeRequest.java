package com.nic.master.requestDTO.process.mstprocesstype;

import lombok.Data;

@Data
public class UpdateMstProcessTypeRequest {

    private String processTypeCode;

    private String processTypeNameEn;

    private String processTypeNameHi;

    private String processTypeNameRl;

    private String processTypeDescription;

    private String modifiedRemarks;

    private String modifiedUri;

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

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }

    public String getModifiedUri() {
        return modifiedUri;
    }

    public void setModifiedUri(String modifiedUri) {
        this.modifiedUri = modifiedUri;
    }
}
