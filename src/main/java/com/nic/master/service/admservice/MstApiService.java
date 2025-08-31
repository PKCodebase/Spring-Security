package com.nic.master.service.admservice;

import com.nic.master.entity.adm.MstApi;
import com.nic.master.param.StatusParam;
import com.nic.master.request.adm.apiRequest.AddMstApiRequest;
import com.nic.master.response.mstapiresponse.MstApiResponse;

import java.util.List;

public interface MstApiService {

    StatusParam addMstApi (String microserviceGuid,String urlTypeGuid,AddMstApiRequest addMstApiRequest);

    List<MstApiResponse> getAllMstApi();
}
