package com.nic.master.request.mst.wardrequest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WardAddRequest {

//    @NotNull(message = "Ward code cannot be null")
//    @NotEmpty(message = "Ward code is required")
    @Size(min = 2, max = 20, message = "Ward code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_]+$", message = "Ward code: only uppercase letters, numbers and underscore allowed")
    private String wardCode;

//    @NotNull(message = "Ward name cannot be null")
//    @NotEmpty(message = "Ward name (English) is required")
    @Size(min = 2, max = 100, message = "Ward name must be between 2-100 characters")
    private String wardNameEn;

    @Size(max = 100, message = "Ward name (Hindi) cannot exceed 100 characters")
    private String wardNameHi;

    @Size(max = 100, message = "Ward name (Regional) cannot exceed 100 characters")
    private String wardNameRl;

    @Size(max = 500, message = "Ward description cannot exceed 500 characters")
    private String wardDescription;

    @Size(max = 50, message = "Organization unit code cannot exceed 50 characters")
    private String orgUnitCode;

//    @NotNull(message = "Created by cannot be null")
//    @NotEmpty(message = "Created by is required")
//    @Size(min = 2, max = 50, message = "Created by must be between 2-50 characters")
    private String createdBy;

    private String createdIpAddr;

    @Size(max = 100, message = "MAC address cannot exceed 100 characters")
    private String createdMacAddr;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String createdRemarks;

    @Size(max = 200, message = "URI cannot exceed 200 characters")
    private String createdUri;

    // Getters and Setters
    public String getWardCode() { return wardCode; }
    public void setWardCode(String wardCode) { this.wardCode = wardCode; }

    public String getWardNameEn() { return wardNameEn; }
    public void setWardNameEn(String wardNameEn) { this.wardNameEn = wardNameEn; }

    public String getWardNameHi() { return wardNameHi; }
    public void setWardNameHi(String wardNameHi) { this.wardNameHi = wardNameHi; }

    public String getWardNameRl() { return wardNameRl; }
    public void setWardNameRl(String wardNameRl) { this.wardNameRl = wardNameRl; }

    public String getWardDescription() { return wardDescription; }
    public void setWardDescription(String wardDescription) { this.wardDescription = wardDescription; }

    public String getOrgUnitCode() { return orgUnitCode; }
    public void setOrgUnitCode(String orgUnitCode) { this.orgUnitCode = orgUnitCode; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getCreatedIpAddr() { return createdIpAddr; }
    public void setCreatedIpAddr(String createdIpAddr) { this.createdIpAddr = createdIpAddr; }

    public String getCreatedMacAddr() { return createdMacAddr; }
    public void setCreatedMacAddr(String createdMacAddr) { this.createdMacAddr = createdMacAddr; }

    public String getCreatedRemarks() { return createdRemarks; }
    public void setCreatedRemarks(String createdRemarks) { this.createdRemarks = createdRemarks; }

    public String getCreatedUri() { return createdUri; }
    public void setCreatedUri(String createdUri) { this.createdUri = createdUri; }
}