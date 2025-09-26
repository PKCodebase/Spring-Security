package com.nic.master.service.mstservice;

import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.mst.colonyrequest.ColonyAddRequest;
import com.nic.master.requestDTO.mst.colonyrequest.ColonyUpdateRequest;
import com.nic.master.responseDTO.colonyresponse.ColonyResponse;
import java.util.List;

public interface ColonyService {

    StatusParam addColony(String wardGuid, ColonyAddRequest request);

    ColonyResponse getColonyByGuid(String colonyGuid);

    List<SelectOptionParam> fetchColonyMaster();

    SelectOptionParam fetchColonyMasterByCode(String colonyCode );

    List<ColonyResponse> getAllColonies();

    StatusParam updateColonyByGuid(String wardGuid,String colonyGuid, ColonyUpdateRequest colonyUpdateRequest);

}
