package com.nic.master.requestDTO.mst.colonyrequest;

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
public class ColonyAddRequest {

    @NotNull(message = "Colony code cannot be null")
    @NotEmpty(message = "Colony code is required")
    @Size(min = 4, max = 20, message = "Colony code must be between 4-20 characters")
    @Pattern(regexp = "^[A-Z\\d_]+$", message = "Colony code: only uppercase letters, numbers and underscore allowed")
    private String colonyCode;

    @NotNull(message = "Colony name cannot be null")
    @NotEmpty(message = "Colony name (English) is required")
    @Size(min = 3, max = 30, message = "Colony name must be between 3-30 characters")
    private String colonyNameEn;

    @Size(max = 100, message = "Colony name (Hindi) cannot exceed 100 characters")
    private String colonyNameHi;

    @Size(max = 100, message = "Colony name (Regional) cannot exceed 100 characters")
    private String colonyNameRl;

    @Size(max = 500, message = "Colony description cannot exceed 500 characters")
    private String colonyDescription;

//    @NotNull(message = "Created by cannot be null")
//    @NotEmpty(message = "Created by is required")
//    @Size(min = 4, max = 50, message = "Created by must be between 4-50 characters")
    private String createdBy;

    private String createdIpAddr;

    @Size(max = 100, message = "MAC address cannot exceed 100 characters")
    private String createdMacAddr;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String createdRemarks;

    @Size(max = 200, message = "URI cannot exceed 200 characters")
    private String createdUri;

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
}