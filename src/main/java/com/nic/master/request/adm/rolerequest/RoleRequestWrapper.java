package com.nic.master.request.adm.rolerequest;

import lombok.Data;

@Data
public class RoleRequestWrapper {

    private String operation;   // ADD, UPDATE, GETALL, GETBYCODE, GETBYGUID
    private String roleGuid;    // For getByGuid / update
    private String roleCode;    // For getByCode
    private RoleAddRequest roleAddRequest;
    private RoleUpdateRequest roleUpdateRequest;


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getRoleGuid() {
        return roleGuid;
    }

    public void setRoleGuid(String roleGuid) {
        this.roleGuid = roleGuid;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public RoleAddRequest getRoleAddRequest() {
        return roleAddRequest;
    }

    public void setRoleAddRequest(RoleAddRequest roleAddRequest) {
        this.roleAddRequest = roleAddRequest;
    }

    public RoleUpdateRequest getRoleUpdateRequest() {
        return roleUpdateRequest;
    }

    public void setRoleUpdateRequest(RoleUpdateRequest roleUpdateRequest) {
        this.roleUpdateRequest = roleUpdateRequest;
    }
}
