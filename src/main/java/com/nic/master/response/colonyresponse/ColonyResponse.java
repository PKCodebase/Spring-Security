package com.nic.master.response.colonyresponse;


public class ColonyResponse {

    private String colonyGuid;

    private Long colonyId;

    private String colonyCode;

    private String colonyNameEn;

    private String colonyNameHi;

    private String colonyNameRl;

    private  String colonyDescription;

    private String createdBy;

    private String createdIpAddr;

    private String createdMacAddr;

    private  String createdRemarks;

    private String modifiedBy;
    private String modifiedIpAddr;
    private String modifiedMacAddr;
    private String modifiedRemarks;
    private String modifiedUri;




    private Boolean isActive = true;

    private  String wardGuid;

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getWardGuid() {
        return wardGuid;
    }

    public void setWardGuid(String wardGuid) {
        this.wardGuid = wardGuid;
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
