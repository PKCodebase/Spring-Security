package com.nic.master.response.processdefdescactionresponse;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProcessDefDescActionResponse {

    private String processDefDescActionGuid;
    private String processDefDescGuid;
    private String sectionTypeGuid;
    private String actionTypeGuid;
    private String actionLabel;
    private String createdBy;
    private LocalDateTime createdDate;
    private String createdIpAddr;
    private String createdMacAddr;
    private String createdRemarks;
    private String createdUri;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
    private String modifiedIpAddr;
    private String modifiedMacAddr;
    private String modifiedRemarks;
    private String modifiedUri;
    private Boolean isActive;
    private Boolean haveSearchIndividualOption;
    private String actionDesc;
    private Integer actionOrder;
    private String actionStyle;

    private Boolean havePrefillData;
    private String prefillQuery;

    // Getters and Setters
    public String getProcessDefDescActionGuid() {
        return processDefDescActionGuid;
    }

    public void setProcessDefDescActionGuid(String processDefDescActionGuid) {
        this.processDefDescActionGuid = processDefDescActionGuid;
    }


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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
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
