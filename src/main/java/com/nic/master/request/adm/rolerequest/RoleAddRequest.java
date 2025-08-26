package com.nic.master.request.adm.rolerequest;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleAddRequest {

    @NotBlank(message = "Role code is mandatory")
    @Size(max = 255, message = "Module code cannot exceed 255 characters")
    private String roleCode;

    @NotBlank(message = "Role name is mandatory")
    @Size(max = 255, message = "Module name cannot exceed 255 characters")
    private String roleName;

    private String roleDescription;

    private String createdRemarks;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
    }
}
