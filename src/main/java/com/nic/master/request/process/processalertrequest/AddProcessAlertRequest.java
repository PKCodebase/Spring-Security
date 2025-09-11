package com.nic.master.request.process.processalertrequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddProcessAlertRequest {
//    @NotBlank(message = "ProcessDef GUID is required")
//    @Size(min = 36, max = 36, message = "ProcessDef GUID must be exactly 36 characters (UUID)")
//    private String processDefGuid;
//
//    @NotBlank(message = "ActionType GUID is required")
//    @Size(min = 36, max = 36, message = "ActionType GUID must be exactly 36 characters (UUID)")
//    private String actionTypeGuid;

    private String smsMsg;

    private String smsTemplateId;

    private String smsTemplateName;

    private String emailMsg;

    private String emailTemplateId;

    private String gimsMsg;

    private String gimsTemplateId;

//    @NotBlank(message = "CreatedBy is required")
//    private String createdBy;
//
//    @NotBlank(message = "Created IP address is required")
//    private String createdIpAddr;
//
//    private String createdMacAddr;

    private String createdRemarks;

    private String createdUri;
}
