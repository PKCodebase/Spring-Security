package com.nic.master.service.process;

import com.nic.master.entity.process.MstSectionType;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.mstsectiontype.AddMstSectionTypeRequest;
import com.nic.master.requestDTO.process.mstsectiontype.UpdateMstSectionTypeRequest;

import java.util.List;

public interface MstSectionTypeService {


    StatusParam addMstSection(AddMstSectionTypeRequest addMstSectionTypeRequest);

    List<MstSectionType> getAllMstSections();

    MstSectionType getSectionByGuid(String sectionTypeGuid);

    SelectOptionParam getSectionByCode(String sectionTypeCode);

    StatusParam updateSection(String sectionTypeGuid,UpdateMstSectionTypeRequest updateMstSectionTypeRequest);
}
