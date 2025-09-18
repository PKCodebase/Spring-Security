package com.nic.master.request.process.reftemplate;

import lombok.Data;

@Data
public class UpdateRefTemplateRequest {

    private String refTemplateGuid;

    private String processDefGuid;

    private String sectionTypeGuid;

    private String actionTypeGuid;

    private Boolean havePrefillData;

    private String prefillDataResultQuery;

    private String modifiedBy;

    private String modifiedIpAddr;

    private String modifiedMacAddr;

    private String modifiedRemarks;

    private String modifiedUri;

    private Boolean isActive;

    private String refTemplateName;

    private String refTemplateCode;

    private Boolean isQueryRaiseCheck;

    private String queryCheckMessage;
}
