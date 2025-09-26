package com.nic.master.service.admservice;

import com.nic.master.entity.adm.MstApiService;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.adm.apiservicerequest.AddApiServiceRequest;
import com.nic.master.requestDTO.adm.apiservicerequest.UpdateApiServiceRequest;

import java.util.List;

public interface MstApiServices {

    StatusParam addApiService(AddApiServiceRequest apiServiceAddRequest);

    List<MstApiService> getAllApiServices();

    MstApiService getApiServiceByGuid(String apiServiceGuid);

    SelectOptionParam getApiServiceByCode(String apiServiceCode);

    StatusParam updateApiService(String apiServiceGuid, UpdateApiServiceRequest updateApiServiceRequest);

//    StatusParam updateApiService(@Valid UpdateApiServiceRequest updateApiServiceRequest);
}
