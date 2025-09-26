package com.nic.master.requestDTO.process.mstsectiontype;

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

    public String getSectionTypeCode() {
        return sectionTypeCode;
    }

    public void setSectionTypeCode(String sectionTypeCode) {
        this.sectionTypeCode = sectionTypeCode;
    }

    public String getSectionTypeName() {
        return sectionTypeName;
    }

    public void setSectionTypeName(String sectionTypeName) {
        this.sectionTypeName = sectionTypeName;
    }

    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
    }

    public String getCreatedUri() {
        return createdUri;
    }

    public void setCreatedUri(String createdUri) {
        this.createdUri = createdUri;
    }

    public String getSectionCategory() {
        return sectionCategory;
    }

    public void setSectionCategory(String sectionCategory) {
        this.sectionCategory = sectionCategory;
    }
}
