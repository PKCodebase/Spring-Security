package com.nic.master.entity.process;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "processed_request_role", schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "processed_request_role_process_def_guid_uk", columnNames = "process_def_guid"),
                @UniqueConstraint(name = "processed_request_role_processed_request_role_id_uk", columnNames = "processed_request_role_id")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessedRequestRole {


    @Id
    @Column(name = "processed_request_role_guid", nullable = false, length = 36, updatable = false)
    private String processedRequestRoleGuid;

    @Column(name = "processed_request_role_id", nullable = false, updatable = false,insertable = false,unique = true)
    private Long processedRequestRoleId;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "process_def_guid",
            referencedColumnName = "process_def_guid",
            foreignKey = @ForeignKey(name = "processed_request_role_process_def_guid_fk"),
            nullable = false)
    private ProcessDef processDef;

    @Column(name = "approve_processed_role_code")
    private String approveProcessedRoleCode;

    @Column(name = "reject_processed_role_code")
    private String rejectProcessedRoleCode;

    @Column(name = "close_processed_role_code")
    private String closeProcessedRoleCode;

    @Column(name = "process_user_role_code")
    private String processUserRoleCode;

    // Audit Fields
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "created_ip_addr", nullable = false)
    private String createdIpAddr;

    @Column(name = "created_mac_addr")
    private String createdMacAddr;

    @Column(name = "created_remarks")
    private String createdRemarks;

    @Column(name = "created_uri")
    private String createdUri;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "modified_ip_addr")
    private String modifiedIpAddr;

    @Column(name = "modified_mac_addr")
    private String modifiedMacAddr;

    @Column(name = "modified_remarks")
    private String modifiedRemarks;

    @Column(name = "modified_uri")
    private String modifiedUri;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    public String getProcessedRequestRoleGuid() {
        return processedRequestRoleGuid;
    }

    public void setProcessedRequestRoleGuid(String processedRequestRoleGuid) {
        this.processedRequestRoleGuid = processedRequestRoleGuid;
    }

    public Long getProcessedRequestRoleId() {
        return processedRequestRoleId;
    }

    public void setProcessedRequestRoleId(Long processedRequestRoleId) {
        this.processedRequestRoleId = processedRequestRoleId;
    }

    public ProcessDef getProcessDef() {
        return processDef;
    }

    public void setProcessDef(ProcessDef processDef) {
        this.processDef = processDef;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
