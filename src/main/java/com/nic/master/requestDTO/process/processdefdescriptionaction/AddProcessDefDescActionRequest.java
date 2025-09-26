package com.nic.master.requestDTO.process.processdefdescriptionaction;

import lombok.Data;

@Data
public class AddProcessDefDescActionRequest {

    private String processDefDescGuid;
    private String actionTypeGuid;
    private String actionLabel;
    private String createdBy;
    private String createdIpAddr;
    private String createdMacAddr;
    private String createdRemarks;
    private String createdUri;
    private Boolean isActive;
    private Boolean haveSearchIndividualOption;
    private String actionDesc;
    private Integer actionOrder;
    private String actionStyle;
    private String sectionTypeGuid;
    private Boolean havePrefillData;
    private String prefillQuery;

    // Getters and Setters
    public String getProcessDefDescGuid() {
        return processDefDescGuid;
    }

    public void setProcessDefDescGuid(String processDefDescGuid) {
        this.processDefDescGuid = processDefDescGuid;
    }

    public String getActionTypeGuid() {
        return actionTypeGuid;
    }

    public void setActionTypeGuid(String actionTypeGuid) {
        this.actionTypeGuid = actionTypeGuid;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
