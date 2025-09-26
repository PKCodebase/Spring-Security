package com.nic.master.requestDTO.process.processdefrequest;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateProcessDefRequest {
//    @NotBlank(message = "Process Def GUID is required")
//    private String processDefGuid;
//
//    @NotBlank(message = "Process Type GUID is required")
//    private String processTypeGuid;

    @Pattern(regexp = "^[A-Z0-9\\-_]*$", message = "Only capital letters, numbers, hyphen, underscore allowed")
    private String processDefCode;

//    @NotBlank(message = "Process Def Name is required")
    private String processDefName;

    private Boolean isPrimaryOrgApplicable;
    private Boolean isWrapperOrgApplicable;
    private Boolean isOrgUnitApplicable;
    private Boolean isServiceApplicable;
    private Boolean isCadreApplicable;

//    @NotBlank(message = "Modified By is required")
    private String modifiedBy;

//    @NotBlank(message = "Modified IP Address is required")
    private String modifiedIpAddr;

    private String modifiedMacAddr;
    private String modifiedRemarks;
    private String modifiedUri;

//    private Boolean isActive;
//    private Boolean canShowParent;
//    private Boolean canShowChild;
//    private Boolean canDraft;
}
