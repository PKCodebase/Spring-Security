package com.nic.master.entity.adm;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mst_role", schema = "adm")
public class MstRole {

    @Id
    @Column(name = "role_guid", nullable = false, length = 36)
    private String roleGuid;


    @Column(name = "role_id",nullable = false, unique = true,updatable = false,insertable = false)
    private Long roleId;

    @Column(name = "role_code", nullable = false)
    private String roleCode;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_ip_addr", nullable = false)
    private String createdIpAddr;

    @Column(name = "created_remarks")
    private String createdRemarks;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "modified_ip_addr")
    private String modifiedIpAddr;

    @Column(name = "modified_remarks")
    private String modifiedRemarks;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    // --------- Getters and Setters --------- //
    public String getRoleGuid() { return roleGuid; }
    public void setRoleGuid(String roleGuid) { this.roleGuid = roleGuid; }

    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public String getRoleCode() { return roleCode; }
    public void setRoleCode(String roleCode) { this.roleCode = roleCode; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getRoleDescription() { return roleDescription; }
    public void setRoleDescription(String roleDescription) { this.roleDescription = roleDescription; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public String getCreatedIpAddr() { return createdIpAddr; }
    public void setCreatedIpAddr(String createdIpAddr) { this.createdIpAddr = createdIpAddr; }

    public String getCreatedRemarks() { return createdRemarks; }
    public void setCreatedRemarks(String createdRemarks) { this.createdRemarks = createdRemarks; }

    public String getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; }

    public LocalDateTime getModifiedDate() { return modifiedDate; }
    public void setModifiedDate(LocalDateTime modifiedDate) { this.modifiedDate = modifiedDate; }

    public String getModifiedIpAddr() { return modifiedIpAddr; }
    public void setModifiedIpAddr(String modifiedIpAddr) { this.modifiedIpAddr = modifiedIpAddr; }

    public String getModifiedRemarks() { return modifiedRemarks; }
    public void setModifiedRemarks(String modifiedRemarks) { this.modifiedRemarks = modifiedRemarks; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean active) { isActive = active; }
}
