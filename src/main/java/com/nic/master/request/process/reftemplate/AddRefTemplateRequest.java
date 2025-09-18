package com.nic.master.request.process.reftemplate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddRefTemplateRequest {

//    @NotBlank(message = "ProcessDefGuid is required")
//    private String processDefGuid;
//
//    @NotBlank(message = "SectionTypeGuid is required")
//    private String sectionTypeGuid;
//
//    @NotBlank(message = "ActionTypeGuid is required")
//    private String actionTypeGuid;

    private Boolean havePrefillData;

    private String prefillDataResultQuery;

    private String createdBy;

    private String createdIpAddr;

    private String createdMacAddr;

    private String createdRemarks;

    private String createdUri;

    @NotBlank(message = "RefTemplateName is required")
    private String refTemplateName;

    @NotBlank(message = "RefTemplateCode is required")
    private String refTemplateCode;

    private Boolean isQueryRaiseCheck;

    private String queryCheckMessage;

	public Boolean getHavePrefillData() {
		return havePrefillData;
	}

	public void setHavePrefillData(Boolean havePrefillData) {
		this.havePrefillData = havePrefillData;
	}

	public String getPrefillDataResultQuery() {
		return prefillDataResultQuery;
	}

	public void setPrefillDataResultQuery(String prefillDataResultQuery) {
		this.prefillDataResultQuery = prefillDataResultQuery;
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

	public String getRefTemplateName() {
		return refTemplateName;
	}

	public void setRefTemplateName(String refTemplateName) {
		this.refTemplateName = refTemplateName;
	}

	public String getRefTemplateCode() {
		return refTemplateCode;
	}

	public void setRefTemplateCode(String refTemplateCode) {
		this.refTemplateCode = refTemplateCode;
	}

	public Boolean getIsQueryRaiseCheck() {
		return isQueryRaiseCheck;
	}

	public void setIsQueryRaiseCheck(Boolean isQueryRaiseCheck) {
		this.isQueryRaiseCheck = isQueryRaiseCheck;
	}

	public String getQueryCheckMessage() {
		return queryCheckMessage;
	}

	public void setQueryCheckMessage(String queryCheckMessage) {
		this.queryCheckMessage = queryCheckMessage;
	}


}
