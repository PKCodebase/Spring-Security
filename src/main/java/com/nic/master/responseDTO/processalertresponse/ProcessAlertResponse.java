package com.nic.master.responseDTO.processalertresponse;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProcessAlertResponse {

    private String processAlertGuid;

    private Long processAlertId;

    private String processDefGuid; // from processDef entity
    private String actionTypeGuid; // from actionType entity

    private String smsMsg;

    private String smsTemplateId;

    private String smsTemplateName;

    private String emailMsg;

    private String emailTemplateId;

    private String gimsMsg;

    private String gimsTemplateId;

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

	public String getProcessAlertGuid() {
		return processAlertGuid;
	}

	public void setProcessAlertGuid(String processAlertGuid) {
		this.processAlertGuid = processAlertGuid;
	}

	public Long getProcessAlertId() {
		return processAlertId;
	}

	public void setProcessAlertId(Long processAlertId) {
		this.processAlertId = processAlertId;
	}

	public String getProcessDefGuid() {
		return processDefGuid;
	}

	public void setProcessDefGuid(String processDefGuid) {
		this.processDefGuid = processDefGuid;
	}

	public String getActionTypeGuid() {
		return actionTypeGuid;
	}

	public void setActionTypeGuid(String actionTypeGuid) {
		this.actionTypeGuid = actionTypeGuid;
	}

	public String getSmsMsg() {
		return smsMsg;
	}

	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}

	public String getSmsTemplateId() {
		return smsTemplateId;
	}

	public void setSmsTemplateId(String smsTemplateId) {
		this.smsTemplateId = smsTemplateId;
	}

	public String getSmsTemplateName() {
		return smsTemplateName;
	}

	public void setSmsTemplateName(String smsTemplateName) {
		this.smsTemplateName = smsTemplateName;
	}

	public String getEmailMsg() {
		return emailMsg;
	}

	public void setEmailMsg(String emailMsg) {
		this.emailMsg = emailMsg;
	}

	public String getEmailTemplateId() {
		return emailTemplateId;
	}

	public void setEmailTemplateId(String emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}

	public String getGimsMsg() {
		return gimsMsg;
	}

	public void setGimsMsg(String gimsMsg) {
		this.gimsMsg = gimsMsg;
	}

	public String getGimsTemplateId() {
		return gimsTemplateId;
	}

	public void setGimsTemplateId(String gimsTemplateId) {
		this.gimsTemplateId = gimsTemplateId;
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

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
    
    
    
}
