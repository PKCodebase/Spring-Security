package com.nic.master.requestDTO.process.processalertrequest;

import lombok.Data;

@Data
public class UpdateProcessAlertRequest {
    private String smsMsg;

    private String smsTemplateId;

    private String smsTemplateName;

    private String emailMsg;

    private String emailTemplateId;

    private String gimsMsg;

    private String gimsTemplateId;

//    @NotBlank(message = "ModifiedBy is required")
//    private String modifiedBy;
//
//    @NotBlank(message = "Modified IP address is required")
//    private String modifiedIpAddr;
//
//    private String modifiedMacAddr;

    private String modifiedRemarks;

    private String modifiedUri;

//    private Boolean isActive;
}
