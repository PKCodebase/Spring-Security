package com.nic.master.request.process.ref;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddRefTemplateRequest {

//    @NotBlank(message = "ProcessDefGuid is required")
//    private String processDefGuid;
//
//    @NotBlank(message = "SectionTypeGuid is required")
//    private String sectionTypeGuid;
//
//    @NotBlank(message = "ActionTypeGuid is required")
//    private String actionTypeGuid;

    private Boolean havePrefillData;

    private String prefillDataResultQuery;

    private String createdBy;

    private String createdIpAddr;

    private String createdMacAddr;

    private String createdRemarks;

    private String createdUri;

    @NotBlank(message = "RefTemplateName is required")
    private String refTemplateName;

    @NotBlank(message = "RefTemplateCode is required")
    private String refTemplateCode;

    private Boolean isQueryRaiseCheck;

    private String queryCheckMessage;
}
