package com.nic.master.service.process;

import com.nic.master.entity.process.MstCodeImpl;
import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.mstcodeimpl.AddMstCodeImplRequest;
import com.nic.master.requestDTO.process.mstcodeimpl.UpdateMstCodeImplRequest;

import java.util.List;

public interface MstCodeService {

    StatusParam addMstCode(AddMstCodeImplRequest addMstCodeImplRequest);

    List<MstCodeImpl> getAllMstCodeImpl();

    MstCodeImpl getByGuid(String codeImplGuid);

    StatusParam updateMstCodeImpl(String codeImplGuid, UpdateMstCodeImplRequest updateMstCodeImplRequest);
}
