package com.nic.master.request.adm.rolerequest;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleUpdateRequest {


    @Size(min = 2, max = 100, message = "Role name must be between 2-100 characters")
    private String roleName;

    @Size(min = 4, max = 20, message = "Role code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Zone code: only uppercase letters, numbers, hyphens(-) and underscore(_) allowed")
    private String roleCode;

    private String modifiedBy;

    private String modifiedIpAddr;

    private String modifiedRemarks;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

//    public String getModifiedBy() {
//        return modifiedBy;
//    }
//
//    public void setModifiedBy(String modifiedBy) {
//        this.modifiedBy = modifiedBy;
//    }
//
//    public String getModifiedIpAddr() {
//        return modifiedIpAddr;
//    }
//
//    public void setModifiedIpAddr(String modifiedIpAddr) {
//        this.modifiedIpAddr = modifiedIpAddr;
//    }

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }
}
