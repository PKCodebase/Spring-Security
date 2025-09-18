package com.nic.master.entity.process;


import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
@Table(
        name = "process_def_config",
        schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "process_def_config_process_def_config_id_uk", columnNames = "process_def_config_id"),
                @UniqueConstraint(name = "process_def_process_def_guid_uk", columnNames = "process_def_guid")
        }
)
@Check(constraints = "char_length(process_def_config_guid) = 36")
@Check(constraints =
        "(modified_by IS NOT NULL AND modified_date IS NOT NULL AND modified_ip_addr IS NOT NULL) " +
                "OR (modified_by IS NULL AND modified_date IS NULL AND modified_ip_addr IS NULL)")
@Check(constraints = "modified_date IS NULL OR modified_date >= created_date")
public class ProcessDefConfig {

    @Id
    @Column(name = "process_def_config_guid", length = 36, nullable = false, updatable = false)
    private String processDefConfigGuid;

    @Column(name = "process_def_config_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Long processDefConfigId;

    // ✅ FK to ProcessDef
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "process_def_guid",
            nullable = false,
            foreignKey = @ForeignKey(name = "process_def_config_process_def_guid_fk")
    )
    private ProcessDef processDef;

    @Type(JsonType.class)
    @Column(name = "config", nullable = false, columnDefinition = "jsonb")
    private Map<String,Object> config;

    // ✅ Audit fields
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

    public String getProcessDefConfigGuid() {
        return processDefConfigGuid;
    }

    public void setProcessDefConfigGuid(String processDefConfigGuid) {
        this.processDefConfigGuid = processDefConfigGuid;
    }

    public Long getProcessDefConfigId() {
        return processDefConfigId;
    }

    public void setProcessDefConfigId(Long processDefConfigId) {
        this.processDefConfigId = processDefConfigId;
    }

    public ProcessDef getProcessDef() {
        return processDef;
    }

    public void setProcessDef(ProcessDef processDef) {
        this.processDef = processDef;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
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
