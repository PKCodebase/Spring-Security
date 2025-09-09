package com.nic.master.request.process.mstsectiontype;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateMstSectionTypeRequest {
    @Size(max = 100, message = "SectionType code must not exceed 100 characters")
    private String sectionTypeCode;

    private String sectionTypeName;

    private String modifiedRemarks;

    private String modifiedUri;

    // enum type for sectionCategory (FORM / TEMPLATE / QUERY)
    private String sectionCategory;
}
