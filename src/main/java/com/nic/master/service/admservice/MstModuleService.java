package com.nic.master.service.admservice;

import com.nic.master.entity.adm.MstModule;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.adm.modulerequest.MstModuleAddRequest;
import com.nic.master.requestDTO.adm.modulerequest.MstModuleUpdateRequest;

import java.util.List;

public interface MstModuleService {

    StatusParam addModule(MstModuleAddRequest modelAddRequest);

    List<MstModule> getAllModules();

    MstModule getModuleByGuid(String moduleGuid);

    SelectOptionParam getModuleByCode(String moduleCode);

    StatusParam updateModuleByGuid(String moduleGuid, MstModuleUpdateRequest mstModuleUpdateRequest);


}
