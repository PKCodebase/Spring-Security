package com.nic.master.requestDTO.process.processedrequestrole;

import lombok.Data;

@Data
public class UpdateProcessedRoleRequest {
//    @NotBlank(message = "processedRequestRoleGuid is required")
//    @Size(min = 36, max = 36, message = "processedRequestRoleGuid must be 36 characters UUID")
//    private String processedRequestRoleGuid;

    private String approveProcessedRoleCode;

    private String rejectProcessedRoleCode;

    private String closeProcessedRoleCode;

    private String processUserRoleCode;

    private String modifiedBy;


    private String modifiedIpAddr;

    private String modifiedMacAddr;

    private String modifiedRemarks;

    private String modifiedUri;

}
