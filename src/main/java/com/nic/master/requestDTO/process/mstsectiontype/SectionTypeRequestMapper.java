package com.nic.master.requestDTO.process.mstsectiontype;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class SectionTypeRequestMapper {

    private String operation;

    private String sectionTypeCode;

    private String  sectionTypeGuid;

    @Valid
    private AddMstSectionTypeRequest addMstSectionTypeRequest;

    @Valid
    private UpdateMstSectionTypeRequest updateMstSectionTypeRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSectionTypeCode() {
        return sectionTypeCode;
    }

    public void setSectionTypeCode(String sectionTypeCode) {
        this.sectionTypeCode = sectionTypeCode;
    }

    public String getSectionTypeGuid() {
        return sectionTypeGuid;
    }

    public void setSectionTypeGuid(String sectionTypeGuid) {
        this.sectionTypeGuid = sectionTypeGuid;
    }

    public AddMstSectionTypeRequest getAddMstSectionTypeRequest() {
        return addMstSectionTypeRequest;
    }

    public void setAddMstSectionTypeRequest(AddMstSectionTypeRequest addMstSectionTypeRequest) {
        this.addMstSectionTypeRequest = addMstSectionTypeRequest;
    }

    public UpdateMstSectionTypeRequest getUpdateMstSectionTypeRequest() {
        return updateMstSectionTypeRequest;
    }

    public void setUpdateMstSectionTypeRequest(UpdateMstSectionTypeRequest updateMstSectionTypeRequest) {
        this.updateMstSectionTypeRequest = updateMstSectionTypeRequest;
    }
}
