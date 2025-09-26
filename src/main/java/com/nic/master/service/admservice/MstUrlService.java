package com.nic.master.service.admservice;

import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.adm.msturlrequest.AddMstUrlRequest;
import com.nic.master.requestDTO.adm.msturlrequest.UpdateMstUrlRequest;
import com.nic.master.responseDTO.msturlresponse.MstUrlResponse;

import java.util.List;

public interface MstUrlService {

    StatusParam addMstUrl(AddMstUrlRequest addMstUrlRequest);

    List<MstUrlResponse> getAllUrl();

    MstUrlResponse getApiUrlByGuid(String urlGuid);

    SelectOptionParam getApiUrlByCode(String urlCode);

    StatusParam updateMstUrlByGuid(String urlGuid, UpdateMstUrlRequest updateMstUrlRequest);
}
