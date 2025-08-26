package com.nic.master.entity.mst;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "colony", schema = "mst")
public class Colony {

    @Id
    @Column(name = "colony_guid", length = 36, nullable = false)
    private String colonyGuid;

    @Column(name = "colony_id", nullable = false, insertable = false, updatable = false)
    private Long colonyId;

    @Column(name = "colony_code", length = 100, nullable = false, unique = true)
    private String colonyCode;

    @Column(name = "colony_name_en", nullable = false)
    private String colonyNameEn;

    @Column(name = "colony_name_hi")
    private String colonyNameHi;

    @Column(name = "colony_name_rl")
    private String colonyNameRl;

    @Column(name = "colony_description")
    private String colonyDescription;

//    @Column(name = "created_by", nullable = false)
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "created_ip_addr")
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
    private LocalDate modifiedDate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_guid", nullable = false)
    private Ward ward;

    public String getColonyGuid() {
        return colonyGuid;
    }

    public void setColonyGuid(String colonyGuid) {
        this.colonyGuid = colonyGuid;
    }

    public Long getColonyId() {
        return colonyId;
    }

    public void setColonyId(Long colonyId) {
        this.colonyId = colonyId;
    }

    public String getColonyCode() {
        return colonyCode;
    }

    public void setColonyCode(String colonyCode) {
        this.colonyCode = colonyCode;
    }

    public String getColonyNameEn() {
        return colonyNameEn;
    }

    public void setColonyNameEn(String colonyNameEn) {
        this.colonyNameEn = colonyNameEn;
    }

    public String getColonyNameHi() {
        return colonyNameHi;
    }

    public void setColonyNameHi(String colonyNameHi) {
        this.colonyNameHi = colonyNameHi;
    }

    public String getColonyNameRl() {
        return colonyNameRl;
    }

    public void setColonyNameRl(String colonyNameRl) {
        this.colonyNameRl = colonyNameRl;
    }

    public String getColonyDescription() {
        return colonyDescription;
    }

    public void setColonyDescription(String colonyDescription) {
        this.colonyDescription = colonyDescription;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
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

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
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

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
