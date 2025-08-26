package com.nic.master.service.admservice;

import com.nic.master.entity.adm.MstRole;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.adm.rolerequest.RoleAddRequest;
import com.nic.master.request.adm.rolerequest.RoleUpdateRequest;

import java.util.List;

public interface MstRoleService {

    StatusParam addRole(RoleAddRequest roleAddRequest);

    List<MstRole> getAllRoles();

    SelectOptionParam getRoleByCode(String roleCode);

    MstRole getRoleByGuid(String roleGuid);

    StatusParam updateRoleByGuid(String roleGuid , RoleUpdateRequest roleUpdateRequest);
}
