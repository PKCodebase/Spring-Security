package com.nic.master.responseDTO.refresponse;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RefTemplateResponse {

    private String refTemplateGuid;

    private Long refTemplateId;

    private String processDefGuid;

    private String sectionTypeGuid;

    private String actionTypeGuid;

    private Boolean havePrefillData;

    private String prefillDataResultQuery;

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

    private String refTemplateName;

    private String refTemplateCode;

    private Boolean isQueryRaiseCheck;

    private String queryCheckMessage;

    // Optional FK info
    private String actionTypeName;
    private String processDefCode;
    private String sectionTypeName;
}
