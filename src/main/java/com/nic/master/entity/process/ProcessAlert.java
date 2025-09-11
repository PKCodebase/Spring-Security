package com.nic.master.entity.process;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "process_alert",
        schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "process_alert_process_alert_id_uk", columnNames = "process_alert_id"),
                @UniqueConstraint(name = "process_alert_uk", columnNames = {"process_def_guid", "action_type_guid"})
        }
)
public class ProcessAlert {

    @Id
    @Column(name = "process_alert_guid", length = 36, nullable = false)
    private String processAlertGuid;


    @Column(name = "process_alert_id", nullable = false, unique = true,insertable = false,updatable = false)
    private Long processAlertId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "process_def_guid",
            nullable = false,
            foreignKey = @ForeignKey(name = "process_alert_process_def_guid_fk")
    )
    private ProcessDef processDef;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "action_type_guid",
            nullable = false,
            foreignKey = @ForeignKey(name = "process_alert_action_type_guid_fk")
    )
    private MstActionType mstActionType;

    @Column(name = "sms_msg")
    private String smsMsg;

    @Column(name = "sms_template_id")
    private String smsTemplateId;

    @Column(name = "sms_template_name")
    private String smsTemplateName;

    @Column(name = "email_msg")
    private String emailMsg;

    @Column(name = "email_template_id")
    private String emailTemplateId;

    @Column(name = "gims_msg")
    private String gimsMsg;

    @Column(name = "gims_template_id")
    private String gimsTemplateId;

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

    public String getProcessAlertGuid() {
        return processAlertGuid;
    }

    public void setProcessAlertGuid(String processAlertGuid) {
        this.processAlertGuid = processAlertGuid;
    }

    public Long getProcessAlertId() {
        return processAlertId;
    }

    public void setProcessAlertId(Long processAlertId) {
        this.processAlertId = processAlertId;
    }

    public ProcessDef getProcessDef() {
        return processDef;
    }

    public void setProcessDef(ProcessDef processDef) {
        this.processDef = processDef;
    }

    public MstActionType getMstActionType() {
        return mstActionType;
    }

    public void setMstActionType(MstActionType mstActionType) {
        this.mstActionType = mstActionType;
    }

    public String getSmsMsg() {
        return smsMsg;
    }

    public void setSmsMsg(String smsMsg) {
        this.smsMsg = smsMsg;
    }

    public String getSmsTemplateId() {
        return smsTemplateId;
    }

    public void setSmsTemplateId(String smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
    }

    public String getSmsTemplateName() {
        return smsTemplateName;
    }

    public void setSmsTemplateName(String smsTemplateName) {
        this.smsTemplateName = smsTemplateName;
    }

    public String getEmailMsg() {
        return emailMsg;
    }

    public void setEmailMsg(String emailMsg) {
        this.emailMsg = emailMsg;
    }

    public String getEmailTemplateId() {
        return emailTemplateId;
    }

    public void setEmailTemplateId(String emailTemplateId) {
        this.emailTemplateId = emailTemplateId;
    }

    public String getGimsMsg() {
        return gimsMsg;
    }

    public void setGimsMsg(String gimsMsg) {
        this.gimsMsg = gimsMsg;
    }

    public String getGimsTemplateId() {
        return gimsTemplateId;
    }

    public void setGimsTemplateId(String gimsTemplateId) {
        this.gimsTemplateId = gimsTemplateId;
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
