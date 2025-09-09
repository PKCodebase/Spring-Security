package com.nic.master.entity.process;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "mst_process_type",
        schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "mst_process_type_process_type_code_uk", columnNames = "process_type_code"),
                @UniqueConstraint(name = "mst_process_type_process_type_id_uk", columnNames = "process_type_id")
        }
)
@Check(constraints = "char_length(process_type_guid) = 36")
@Check(constraints = "(modified_by IS NOT NULL AND modified_date IS NOT NULL AND modified_ip_addr IS NOT NULL) " +
        "OR (modified_by IS NULL AND modified_date IS NULL AND modified_ip_addr IS NULL)")
@Check(constraints = "modified_date IS NULL OR modified_date >= created_date")
@Data
public class MstProcessType {

    @Id
    @Column(name = "process_type_guid", length = 36, nullable = false, updatable = false)
    private String processTypeGuid;

    @Column(name = "process_type_id", nullable = false, unique = true,insertable = false,updatable = false)
    private Long processTypeId;

    @Column(name = "process_type_code", length = 100, nullable = false, unique = true)
    private String processTypeCode;

    @Column(name = "process_type_name_en", nullable = false)
    private String processTypeNameEn;

    @Column(name = "process_type_name_hi")
    private String processTypeNameHi;

    @Column(name = "process_type_name_rl")
    private String processTypeNameRl;

    @Column(name = "process_type_description")
    private String processTypeDescription;

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

    /** 🔹 Lifecycle Hooks */
    @PrePersist
    public void prePersist() {
        if (this.processTypeGuid == null) {
            this.processTypeGuid = UUID.randomUUID().toString();
        }
        if (this.createdDate == null) {
            this.createdDate = LocalDateTime.now();
        }
        if (this.isActive == null) {
            this.isActive = true;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }

    public String getProcessTypeGuid() {
        return processTypeGuid;
    }

    public void setProcessTypeGuid(String processTypeGuid) {
        this.processTypeGuid = processTypeGuid;
    }

    public Long getProcessTypeId() {
        return processTypeId;
    }

    public void setProcessTypeId(Long processTypeId) {
        this.processTypeId = processTypeId;
    }

    public String getProcessTypeCode() {
        return processTypeCode;
    }

    public void setProcessTypeCode(String processTypeCode) {
        this.processTypeCode = processTypeCode;
    }

    public String getProcessTypeNameEn() {
        return processTypeNameEn;
    }

    public void setProcessTypeNameEn(String processTypeNameEn) {
        this.processTypeNameEn = processTypeNameEn;
    }

    public String getProcessTypeNameHi() {
        return processTypeNameHi;
    }

    public void setProcessTypeNameHi(String processTypeNameHi) {
        this.processTypeNameHi = processTypeNameHi;
    }

    public String getProcessTypeNameRl() {
        return processTypeNameRl;
    }

    public void setProcessTypeNameRl(String processTypeNameRl) {
        this.processTypeNameRl = processTypeNameRl;
    }

    public String getProcessTypeDescription() {
        return processTypeDescription;
    }

    public void setProcessTypeDescription(String processTypeDescription) {
        this.processTypeDescription = processTypeDescription;
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

    public Boolean getISActive() {
        return isActive;
    }

    public void setISActive(Boolean active) {
        isActive = active;
    }
}
