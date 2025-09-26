package com.nic.master.service.process;

import com.nic.master.entity.process.MstProcessType;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.mstprocesstype.AddMstProcessTypeRequest;
import com.nic.master.requestDTO.process.mstprocesstype.UpdateMstProcessTypeRequest;

import java.util.List;


public interface MstProcessTypeService  {

    StatusParam addMstProcess(AddMstProcessTypeRequest addMstProcessTypeRequest);

    List<MstProcessType> getAllMstProcess();

    MstProcessType getByProcessTypeGuid(String processTypeGuid);

    SelectOptionParam getByProcessTypeCode(String processTypeCode);

    StatusParam updateMstProcess(String processTypeGuid, UpdateMstProcessTypeRequest updateMstProcessTypeRequest);




}
