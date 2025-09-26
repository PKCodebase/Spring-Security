package com.nic.master.responseDTO.wardresponse;

public class WardResponse {

    private Long wardId;
    private String wardGuid;
    private String wardCode;
    private String wardNameEn;
    private String wardNameHi;
    private String wardNameRl;
    private String wardDescription;

    private String orgUnitCode;
    private String createdBy;
    private String createdIpAddr;
    private String createdMacAddr;
    private String createdRemarks;
    private String createdUri;

    private  String modifiedBy;
    private String   modifiedIpAddr;
    private String  modifiedMacAddr;
    private  String modifiedRemarks;
    private String modifiedUri;

    private Boolean isActive=true;

    private String zoneGuid;

    private String zoneNameEn;

    public String getZoneNameEn() {
        return zoneNameEn;
    }

    public void setZoneNameEn(String zoneNameEn) {
        this.zoneNameEn = zoneNameEn;
    }

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public String getWardGuid() {
        return wardGuid;
    }

    public void setWardGuid(String wardGuid) {
        this.wardGuid = wardGuid;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getWardNameEn() {
        return wardNameEn;
    }

    public void setWardNameEn(String wardNameEn) {
        this.wardNameEn = wardNameEn;
    }

    public String getWardNameHi() {
        return wardNameHi;
    }

    public void setWardNameHi(String wardNameHi) {
        this.wardNameHi = wardNameHi;
    }

    public String getWardNameRl() {
        return wardNameRl;
    }

    public void setWardNameRl(String wardNameRl) {
        this.wardNameRl = wardNameRl;
    }

    public String getWardDescription() {
        return wardDescription;
    }

    public void setWardDescription(String wardDescription) {
        this.wardDescription = wardDescription;
    }

    public String getOrgUnitCode() {
        return orgUnitCode;
    }

    public void setOrgUnitCode(String orgUnitCode) {
        this.orgUnitCode = orgUnitCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getZoneGuid() {
        return zoneGuid;
    }

    public void setZoneGuid(String zoneGuid) {
        this.zoneGuid = zoneGuid;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
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
}
