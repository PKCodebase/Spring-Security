package com.nic.master.service.process;


import com.nic.master.param.StatusParam;
import com.nic.master.request.process.processedrequestrole.AddProcessedRoleRequest;
import com.nic.master.request.process.processedrequestrole.UpdateProcessedRoleRequest;
import com.nic.master.response.processroleresponse.ProcessRoleResponse;

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

    /**
     * Get Process Request Roles by Approve Role Code
     */
    List<ProcessRoleResponse> getProcessRequestRolesByApproveProcessedRoleCode(String approveProcessedRoleCode);

    /**
     * Get Process Request Roles by Reject Role Code
     */
    List<ProcessRoleResponse> getProcessRequestRolesByRejectProcessedRoleCode(String rejectProcessedRoleCode);

    /**
     * Get Process Request Roles by Close Role Code
     */
    List<ProcessRoleResponse> getProcessRequestRolesByCloseProcessedRoleCode(String closeProcessedRoleCode);

    /**
     * Get Process Request Roles by Process User Role Code
     */
    List<ProcessRoleResponse> getProcessRequestRolesByProcessUserRoleCode(String processUserRoleCode);

}
