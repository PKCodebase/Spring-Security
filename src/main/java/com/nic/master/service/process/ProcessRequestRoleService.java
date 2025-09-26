package com.nic.master.service.process;


import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.processedrequestrole.AddProcessedRoleRequest;
import com.nic.master.requestDTO.process.processedrequestrole.UpdateProcessedRoleRequest;
import com.nic.master.responseDTO.processroleresponse.ProcessRoleResponse;

import java.util.List;

public interface ProcessRequestRoleService {

    /**
     * Add a new Process Request Role
     */
    StatusParam addProcessRequestRole(String processDefGuid, AddProcessedRoleRequest addProcessedRoleRequest);

    /**
     * Fetch all Process Request Roles
     */
    List<ProcessRoleResponse> getAllProcessRequestRoles();

    /**
     * Fetch Process Request Role by Guid
     */
    ProcessRoleResponse getProcessedRequestRoleByGuid(String processedRequestRoleGuid);

    /**
     * Update Process Request Role
     */
    StatusParam updateProcessRequestRole(String processDefGuid, String processedRequestRoleGuid, UpdateProcessedRoleRequest updateProcessedRoleRequest);



}
