package com.nic.master.response.Processdefdescresponse;

import lombok.Data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProcessDefDescResponse {

    private String processDefDescGuid;

    private String processDefGuid;       // entity se processDef.getProcessDefGuid()
    private Integer levelNum;
    private String levelDesc;
    private String roleCode;
    private String sectionTypeGuid;      // entity se sectionType.getSectionTypeGuid()

    private LocalDateTime actionDueDate;
    private Integer actionDays;

    // Audit fields
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

    // Flags
    private Boolean isActive;
    private Boolean canChangeRequestApplicability;
    private Boolean canPreviewTemplate;
}
