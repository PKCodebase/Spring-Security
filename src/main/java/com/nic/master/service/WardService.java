package com.nic.master.service;

import com.nic.master.param.SelectOptionParam;
import com.nic.master.request.wardrequest.WardAddRequest;
import com.nic.master.request.wardrequest.WardUpdateRequest;
import com.nic.master.response.wardresponse.WardAddResponse;
import com.nic.master.response.wardresponse.WardResponse;
import com.nic.master.response.wardresponse.WardUpdateResponse;
import java.util.List;

public interface WardService {

    WardAddResponse addWard(String zoneGuid,WardAddRequest wardAddRequest);

    WardResponse getWardByGuid(String wardGuid);

    List<WardResponse> getAllWards();

    List<SelectOptionParam> fetchWardMaster();

    SelectOptionParam fetchWardMasterByCode(String wardCode);

    WardUpdateResponse updateWardByGuid(String zoneGuid,String wardGuid, WardUpdateRequest wardUpdateRequest);

}

