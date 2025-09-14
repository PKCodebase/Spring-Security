package com.nic.master.response.processroleresponse;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProcessRoleResponse {

    private String processedRequestRoleGuid;

    private String processDefGuid;

    private String approveProcessedRoleCode;

    private String rejectProcessedRoleCode;

    private String closeProcessedRoleCode;

    private String processUserRoleCode;

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

}
