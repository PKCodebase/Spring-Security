package com.nic.master.request.adm.apiRequest;

import com.nic.master.request.adm.apiservicerequest.AddApiServiceRequest;
import com.nic.master.request.adm.apiservicerequest.UpdateApiServiceRequest;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ApiRequestMapper {

    private  String operation;

    private String apiGuid;

    private String apiCode;

    private String microserviceGuid;

    private  String  urlTypeGuid;

    @Valid
    private AddMstApiRequest addMstApiRequest;

    @Valid
    private UpdateMstApiRequest updateMstApiRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getApiGuid() {
        return apiGuid;
    }

    public void setApiGuid(String apiGuid) {
        this.apiGuid = apiGuid;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public AddMstApiRequest getAddMstApiRequest() {
        return addMstApiRequest;
    }

    public void setAddMstApiRequest(AddMstApiRequest addMstApiRequest) {
        this.addMstApiRequest = addMstApiRequest;
    }

    public UpdateMstApiRequest getUpdateMstApiRequest() {
        return updateMstApiRequest;
    }

    public void setUpdateMstApiRequest(UpdateMstApiRequest updateMstApiRequest) {
        this.updateMstApiRequest = updateMstApiRequest;
    }

    public String getMicroserviceGuid() {
        return microserviceGuid;
    }

    public void setMicroserviceGuid(String microserviceGuid) {
        this.microserviceGuid = microserviceGuid;
    }

    public String getUrlTypeGuid() {
        return urlTypeGuid;
    }

    public void setUrlTypeGuid(String urlTypeGuid) {
        this.urlTypeGuid = urlTypeGuid;
    }
}
