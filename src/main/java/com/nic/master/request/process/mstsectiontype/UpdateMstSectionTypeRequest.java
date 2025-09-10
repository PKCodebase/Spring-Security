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

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }

    public String getModifiedUri() {
        return modifiedUri;
    }

    public void setModifiedUri(String modifiedUri) {
        this.modifiedUri = modifiedUri;
    }

    public String getSectionCategory() {
        return sectionCategory;
    }

    public void setSectionCategory(String sectionCategory) {
        this.sectionCategory = sectionCategory;
    }
}
