package com.nic.master.response.processdefconfigresponse;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ProcessDefConfigResponse {

    private String processDefConfigGuid;


    private String processDefGuid;

    private Map<String, Object> config;


    private String createdBy;
    private LocalDateTime createdDate;
    private String createdIpAddr;
    private String createdMacAddr;
    private String createdRemarks;
    private String createdUri;

    private String modifiedBy;
    private LocalDateTime modifiedDate;
    private String modifiedIpAddr;
    private String modifiedMacAddr;
    private String modifiedRemarks;
    private String modifiedUri;

    private Boolean isActive;
}
