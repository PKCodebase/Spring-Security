package com.nic.master.responseDTO.Processdefdescresponse;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProcessDefDescResponse {

    private String processDefDescGuid;

    private String processDefGuid;       // entity se processDef.getProcessDefGuid()
    private Integer levelNum;
    private String levelDesc;
    private String roleCode;
    private String sectionTypeGuid;      // entity se sectionType.getSectionTypeGuid()

    private LocalDateTime actionDueDate;
    private Integer actionDays;

    // Audit fields
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

    // Flags
    private Boolean isActive;
    private Boolean canChangeRequestApplicability;
    private Boolean canPreviewTemplate;

    public String getProcessDefDescGuid() {
        return processDefDescGuid;
    }

    public void setProcessDefDescGuid(String processDefDescGuid) {
        this.processDefDescGuid = processDefDescGuid;
    }

    public String getProcessDefGuid() {
        return processDefGuid;
    }

    public void setProcessDefGuid(String processDefGuid) {
        this.processDefGuid = processDefGuid;
    }

    public Integer getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public String getLevelDesc() {
        return levelDesc;
    }

    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getSectionTypeGuid() {
        return sectionTypeGuid;
    }

    public void setSectionTypeGuid(String sectionTypeGuid) {
        this.sectionTypeGuid = sectionTypeGuid;
    }

    public LocalDateTime getActionDueDate() {
        return actionDueDate;
    }

    public void setActionDueDate(LocalDateTime actionDueDate) {
        this.actionDueDate = actionDueDate;
    }

    public Integer getActionDays() {
        return actionDays;
    }

    public void setActionDays(Integer actionDays) {
        this.actionDays = actionDays;
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

    public Boolean getCanChangeRequestApplicability() {
        return canChangeRequestApplicability;
    }

    public void setCanChangeRequestApplicability(Boolean canChangeRequestApplicability) {
        this.canChangeRequestApplicability = canChangeRequestApplicability;
    }

    public Boolean getCanPreviewTemplate() {
        return canPreviewTemplate;
    }

    public void setCanPreviewTemplate(Boolean canPreviewTemplate) {
        this.canPreviewTemplate = canPreviewTemplate;
    }
}
