package com.nic.master.entity;


import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "zone", schema = "mst")
public class Zone {

    @Id
    @Column(name = "zone_guid", nullable = false, length = 36)
    private String zoneGuid;

    @Column(name = "zone_id", nullable = false, unique = true,updatable = false,insertable = false)
    private Long zoneId;

    @Column(name = "zone_code", nullable = false,  unique = true)
    private String zoneCode;

    @Column(name = "zone_name_en", nullable = false)
    private String zoneNameEn;

    @Column(name = "zone_name_hi")
    private String zoneNameHi;


    @Column(name = "zone_name_rl")
    private String zoneNameRl;

    @Column(name = "zone_description")
    private String zoneDescription;

    @Column(name = "wrapper_code")
    private String wrapperCode;

    @Column(name = "created_by", nullable = false)
    private String createdBy;


    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;


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

    public String getZoneGuid() {
        return zoneGuid;
    }

    public void setZoneGuid(String zoneGuid) {
        this.zoneGuid = zoneGuid;
    }

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneNameEn() {
        return zoneNameEn;
    }

    public void setZoneNameEn(String zoneNameEn) {
        this.zoneNameEn = zoneNameEn;
    }

    public String getZoneNameHi() {
        return zoneNameHi;
    }

    public void setZoneNameHi(String zoneNameHi) {
        this.zoneNameHi = zoneNameHi;
    }

    public String getZoneNameRl() {
        return zoneNameRl;
    }

    public void setZoneNameRl(String zoneNameRl) {
        this.zoneNameRl = zoneNameRl;
    }

    public String getZoneDescription() {
        return zoneDescription;
    }

    public void setZoneDescription(String zoneDescription) {
        this.zoneDescription = zoneDescription;
    }

    public String getWrapperCode() {
        return wrapperCode;
    }

    public void setWrapperCode(String wrapperCode) {
        this.wrapperCode = wrapperCode;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
