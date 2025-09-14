package com.nic.master.entity.process;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "process_def",
        schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "process_def_process_def_code_uk", columnNames = "process_def_code"),
                @UniqueConstraint(name = "process_def_process_def_id_uk", columnNames = "process_def_id")
        }
)
@Check(constraints = "char_length(process_def_guid) = 36")
@Check(constraints = "(modified_by IS NOT NULL AND modified_date IS NOT NULL AND modified_ip_addr IS NOT NULL) " +
        "OR (modified_by IS NULL AND modified_date IS NULL AND modified_ip_addr IS NULL)")
@Check(constraints = "modified_date IS NULL OR modified_date >= created_date")
@Data
public class ProcessDef {

    @Id
    @Column(name = "process_def_guid", length = 36, nullable = false, updatable = false)
    private String processDefGuid;

    @Column(name = "process_def_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Long processDefId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "process_type_guid",
            nullable = false,
            foreignKey = @ForeignKey(name = "process_def_process_type_guid_fk")
    )
    private MstProcessType processType;

    @Column(name = "process_def_code", nullable = false, length = 100, unique = true)
    private String processDefCode;

    @Column(name = "process_def_name", nullable = false)
    private String processDefName;

    @OneToOne(mappedBy = "processDef", cascade = CascadeType.ALL, optional = true)
//    @LazyToOne(LazyToOneOption.NO_PROXY)
    private ProcessDefConfig processDefConfig;


    @Column(name = "is_primary_org_applicable")
    private Boolean isPrimaryOrgApplicable;

    @Column(name = "is_wrapper_org_applicable")
    private Boolean isWrapperOrgApplicable;

    @Column(name = "is_org_unit_applicable")
    private Boolean isOrgUnitApplicable;

    @Column(name = "is_service_applicable")
    private Boolean isServiceApplicable;

    @Column(name = "is_cadre_applicable")
    private Boolean isCadreApplicable;

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

    @Column(name = "can_show_parent", nullable = false)
    private Boolean canShowParent = true;

    @Column(name = "can_show_child", nullable = false)
    private Boolean canShowChild = false;

    @Column(name = "can_draft", nullable = false)
    private Boolean canDraft = false;

    public String getProcessDefGuid() {
        return processDefGuid;
    }

    public void setProcessDefGuid(String processDefGuid) {
        this.processDefGuid = processDefGuid;
    }

    public Long getProcessDefId() {
        return processDefId;
    }

    public void setProcessDefId(Long processDefId) {
        this.processDefId = processDefId;
    }

    public MstProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(MstProcessType processType) {
        this.processType = processType;
    }

    public String getProcessDefCode() {
        return processDefCode;
    }

    public void setProcessDefCode(String processDefCode) {
        this.processDefCode = processDefCode;
    }

    public String getProcessDefName() {
        return processDefName;
    }

    public void setProcessDefName(String processDefName) {
        this.processDefName = processDefName;
    }

    public ProcessDefConfig getProcessDefConfig() {
        return processDefConfig;
    }

    public void setProcessDefConfig(ProcessDefConfig processDefConfig) {
        this.processDefConfig = processDefConfig;
    }

    public Boolean getPrimaryOrgApplicable() {
        return isPrimaryOrgApplicable;
    }

    public void setPrimaryOrgApplicable(Boolean primaryOrgApplicable) {
        isPrimaryOrgApplicable = primaryOrgApplicable;
    }

    public Boolean getWrapperOrgApplicable() {
        return isWrapperOrgApplicable;
    }

    public void setWrapperOrgApplicable(Boolean wrapperOrgApplicable) {
        isWrapperOrgApplicable = wrapperOrgApplicable;
    }

    public Boolean getOrgUnitApplicable() {
        return isOrgUnitApplicable;
    }

    public void setOrgUnitApplicable(Boolean orgUnitApplicable) {
        isOrgUnitApplicable = orgUnitApplicable;
    }

    public Boolean getServiceApplicable() {
        return isServiceApplicable;
    }

    public void setServiceApplicable(Boolean serviceApplicable) {
        isServiceApplicable = serviceApplicable;
    }

    public Boolean getCadreApplicable() {
        return isCadreApplicable;
    }

    public void setCadreApplicable(Boolean cadreApplicable) {
        isCadreApplicable = cadreApplicable;
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
    public Boolean getCanShowParent() {
        return canShowParent;
    }

    public void setCanShowParent(Boolean canShowParent) {
        this.canShowParent = canShowParent;
    }

    public Boolean getCanShowChild() {
        return canShowChild;
    }

    public void setCanShowChild(Boolean canShowChild) {
        this.canShowChild = canShowChild;
    }

    public Boolean getCanDraft() {
        return canDraft;
    }

    public void setCanDraft(Boolean canDraft) {
        this.canDraft = canDraft;
    }
}
