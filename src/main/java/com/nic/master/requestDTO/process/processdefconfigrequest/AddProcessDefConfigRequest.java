package com.nic.master.requestDTO.process.processdefconfigrequest;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Map;

@Data
public class AddProcessDefConfigRequest {
//    @NotBlank(message = "ProcessDefGuid is required")
//    private String processDefGuid;

    @NotEmpty(message = "Config JSON cannot be empty")
    private Map<String,Object> config;

    private String createdRemarks;
    private String createdUri;
}
