package com.nic.master.requestDTO.adm.apiservicerequest;

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getApiServiceGuid() {
        return apiServiceGuid;
    }

    public void setApiServiceGuid(String apiServiceGuid) {
        this.apiServiceGuid = apiServiceGuid;
    }

    public String getApiServiceCode() {
        return apiServiceCode;
    }

    public void setApiServiceCode(String apiServiceCode) {
        this.apiServiceCode = apiServiceCode;
    }

    public AddApiServiceRequest getAddApiServiceRequest() {
        return addApiServiceRequest;
    }

    public void setAddApiServiceRequest(AddApiServiceRequest addApiServiceRequest) {
        this.addApiServiceRequest = addApiServiceRequest;
    }

    public UpdateApiServiceRequest getUpdateApiServiceRequest() {
        return updateApiServiceRequest;
    }

    public void setUpdateApiServiceRequest(UpdateApiServiceRequest updateApiServiceRequest) {
        this.updateApiServiceRequest = updateApiServiceRequest;
    }
}
