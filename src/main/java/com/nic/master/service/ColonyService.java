package com.nic.master.service;

import com.nic.master.param.SelectOptionParam;
import com.nic.master.request.colonyrequest.ColonyAddRequest;
import com.nic.master.request.colonyrequest.ColonyUpdateRequest;
import com.nic.master.response.colonyresponse.ColonyAddResponse;
import com.nic.master.response.colonyresponse.ColonyResponse;
import com.nic.master.response.colonyresponse.ColonyUpdateResponse;
import java.util.List;

public interface ColonyService {

    ColonyAddResponse addColony(String wardGuid,ColonyAddRequest request);

    ColonyResponse getColonyByGuid(String colonyGuid);

    List<SelectOptionParam> fetchColonyMaster();

    SelectOptionParam fetchColonyMasterByCode(String colonyCode );

    List<ColonyResponse> getAllColonies();

    ColonyUpdateResponse updateColonyByGuid(String wardGuid,String colonyGuid, ColonyUpdateRequest colonyUpdateRequest);

}
