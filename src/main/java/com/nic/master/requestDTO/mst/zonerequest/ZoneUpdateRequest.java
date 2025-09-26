package com.nic.master.requestDTO.mst.zonerequest;

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
public class ZoneUpdateRequest {


    @Size(min = 4, max = 20, message = "Zone code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Zone code: only uppercase letters, numbers, hyphens(-) and underscore(_) allowed")
    private String zoneCode;


    @Size(min = 2, max = 100, message = "Zone name must be between 2-100 characters")
    private String zoneNameEn;

    @Size(max = 100, message = "Zone name (Hindi) cannot exceed 100 characters")
    private String zoneNameHi;

    @Size(max = 100, message = "Zone name (Regional) cannot exceed 100 characters")
    private String zoneNameRl;

    @Size(max = 500, message = "Zone description cannot exceed 500 characters")
    private String zoneDescription;

    @Size(max = 50, message = "Wrapper code cannot exceed 50 characters")
    private String wrapperCode;


//    @NotNull(message = "Modified by cannot be null")
//    @NotEmpty(message = "Modified by is required")
//    @Size(min = 2, max = 50, message = "Modified by must be between 2-50 characters")
    private String modifiedBy;

//    @NotNull(message = "Modified IP address cannot be null")
//    @NotEmpty(message = "Modified IP address is required")
//    @Pattern(regexp = "^(?:\\d{1,3}\\.){3}\\d{1,3}$", message = "Invalid IP address format")
    private String modifiedIpAddr;

    @Size(max = 100, message = "MAC address cannot exceed 100 characters")
    private String modifiedMacAddr;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String modifiedRemarks;

    @Size(max = 200, message = "URI cannot exceed 200 characters")
    private String modifiedUri;

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