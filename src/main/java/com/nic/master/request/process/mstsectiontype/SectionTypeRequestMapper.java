package com.nic.master.request.process.mstsectiontype;

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
}
