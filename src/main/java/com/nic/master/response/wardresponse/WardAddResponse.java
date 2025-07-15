package com.nic.master.response.wardresponse;

import com.nic.master.param.StatusParam;



public class WardAddResponse {

    private String wardGuid;

    private String wardCode;

    private String wardNameEn;

    private String wardNameHi;

    private String wardNameRl;

    private  String wardDescription;

    private String orgUnitCode;

    private String createdBy;

    private String createdIpAddr;

    private String createdMacAddr;

    private  String createdRemarks;

    private String createdUri;

    private Boolean isActive = true ;

    private String zoneGuid;
    
    private StatusParam  status;

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

    public StatusParam getStatus() {
        return status;
    }

    public void setStatus(StatusParam status) {
        this.status = status;
    }
}
