package com.nic.master.request.process.processdefconfigrequest;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddProcessDefConfigRequest {
    @NotBlank(message = "ProcessDefGuid is required")
    private String processDefGuid;

    @NotBlank(message = "Config JSON is required")
    private String config; // JSON string

    @NotBlank(message = "CreatedBy is required")
    private String createdBy;

    @NotBlank(message = "CreatedIpAddr is required")
    private String createdIpAddr;

    private String createdMacAddr;
    private String createdRemarks;
    private String createdUri;
}
