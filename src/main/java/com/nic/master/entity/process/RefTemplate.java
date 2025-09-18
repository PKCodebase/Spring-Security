package com.nic.master.entity.process;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ref_template", schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "ref_template_ref_template_id_uk", columnNames = "ref_template_id"),
                @UniqueConstraint(name = "ref_template_ref_template_code_uk", columnNames = "ref_template_code"),
                @UniqueConstraint(name = "ref_template_uk", columnNames = {"process_def_guid", "section_type_guid", "action_type_guid"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefTemplate {

    @Id
    @Column(name = "ref_template_guid", nullable = false, length = 36, unique = true)
    private String refTemplateGuid;


    @Column(name = "ref_template_id", nullable = false, unique = true, updatable = false, insertable = false)
    private Long refTemplateId;

    @Column(name = "process_def_guid", nullable = false, length = 36)
    private String processDefGuid;

    @Column(name = "section_type_guid", nullable = false, length = 36)
    private String sectionTypeGuid;

    @Column(name = "action_type_guid", nullable = false, length = 36)
    private String actionTypeGuid;

    @Builder.Default
    @Column(name = "have_prefill_data", nullable = false)
    private Boolean havePrefillData = false;

    @Column(name = "prefill_data_result_query")
    private String prefillDataResultQuery;

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

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "ref_template_name", nullable = false)
    private String refTemplateName;

    @Column(name = "ref_template_code", nullable = false,unique = true)
    private String refTemplateCode;

    @Builder.Default
    @Column(name = "is_query_raise_check", nullable = false)
    private Boolean isQueryRaiseCheck = false;

    @Column(name = "query_check_message")
    private String queryCheckMessage;

    // Foreign key mappings
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_type_guid", referencedColumnName = "action_type_guid", insertable = false, updatable = false)
    private MstActionType actionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_def_guid", referencedColumnName = "process_def_guid", insertable = false, updatable = false)
    private ProcessDef processDef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_type_guid", referencedColumnName = "section_type_guid", insertable = false, updatable = false)
    private MstSectionType sectionType;

    public String getRefTemplateGuid() {
        return refTemplateGuid;
    }

    public void setRefTemplateGuid(String refTemplateGuid) {
        this.refTemplateGuid = refTemplateGuid;
    }

    public Long getRefTemplateId() {
        return refTemplateId;
    }

    public void setRefTemplateId(Long refTemplateId) {
        this.refTemplateId = refTemplateId;
    }

    public String getProcessDefGuid() {
        return processDefGuid;
    }

    public void setProcessDefGuid(String processDefGuid) {
        this.processDefGuid = processDefGuid;
    }

    public String getSectionTypeGuid() {
        return sectionTypeGuid;
    }

    public void setSectionTypeGuid(String sectionTypeGuid) {
        this.sectionTypeGuid = sectionTypeGuid;
    }

    public String getActionTypeGuid() {
        return actionTypeGuid;
    }

    public void setActionTypeGuid(String actionTypeGuid) {
        this.actionTypeGuid = actionTypeGuid;
    }

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

    public Boolean getQueryRaiseCheck() {
        return isQueryRaiseCheck;
    }

    public void setQueryRaiseCheck(Boolean queryRaiseCheck) {
        isQueryRaiseCheck = queryRaiseCheck;
    }

    public String getQueryCheckMessage() {
        return queryCheckMessage;
    }

    public void setQueryCheckMessage(String queryCheckMessage) {
        this.queryCheckMessage = queryCheckMessage;
    }

    public MstActionType getActionType() {
        return actionType;
    }

    public void setActionType(MstActionType actionType) {
        this.actionType = actionType;
    }

    public ProcessDef getProcessDef() {
        return processDef;
    }

    public void setProcessDef(ProcessDef processDef) {
        this.processDef = processDef;
    }

    public MstSectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(MstSectionType sectionType) {
        this.sectionType = sectionType;
    }
}
