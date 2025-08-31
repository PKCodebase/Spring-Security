package com.nic.master.entity.adm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "mst_url_type", schema = "adm")
public class MstUrlType {

    @Id
    @Column(name = "url_type_guid", length = 36, nullable = false)
    private String urlTypeGuid;

    @Column(name = "url_type_id", nullable = false,insertable = false,updatable = false)
    private Long urlTypeId;

    @Column(name = "url_type_code", nullable = false)
    private String urlTypeCode;

    @Column(name = "url_type_name", nullable = false)
    private String urlTypeName;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_ip_addr", nullable = false)
    private String createdIpAddr;

    @Column(name = "created_remarks")
    private String createdRemarks;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "modified_ip_addr")
    private String modifiedIpAddr;

    @Column(name = "modified_remarks")
    private String modifiedRemarks;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    public String getUrlTypeGuid() {
        return urlTypeGuid;
    }

    public void setUrlTypeGuid(String urlTypeGuid) {
        this.urlTypeGuid = urlTypeGuid;
    }

    public Long getUrlTypeId() {
        return urlTypeId;
    }

    public void setUrlTypeId(Long urlTypeId) {
        this.urlTypeId = urlTypeId;
    }

    public String getUrlTypeCode() {
        return urlTypeCode;
    }

    public void setUrlTypeCode(String urlTypeCode) {
        this.urlTypeCode = urlTypeCode;
    }

    public String getUrlTypeName() {
        return urlTypeName;
    }

    public void setUrlTypeName(String urlTypeName) {
        this.urlTypeName = urlTypeName;
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

    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
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

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
