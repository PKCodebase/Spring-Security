package com.nic.master.service.admservice;

import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.adm.apiRequest.AddMstApiRequest;
import com.nic.master.requestDTO.adm.apiRequest.UpdateMstApiRequest;
import com.nic.master.responseDTO.mstapiresponse.MstApiResponse;

import java.util.List;

public interface MstApiService {

    StatusParam addMstApi (String microserviceGuid,String urlTypeGuid,AddMstApiRequest addMstApiRequest);

    List<MstApiResponse> getAllMstApi();

    MstApiResponse getMstApiByGuid(String apiGuid);

    SelectOptionParam getMstApiByCode(String apiCode);

    StatusParam updateMstApiByGuid(String apiGuid, UpdateMstApiRequest updateMstApiRequest);
}
