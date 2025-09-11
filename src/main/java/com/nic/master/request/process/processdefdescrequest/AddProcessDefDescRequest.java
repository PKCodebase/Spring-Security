package com.nic.master.request.process.processdefdescrequest;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AddProcessDefDescRequest {

//    @NotBlank(message = "processDefGuid is required")
//    @Size(min = 36, max = 36, message = "processDefGuid must be exactly 36 characters (UUID)")
//    private String processDefGuid;

    @NotNull(message = "levelNum is required")
    @Min(value = 1, message = "levelNum must be greater than or equal to 1")
    private Integer levelNum;

    @NotBlank(message = "levelDesc is required")
    private String levelDesc;

    @NotBlank(message = "roleCode is required")
    private String roleCode;

//    @NotBlank(message = "sectionTypeGuid is required")
//    @Size(min = 36, max = 36, message = "sectionTypeGuid must be exactly 36 characters (UUID)")
//    private String sectionTypeGuid;

    /** actionDueDate OR actionDays required (handle in service) */
    @NotBlank(message = "actionDueDate is required")
    @Pattern(
            regexp = "^([0-2][0-9]|3[0-1])/([0][1-9]|1[0-2])/\\d{4}$",
            message = "actionDueDate must be in the format dd/MM/yyyy, e.g., 04/12/2000"
    )
    private String actionDueDate; // ISO date string e.g. "2025-09-08T12:30:00"

    @Max(value = 364, message = "actionDays must be less than 365")
    private Integer actionDays;

    // Audit fields
//    @NotBlank(message = "createdBy is required")
    private String createdBy;

//    @NotBlank(message = "createdIpAddr is required")
    private String createdIpAddr;

    private String createdMacAddr;
    private String createdRemarks;
    private String createdUri;
}
