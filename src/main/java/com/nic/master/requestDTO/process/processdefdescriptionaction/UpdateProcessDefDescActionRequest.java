package com.nic.master.requestDTO.process.processdefdescriptionaction;

import lombok.Data;

@Data
public class UpdateProcessDefDescActionRequest {

    private String processDefDescActionGuid; // to identify which record to update
    private String actionLabel;
    private String modifiedBy;
    private String modifiedIpAddr;
    private String modifiedMacAddr;
    private String modifiedRemarks;
    private String modifiedUri;
    private Boolean isActive;
    private Boolean haveSearchIndividualOption;
    private String actionDesc;
    private Integer actionOrder;
    private String actionStyle;
    private String sectionTypeGuid;
    private Boolean havePrefillData;
    private String prefillQuery;

    // Getters and Setters
    public String getProcessDefDescActionGuid() {
        return processDefDescActionGuid;
    }

    public void setProcessDefDescActionGuid(String processDefDescActionGuid) {
        this.processDefDescActionGuid = processDefDescActionGuid;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedIpAddr() {
        return modifiedIpAddr;
    }

    public void setModifiedIpAddr(String modifiedIpAddr) {
        this.modifiedIpAddr = modifiedIpAddr;
    }

    public String getModifiedMacAddr() {
        return modifiedMacAddr;
    }

    public void setModifiedMacAddr(String modifiedMacAddr) {
        this.modifiedMacAddr = modifiedMacAddr;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Boolean getHaveSearchIndividualOption() {
        return haveSearchIndividualOption;
    }

    public void setHaveSearchIndividualOption(Boolean haveSearchIndividualOption) {
        this.haveSearchIndividualOption = haveSearchIndividualOption;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    public Integer getActionOrder() {
        return actionOrder;
    }

    public void setActionOrder(Integer actionOrder) {
        this.actionOrder = actionOrder;
    }

    public String getActionStyle() {
        return actionStyle;
    }

    public void setActionStyle(String actionStyle) {
        this.actionStyle = actionStyle;
    }

    public String getSectionTypeGuid() {
        return sectionTypeGuid;
    }

    public void setSectionTypeGuid(String sectionTypeGuid) {
        this.sectionTypeGuid = sectionTypeGuid;
    }

    public Boolean getHavePrefillData() {
        return havePrefillData;
    }

    public void setHavePrefillData(Boolean havePrefillData) {
        this.havePrefillData = havePrefillData;
    }

    public String getPrefillQuery() {
        return prefillQuery;
    }

    public void setPrefillQuery(String prefillQuery) {
        this.prefillQuery = prefillQuery;
    }
}
