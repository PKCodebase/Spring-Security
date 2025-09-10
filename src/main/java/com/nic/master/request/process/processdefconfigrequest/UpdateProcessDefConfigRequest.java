package com.nic.master.request.process.processdefconfigrequest;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Map;

@Data
public class UpdateProcessDefConfigRequest {
//    @NotBlank(message = "ProcessDefConfigGuid is required")
//    private String processDefConfigGuid; // primary key

    private Map<String,Object> config; // allow updating JSON

    private String modifiedBy;
    private String modifiedIpAddr;
    private String modifiedMacAddr;
    private String modifiedRemarks;
    private String modifiedUri;
}
