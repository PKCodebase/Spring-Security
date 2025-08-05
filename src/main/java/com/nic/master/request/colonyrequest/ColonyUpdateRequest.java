package com.nic.master.request.colonyrequest;

import jakarta.validation.constraints.NotBlank;
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
public class ColonyUpdateRequest {

    @NotNull(message = "Colony code cannot be null")
    @NotEmpty(message = "Colony code is required")
    @Size(min = 2, max = 20, message = "Colony code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_]+$", message = "Colony code: only uppercase letters, numbers and underscore allowed")
    private String colonyCode;

    @NotNull(message = "Colony name cannot be null")
    @NotEmpty(message = "Colony name (English) is required")
    @Size(min = 2, max = 100, message = "Colony name must be between 2-100 characters")
    private String colonyNameEn;

    @Size(max = 100, message = "Colony name (Hindi) cannot exceed 100 characters")
    private String colonyNameHi;

    @Size(max = 100, message = "Colony name (Regional) cannot exceed 100 characters")
    private String colonyNameRl;

    @Size(max = 500, message = "Colony description cannot exceed 500 characters")
    private String colonyDescription;


    @NotNull(message = "Modified by cannot be null")
    @NotEmpty(message = "Modified by is required")
    @Size(min = 2, max = 50, message = "Modified by must be between 2-50 characters")
    private String modifiedBy;

    @NotNull(message = "Modified IP address cannot be null")
    @NotEmpty(message = "Modified IP address is required")
    @Pattern(regexp = "^(?:\\d{1,3}\\.){3}\\d{1,3}$", message = "Invalid IP address format")
    private String modifiedIpAddr;

    @Size(max = 100, message = "MAC address cannot exceed 100 characters")
    private String modifiedMacAddr;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String modifiedRemarks;

    @Size(max = 200, message = "URI cannot exceed 200 characters")
    private String modifiedUri;

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