package com.nic.master.request.process.processedrequestrole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProcessedRoleRequest {
    @NotBlank(message = "processedRequestRoleGuid is required")
    @Size(min = 36, max = 36, message = "processedRequestRoleGuid must be 36 characters UUID")
    private String processedRequestRoleGuid;

    private String approveProcessedRoleCode;

    private String rejectProcessedRoleCode;

    private String closeProcessedRoleCode;

    private String processUserRoleCode;

    @NotBlank(message = "modifiedBy is required")
    private String modifiedBy;

    @NotBlank(message = "modifiedIpAddr is required")
    private String modifiedIpAddr;

    private String modifiedMacAddr;

    private String modifiedRemarks;

    private String modifiedUri;

    @NotNull(message = "isActive flag must be provided")
    private Boolean isActive;
}
