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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMicroserviceGuid() {
        return microserviceGuid;
    }

    public void setMicroserviceGuid(String microserviceGuid) {
        this.microserviceGuid = microserviceGuid;
    }

    public String getMicroserviceCode() {
        return microserviceCode;
    }

    public void setMicroserviceCode(String microserviceCode) {
        this.microserviceCode = microserviceCode;
    }

    public MicroserviceAddRequest getMicroserviceAddRequest() {
        return microserviceAddRequest;
    }

    public void setMicroserviceAddRequest(MicroserviceAddRequest microserviceAddRequest) {
        this.microserviceAddRequest = microserviceAddRequest;
    }

    public MicroserviceUpdateRequest getMicroserviceUpdateRequest() {
        return microserviceUpdateRequest;
    }

    public void setMicroserviceUpdateRequest(MicroserviceUpdateRequest microserviceUpdateRequest) {
        this.microserviceUpdateRequest = microserviceUpdateRequest;
    }
}
