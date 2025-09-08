package com.nic.master.entity.process;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mst_action_type", schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "mst_action_type_action_type_code_uk", columnNames = "action_type_code"),
                @UniqueConstraint(name = "mst_action_type_action_type_id_uk", columnNames = "action_type_id")
        })
public class MstActionType {

    @Id
    @Column(name = "action_type_guid", length = 36, nullable = false, updatable = false)
    private String actionTypeGuid;


    @Column(name = "action_type_id", nullable = false, unique = true,updatable = false,insertable = false)
    private Long actionTypeId;

    @Column(name = "action_type_code", length = 100, nullable = false, unique = true)
    private String actionTypeCode;


    @Column(name = "action_type_name", nullable = false)
    private String actionTypeName;

    @Column(name = "default_label", columnDefinition = "text", nullable = false)
    private String defaultLabel;


    @Column(name = "is_process_specific", nullable = false)
    private Boolean isProcessSpecific = true;

    @Column(name = "created_by", nullable = false)
    private String createdBy;


    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

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


    @Column(name = "action_order", nullable = false)
    private Integer actionOrder = 1;

    @PrePersist
    public void prePersist() {
        if (this.createdDate == null) {
            this.createdDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void validateModificationFields() {
        // Rule: If modifiedBy is set, then modifiedDate and modifiedIpAddr must also be set
        if (this.modifiedBy != null) {
            if (this.modifiedDate == null || this.modifiedIpAddr == null) {
                throw new IllegalStateException("If modifiedBy is set, then modifiedDate and modifiedIpAddr must also be set.");
            }
        } else {
            // Rule: If modifiedBy is null, both modifiedDate and modifiedIpAddr should also be null
            if (this.modifiedDate != null || this.modifiedIpAddr != null) {
                throw new IllegalStateException("modifiedDate and modifiedIpAddr must be null when modifiedBy is null.");
            }
        }

        // Rule: modifiedDate >= createdDate
        if (this.modifiedDate != null && this.createdDate != null) {
            if (this.modifiedDate.isBefore(this.createdDate)) {
                throw new IllegalStateException("modifiedDate cannot be earlier than createdDate.");
            }
        }
    }


    public String getActionTypeGuid() {
        return actionTypeGuid;
    }

    public void setActionTypeGuid(String actionTypeGuid) {
        this.actionTypeGuid = actionTypeGuid;
    }

    public Long getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(Long actionTypeId) {
        this.actionTypeId = actionTypeId;
    }

    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public String getActionTypeName() {
        return actionTypeName;
    }

    public void setActionTypeName(String actionTypeName) {
        this.actionTypeName = actionTypeName;
    }

    public String getDefaultLabel() {
        return defaultLabel;
    }

    public void setDefaultLabel(String defaultLabel) {
        this.defaultLabel = defaultLabel;
    }

    public Boolean getIsProcessSpecific() {
        return isProcessSpecific;
    }

    public void setIsProcessSpecific(Boolean isProcessSpecific) {
        this.isProcessSpecific = isProcessSpecific;
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

    public Integer getActionOrder() {
        return actionOrder;
    }

    public void setActionOrder(Integer actionOrder) {
        this.actionOrder = actionOrder;
    }
}

