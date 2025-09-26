package com.nic.master.service.admservice;

import com.nic.master.entity.adm.MstMicroservice;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.adm.mstmicroservicerequest.MicroserviceAddRequest;
import com.nic.master.requestDTO.adm.mstmicroservicerequest.MicroserviceUpdateRequest;

import java.util.List;

public interface MstMicroserviceService {

    StatusParam addMicroservice(MicroserviceAddRequest microserviceAddRequest);

    List<MstMicroservice>  getAllMicroservices();

    MstMicroservice getMicroserviceByGuid(String microserviceGuid);

    SelectOptionParam getMicroserviceByCode(String microserviceCode);

    StatusParam updateMicroServiceByGuid(String microserviceGuid, MicroserviceUpdateRequest microserviceUpdateRequest);
}
