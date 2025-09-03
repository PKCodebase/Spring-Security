package com.nic.master.service.process;

import com.nic.master.entity.process.MstActionType;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.process.mstactiontype.AddMstActionRequest;
import com.nic.master.request.process.mstactiontype.UpdateMstActionRequest;

import java.util.List;

public interface MstActionService {

    StatusParam addMstAction(AddMstActionRequest addMstActionRequest);

    List<MstActionType> getAllMstActions();

    MstActionType getMstActionByGuid(String actionTypeGuid);

    SelectOptionParam getMstActionByCode(String actionTypeCode);

    StatusParam updateMstAction(String actionTypeGuid, UpdateMstActionRequest updateMstActionRequest);
}
