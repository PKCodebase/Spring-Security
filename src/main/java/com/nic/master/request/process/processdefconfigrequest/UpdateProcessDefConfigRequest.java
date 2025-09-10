package com.nic.master.request.process.processdefconfigrequest;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateProcessDefConfigRequest {
    @NotBlank(message = "ProcessDefConfigGuid is required")
    private String processDefConfigGuid; // primary key

    private String config; // allow updating JSON

    private String modifiedBy;
    private String modifiedIpAddr;
    private String modifiedMacAddr;
    private String modifiedRemarks;
    private String modifiedUri;
}
