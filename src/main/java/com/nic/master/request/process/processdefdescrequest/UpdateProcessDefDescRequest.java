package com.nic.master.request.process.processdefdescrequest;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdateProcessDefDescRequest {

//    @NotBlank(message = "processDefDescGuid is required")
//    @Size(min = 36, max = 36, message = "processDefDescGuid must be exactly 36 characters (UUID)")
//    private String processDefDescGuid;

    private String levelDesc;

    private String roleCode;

//    @Size(min = 36, max = 36, message = "sectionTypeGuid must be exactly 36 characters (UUID)")
//    private String sectionTypeGuid;

    /** actionDueDate OR actionDays required (handle in service) */
//    if (request.getActionDueDate() == null && request.getActionDays() == null) {
//        throw new IllegalArgumentException("Either actionDueDate or actionDays must be provided");
//    }

    @Pattern(
            regexp = "^([0-2][0-9]|3[0-1])/([0][1-9]|1[0-2])/\\d{4}$",
            message = "actionDueDate must be in the format dd/MM/yyyy, e.g., 04/12/2000"
    )
    private String actionDueDate;

    @Max(value = 364, message = "actionDays must be less than 365")
    private Integer actionDays;

    // Audit fields (modified block)
    private String modifiedBy;
    private String modifiedIpAddr;
    private String modifiedMacAddr;
    private String modifiedRemarks;
    private String modifiedUri;
}
