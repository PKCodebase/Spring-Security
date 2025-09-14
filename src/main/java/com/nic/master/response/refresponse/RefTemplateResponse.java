package com.nic.master.response.refresponse;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private String modifiedBy;
    private LocalDateTime modifiedDate;
    private String modifiedIpAddr;
    private Boolean isActive;
    private String refTemplateName;
    private String refTemplateCode;
    private Boolean isQueryRaiseCheck;
    private String queryCheckMessage;
}
