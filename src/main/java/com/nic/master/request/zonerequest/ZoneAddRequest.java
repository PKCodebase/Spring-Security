package com.nic.master.request.zonerequest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZoneAddRequest {

    @NotNull(message = "Zone code cannot be null")
    @NotEmpty(message = "Zone code is required")
    @Size(min = 4, max = 20, message = "Zone code must be between 4-20 characters")
    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Zone code: only uppercase letters, numbers,hyphens(-) and underscore(_) allowed")
    private String zoneCode;

    @NotNull(message = "Zone name cannot be null")
    @NotEmpty(message = "Zone name (English) is required")
    @Size(min = 3, max = 30, message = "Zone name must be between 3-30 characters")
    private String zoneNameEn;

    @Size(max = 100, message = "Zone name (Hindi) cannot exceed 100 characters")
    private String zoneNameHi;

    @Size(max = 100, message = "Zone name (Regional) cannot exceed 100 characters")
    private String zoneNameRl;

    @Size(max = 500, message = "Zone description cannot exceed 500 characters")
    private String zoneDescription;

    @Size(max = 50, message = "Wrapper code cannot exceed 50 characters")
    private String wrapperCode;

    @NotNull(message = "Created by cannot be null")
    @NotEmpty(message = "Created by is required")
    @Size(min = 2, max = 50, message = "Created by must be between 2-50 characters")
    private String createdBy;

//    @NotNull(message = "IP address cannot be null")
//    @NotEmpty(message = "IP address is required")
//    @Pattern(regexp = "^(?:\\d{1,3}\\.){3}\\d{1,3}$", message = "Invalid IP address format")
    private String createdIpAddr;

    @Size(max = 100, message = "MAC address cannot exceed 100 characters")
    private String createdMacAddr;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String createdRemarks;

    @Size(max = 200, message = "URI cannot exceed 200 characters")
    private String createdUri;


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
}