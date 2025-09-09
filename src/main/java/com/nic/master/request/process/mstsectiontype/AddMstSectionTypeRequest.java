package com.nic.master.request.process.mstsectiontype;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddMstSectionTypeRequest {
    @NotBlank(message = "SectionType code is required")
    @Size(max = 100, message = "SectionType code must not exceed 100 characters")
    private String sectionTypeCode;

    @NotBlank(message = "SectionType name is required")
    private String sectionTypeName;

    private String createdRemarks;

    private String createdUri;

    // enum type for sectionCategory (FORM / TEMPLATE / QUERY)
    private String sectionCategory;
}
