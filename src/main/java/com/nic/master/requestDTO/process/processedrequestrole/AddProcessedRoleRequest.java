package com.nic.master.requestDTO.process.processedrequestrole;

import lombok.Data;

@Data
public class AddProcessedRoleRequest {
//    @NotBlank(message = "processDefGuid is required")
//    @Size(min = 36, max = 36, message = "processDefGuid must be 36 characters UUID")
//    private String processDefGuid;

    private String approveProcessedRoleCode;

    private String rejectProcessedRoleCode;

    private String closeProcessedRoleCode;

    private String processUserRoleCode;

//    @NotBlank(message = "createdBy is required")
//    private String createdBy;
//
//    @NotBlank(message = "createdIpAddr is required")
//    private String createdIpAddr;
//
//    private String createdMacAddr;

    private String createdRemarks;

    private String createdUri;
}
