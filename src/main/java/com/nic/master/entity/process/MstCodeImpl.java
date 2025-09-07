package com.nic.master.entity.process;


import com.nic.master.enums.ImplType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "mst_code_impl", schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "mst_code_impl_code_impl_id_uk", columnNames = "code_impl_id"),
                @UniqueConstraint(name = "mst_code_impl_uk", columnNames = {"qualified_class_name", "impl_type"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstCodeImpl {

    @Column(name = "code_impl_id", nullable = false, updatable = false, insertable = false)
    private Long codeImplId;

    @Id
    @Column(name = "code_impl_guid", nullable = false, unique = true, updatable = false, length = 36)
    private String codeImplGuid;

    @Column(name = "qualified_class_name", nullable = false, columnDefinition = "TEXT")
    private String qualifiedClassName;

    @Enumerated(EnumType.STRING)
    @Column(name = "impl_type", nullable = false, length = 20)
    private ImplType implType;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false, updatable = false)
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
    private Boolean isActive;

    // 🔹 PrePersist hook to mimic DB defaults
    @PrePersist
    public void prePersist() {
        if (codeImplGuid == null) {
            codeImplGuid =UUID.randomUUID().toString();
        }
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
        if (isActive == null) {
            isActive = true;
        }
    }

    // 🔹 PreUpdate hook to enforce modified constraints
    @PreUpdate
    public void preUpdate() {
        if (modifiedBy != null) {
            if (modifiedDate == null || modifiedIpAddr == null) {
                throw new IllegalStateException("modifiedDate and modifiedIpAddr must be set when modifiedBy is provided");
            }
        } else {
            if (modifiedDate != null || modifiedIpAddr != null) {
                throw new IllegalStateException("modifiedBy must be set if modifiedDate or modifiedIpAddr is provided");
            }
        }

        if (modifiedDate != null && createdDate != null && modifiedDate.isBefore(createdDate)) {
            throw new IllegalStateException("modifiedDate cannot be before createdDate");
        }
    }

    public Long getCodeImplId() {
        return codeImplId;
    }

    public void setCodeImplId(Long codeImplId) {
        this.codeImplId = codeImplId;
    }

    public String getCodeImplGuid() {
        return codeImplGuid;
    }

    public void setCodeImplGuid(String codeImplGuid) {
        this.codeImplGuid = codeImplGuid;
    }

    public String getQualifiedClassName() {
        return qualifiedClassName;
    }

    public void setQualifiedClassName(String qualifiedClassName) {
        this.qualifiedClassName = qualifiedClassName;
    }

    public ImplType getImplType() {
        return implType;
    }

    public void setImplType(ImplType implType) {
        this.implType = implType;
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
