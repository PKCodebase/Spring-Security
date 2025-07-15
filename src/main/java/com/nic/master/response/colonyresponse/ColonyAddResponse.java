package com.nic.master.response.colonyresponse;

import com.nic.master.param.StatusParam;


public class ColonyAddResponse {

    private String colonyGuid;

    private  String colonyCode;

    private String colonyNameEn;

    private String colonyNameHi;

    private String colonyNameRl;

    private String colonyDescription;

    private String createdBy;

    private String createdIpAddr;

    private String createdMacAddr;

    private String createdRemarks;

    private  String createdUri;

    private Boolean isActive = true;

    private String wardGuid;

    private StatusParam status;

    public String getColonyGuid() {
        return colonyGuid;
    }

    public void setColonyGuid(String colonyGuid) {
        this.colonyGuid = colonyGuid;
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

    public String getWardGuid() {
        return wardGuid;
    }

    public void setWardGuid(String wardGuid) {
        this.wardGuid = wardGuid;
    }

    public StatusParam getStatus() {
        return status;
    }

    public void setStatus(StatusParam status) {
        this.status = status;
    }
}
