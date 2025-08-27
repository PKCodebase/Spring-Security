package com.nic.master.request.adm.apiservice;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ApiServiceRequestMapper {

    private  String operation;

    private String apiServiceGuid;

    private String apiServiceCode;

    @Valid
    private  AddApiServiceRequest addApiServiceRequest;

    @Valid
    private UpdateApiServiceRequest updateApiServiceRequest;

}
