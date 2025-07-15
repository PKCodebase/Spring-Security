package com.nic.master.response.documentresponse;

import com.nic.master.param.StatusParam;

public class DocumentAddResponse {

    private String documentGuid;
    private String documentCode;
    private String documentName;
    private String createdBy;
    private String createdIpAddr;
    private String createdRemarks;


    private Boolean isActive = true;

    private StatusParam status;

    public String getDocumentGuid() {
        return documentGuid;
    }

    public void setDocumentGuid(String documentGuid) {
        this.documentGuid = documentGuid;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedIpAddr() {
        return createdIpAddr;
    }

    public void setCreatedIpAddr(String createdIpAddr) {
        this.createdIpAddr = createdIpAddr;
    }

    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public StatusParam getStatus() {
        return status;
    }

    public void setStatus(StatusParam status) {
        this.status = status;
    }
}
