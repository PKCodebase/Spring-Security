package com.nic.master.response.zoneresponse;

import com.nic.master.param.StatusParam;


public class ZoneAddResponse {
    private  String zoneGuid;
    private String zoneCode;
    private String zoneNameEn;
    private  String zoneNameHi;
    private String zoneNameRl;
    private String zoneDescription;
    private String wrapperCode;
    private String createdBy;
    private String createdIpAddr;
    private String createdMacAddr;
    private String createdRemarks;
    private String createdUri;
    private Boolean isActive = true;
	private StatusParam status;

    public String getZoneGuid() {
        return zoneGuid;
    }

    public void setZoneGuid(String zoneGuid) {
        this.zoneGuid = zoneGuid;
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

    public StatusParam getStatus() {
        return status;
    }

    public void setStatus(StatusParam status) {
        this.status = status;
    }
}
