package com.nic.master.entity.process;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "process_def_desc_action", schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "process_def_desc_action_process_def_desc_action_id_uk", columnNames = "process_def_desc_action_id"),
                @UniqueConstraint(name = "process_def_desc_action_process_type_process_group_uk", columnNames = {"process_def_desc_guid", "action_type_guid"})
        })
public class ProcessDefDescAction {

    @Id
    @Column(name = "process_def_desc_action_guid", length = 36, nullable = false)
    private String processDefDescActionGuid;

    @Column(name = "process_def_desc_action_id", nullable = false, unique = true, updatable = false, insertable = false)
    private Long processDefDescActionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_def_desc_guid", nullable = false,
            foreignKey = @ForeignKey(name = "process_def_desc_action_process_def_desc_guid_fk"))
    private ProcessDefDesc processDefDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_type_guid", nullable = false,
            foreignKey = @ForeignKey(name = "process_def_desc_action_action_type_guid_fk"))
    private MstActionType actionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_type_guid",
            foreignKey = @ForeignKey(name = "process_def_desc_action_section_type_guid_fk"))
    private MstSectionType sectionType;

    @Column(name = "action_label", nullable = false, columnDefinition = "text")
    private String actionLabel;

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

    @Column(name = "have_search_individual_option", nullable = false)
    private Boolean haveSearchIndividualOption = false;

    @Column(name = "action_desc")
    private String actionDesc;

    @Column(name = "action_order", nullable = false)
    private Integer actionOrder = 1;

    @Column(name = "action_style")
    private String actionStyle;



    @Column(name = "have_prefill_data", nullable = false)
    private Boolean havePrefillData = false;

    @Column(name = "prefill_query")
    private String prefillQuery;

    // Getters and setters
    public String getProcessDefDescActionGuid() {
        return processDefDescActionGuid;
    }

    public void setProcessDefDescActionGuid(String processDefDescActionGuid) {
        this.processDefDescActionGuid = processDefDescActionGuid;
    }

    public Long getProcessDefDescActionId() {
        return processDefDescActionId;
    }

    public void setProcessDefDescActionId(Long processDefDescActionId) {
        this.processDefDescActionId = processDefDescActionId;
    }

    public ProcessDefDesc getProcessDefDesc() {
        return processDefDesc;
    }

    public void setProcessDefDesc(ProcessDefDesc processDefDesc) {
        this.processDefDesc = processDefDesc;
    }

    public MstActionType getActionType() {
        return actionType;
    }

    public void setActionType(MstActionType actionType) {
        this.actionType = actionType;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
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

    public Boolean getHaveSearchIndividualOption() {
        return haveSearchIndividualOption;
    }

    public void setHaveSearchIndividualOption(Boolean haveSearchIndividualOption) {
        this.haveSearchIndividualOption = haveSearchIndividualOption;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    public Integer getActionOrder() {
        return actionOrder;
    }

    public void setActionOrder(Integer actionOrder) {
        this.actionOrder = actionOrder;
    }

    public String getActionStyle() {
        return actionStyle;
    }

    public void setActionStyle(String actionStyle) {
        this.actionStyle = actionStyle;
    }

    public MstSectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(MstSectionType sectionType) {
        this.sectionType = sectionType;
    }

    public Boolean getHavePrefillData() {
        return havePrefillData;
    }

    public void setHavePrefillData(Boolean havePrefillData) {
        this.havePrefillData = havePrefillData;
    }

    public String getPrefillQuery() {
        return prefillQuery;
    }

    public void setPrefillQuery(String prefillQuery) {
        this.prefillQuery = prefillQuery;
    }
}
