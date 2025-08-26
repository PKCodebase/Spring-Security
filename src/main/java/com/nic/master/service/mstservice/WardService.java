package com.nic.master.service.mstservice;

import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.mst.wardrequest.WardAddRequest;
import com.nic.master.request.mst.wardrequest.WardUpdateRequest;
import com.nic.master.response.wardresponse.WardResponse;
import java.util.List;

public interface WardService {

    StatusParam addWard(String zoneGuid,WardAddRequest wardAddRequest);

    WardResponse getWardByGuid(String wardGuid);

    List<WardResponse> getAllWards();

    List<SelectOptionParam> fetchWardMaster();

    SelectOptionParam fetchWardMasterByCode(String wardCode);

    StatusParam updateWardByGuid(String zoneGuid,String wardGuid, WardUpdateRequest wardUpdateRequest);

}

