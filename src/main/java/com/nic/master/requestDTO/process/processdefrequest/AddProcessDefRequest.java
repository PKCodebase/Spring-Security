package com.nic.master.requestDTO.process.processdefrequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddProcessDefRequest {



    @NotBlank(message = "Process Def Code is required")
    @Pattern(regexp = "^[A-Z0-9\\-_]*$", message = "Only capital letters, numbers, hyphen, underscore allowed")
    private String processDefCode;

    @NotBlank(message = "Process Def Name is required")
    private String processDefName;

    private Boolean isPrimaryOrgApplicable;
    private Boolean isWrapperOrgApplicable;
    private Boolean isOrgUnitApplicable;
    private Boolean isServiceApplicable;
    private Boolean isCadreApplicable;

    private String createdBy;

    private String createdIpAddr;

    private String createdMacAddr;
    private String createdRemarks;
    private String createdUri;

    public String getProcessDefCode() {
        return processDefCode;
    }

    public void setProcessDefCode(String processDefCode) {
        this.processDefCode = processDefCode;
    }

    public String getProcessDefName() {
        return processDefName;
    }

    public void setProcessDefName(String processDefName) {
        this.processDefName = processDefName;
    }

    public Boolean getPrimaryOrgApplicable() {
        return isPrimaryOrgApplicable;
    }

    public void setPrimaryOrgApplicable(Boolean primaryOrgApplicable) {
        isPrimaryOrgApplicable = primaryOrgApplicable;
    }

    public Boolean getWrapperOrgApplicable() {
        return isWrapperOrgApplicable;
    }

    public void setWrapperOrgApplicable(Boolean wrapperOrgApplicable) {
        isWrapperOrgApplicable = wrapperOrgApplicable;
    }

    public Boolean getOrgUnitApplicable() {
        return isOrgUnitApplicable;
    }

    public void setOrgUnitApplicable(Boolean orgUnitApplicable) {
        isOrgUnitApplicable = orgUnitApplicable;
    }

    public Boolean getServiceApplicable() {
        return isServiceApplicable;
    }

    public void setServiceApplicable(Boolean serviceApplicable) {
        isServiceApplicable = serviceApplicable;
    }

    public Boolean getCadreApplicable() {
        return isCadreApplicable;
    }

    public void setCadreApplicable(Boolean cadreApplicable) {
        isCadreApplicable = cadreApplicable;
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

    public String getCreatedMacAddr() {
        return createdMacAddr;
    }

    public void setCreatedMacAddr(String createdMacAddr) {
        this.createdMacAddr = createdMacAddr;
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


