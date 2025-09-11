package com.nic.master.response.processalertresponse;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProcessAlertResponse {

    private String processAlertGuid;

    private Long processAlertId;

    private String processDefGuid; // from processDef entity
    private String actionTypeGuid; // from actionType entity

    private String smsMsg;

    private String smsTemplateId;

    private String smsTemplateName;

    private String emailMsg;

    private String emailTemplateId;

    private String gimsMsg;

    private String gimsTemplateId;

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
