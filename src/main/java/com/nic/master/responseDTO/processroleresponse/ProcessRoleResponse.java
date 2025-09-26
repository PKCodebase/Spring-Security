package com.nic.master.responseDTO.processroleresponse;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProcessRoleResponse {

    private String processedRequestRoleGuid;

    private String processDefGuid;

    private String approveProcessedRoleCode;

    private String rejectProcessedRoleCode;

    private String closeProcessedRoleCode;

    private String processUserRoleCode;

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
	public String getProcessedRequestRoleGuid() {
		return processedRequestRoleGuid;
	}
	public void setProcessedRequestRoleGuid(String processedRequestRoleGuid) {
		this.processedRequestRoleGuid = processedRequestRoleGuid;
	}
	public String getProcessDefGuid() {
		return processDefGuid;
	}
	public void setProcessDefGuid(String processDefGuid) {
		this.processDefGuid = processDefGuid;
	}
	public String getApproveProcessedRoleCode() {
		return approveProcessedRoleCode;
	}
	public void setApproveProcessedRoleCode(String approveProcessedRoleCode) {
		this.approveProcessedRoleCode = approveProcessedRoleCode;
	}
	public String getRejectProcessedRoleCode() {
		return rejectProcessedRoleCode;
	}
	public void setRejectProcessedRoleCode(String rejectProcessedRoleCode) {
		this.rejectProcessedRoleCode = rejectProcessedRoleCode;
	}
	public String getCloseProcessedRoleCode() {
		return closeProcessedRoleCode;
	}
	public void setCloseProcessedRoleCode(String closeProcessedRoleCode) {
		this.closeProcessedRoleCode = closeProcessedRoleCode;
	}
	public String getProcessUserRoleCode() {
		return processUserRoleCode;
	}
	public void setProcessUserRoleCode(String processUserRoleCode) {
		this.processUserRoleCode = processUserRoleCode;
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


}
