package com.nic.master.request.mst.wardrequest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WardUpdateRequest {


    @Size(min = 2, max = 20, message = "Ward code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_]+$", message = "Ward code: only uppercase letters, numbers and underscore allowed")
    private String wardCode;

    @Size(min = 2, max = 100, message = "Ward name must be between 2-100 characters")
    private String wardNameEn;

    @Size(max = 100, message = "Ward name (Regional) cannot exceed 100 characters")
    private String wardNameRl;

    @Size(max = 100, message = "Ward name (Hindi) cannot exceed 100 characters")
    private String wardNameHi;

    @Size(max = 500, message = "Ward description cannot exceed 500 characters")
    private String wardDescription;

    @Size(max = 50, message = "Organization unit code cannot exceed 50 characters")
    private String orgUnitCode;

//    @NotNull(message = "Modified by cannot be null")
//    @NotEmpty(message = "Modified by is required")
    @Size(min = 2, max = 50, message = "Modified by must be between 2-50 characters")
    private String modifiedBy;

    private String modifiedIpAddr;

//    @Size(max = 100, message = "MAC address cannot exceed 100 characters")
    private String modifiedMacAddr;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String modifiedRemarks;

    @Size(max = 200, message = "URI cannot exceed 200 characters")
    private String modifiedUri;

    // Getters and Setters
    public String getWardCode() { return wardCode; }
    public void setWardCode(String wardCode) { this.wardCode = wardCode; }

    public String getWardNameEn() { return wardNameEn; }
    public void setWardNameEn(String wardNameEn) { this.wardNameEn = wardNameEn; }

    public String getWardNameRl() { return wardNameRl; }
    public void setWardNameRl(String wardNameRl) { this.wardNameRl = wardNameRl; }

    public String getWardNameHi() { return wardNameHi; }
    public void setWardNameHi(String wardNameHi) { this.wardNameHi = wardNameHi; }

    public String getWardDescription() { return wardDescription; }
    public void setWardDescription(String wardDescription) { this.wardDescription = wardDescription; }

    public String getOrgUnitCode() { return orgUnitCode; }
    public void setOrgUnitCode(String orgUnitCode) { this.orgUnitCode = orgUnitCode; }

    public String getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; }

    public String getModifiedIpAddr() { return modifiedIpAddr; }
    public void setModifiedIpAddr(String modifiedIpAddr) { this.modifiedIpAddr = modifiedIpAddr; }

    public String getModifiedMacAddr() { return modifiedMacAddr; }
    public void setModifiedMacAddr(String modifiedMacAddr) { this.modifiedMacAddr = modifiedMacAddr; }

    public String getModifiedRemarks() { return modifiedRemarks; }
    public void setModifiedRemarks(String modifiedRemarks) { this.modifiedRemarks = modifiedRemarks; }

    public String getModifiedUri() { return modifiedUri; }
    public void setModifiedUri(String modifiedUri) { this.modifiedUri = modifiedUri; }
}