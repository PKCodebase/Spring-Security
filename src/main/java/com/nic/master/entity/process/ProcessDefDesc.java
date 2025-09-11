package com.nic.master.entity.process;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "process_def_desc",
        schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "process_def_desc_process_def_desc_id_uk", columnNames = "process_def_desc_id"),
                @UniqueConstraint(name = "process_def_desc_process_def_level_num_uk", columnNames = {"process_def_guid", "level_num"})
        }
)
@Check(constraints = "action_days < 365")
@Check(constraints = "action_due_date IS NOT NULL OR action_days IS NOT NULL")
@Check(constraints = "(modified_by IS NOT NULL AND modified_date IS NOT NULL AND modified_ip_addr IS NOT NULL) " +
        "OR (modified_by IS NULL AND modified_date IS NULL AND modified_ip_addr IS NULL)")
@Check(constraints = "modified_date IS NULL OR modified_date >= created_date")
@Check(constraints = "char_length(process_def_desc_guid) = 36")
public class ProcessDefDesc {

    @Id
    @Column(name = "process_def_desc_guid", length = 36, nullable = false, updatable = false)
    private String processDefDescGuid;

    @Column(name = "process_def_desc_id", nullable = false, unique = true, updatable = false, insertable = false)
    private Long processDefDescId;

    /** Mapping with ProcessDef */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "process_def_guid",
            nullable = false,
            foreignKey = @ForeignKey(name = "process_def_desc_process_def_guid_fk")
    )
    private ProcessDef processDef;

    @Column(name = "level_num", nullable = false)
    private Integer levelNum;

    @Column(name = "level_desc", nullable = false)
    private String levelDesc;

    @Column(name = "role_code", nullable = false)
    private String roleCode;

    /** Mapping with MstSectionType */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "section_type_guid",
            nullable = false,
            foreignKey = @ForeignKey(name = "process_def_desc_section_type_guid_fk")
    )
    private MstSectionType sectionType;

    @Column(name = "action_due_date")
    private LocalDateTime actionDueDate;

    @Column(name = "action_days")
    private Integer actionDays;

    // Audit Fields
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

    // Flags
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "can_change_request_applicability", nullable = false)
    private Boolean canChangeRequestApplicability = false;

    @Column(name = "can_preview_template", nullable = false)
    private Boolean canPreviewTemplate = false;

    // Hooks for auditing
    @PrePersist
    public void prePersist() {
        if (this.createdDate == null) this.createdDate = LocalDateTime.now();
        if (this.isActive == null) this.isActive = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }

    public String getProcessDefDescGuid() {
        return processDefDescGuid;
    }

    public void setProcessDefDescGuid(String processDefDescGuid) {
        this.processDefDescGuid = processDefDescGuid;
    }

    public Long getProcessDefDescId() {
        return processDefDescId;
    }

    public void setProcessDefDescId(Long processDefDescId) {
        this.processDefDescId = processDefDescId;
    }

    public ProcessDef getProcessDef() {
        return processDef;
    }

    public void setProcessDef(ProcessDef processDef) {
        this.processDef = processDef;
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

    public MstSectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(MstSectionType sectionType) {
        this.sectionType = sectionType;
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
