package com.nic.master.request.adm.mstmicroservicerequest;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class MicroserviceRequestMapper {

    private String operation;

    private String microserviceGuid;

    private String microserviceCode;

    @Valid
    private  MicroserviceAddRequest microserviceAddRequest;

    @Valid
    private MicroserviceUpdateRequest microserviceUpdateRequest;
}
