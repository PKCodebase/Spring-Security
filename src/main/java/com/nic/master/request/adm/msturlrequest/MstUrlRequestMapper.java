package com.nic.master.request.adm.msturlrequest;

import com.nic.master.request.adm.apiservicerequest.AddApiServiceRequest;
import com.nic.master.request.adm.apiservicerequest.UpdateApiServiceRequest;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class MstUrlRequestMapper {
    private  String operation;

    private String urlTypeGuid;

    private String urlTypeCode;

    @Valid
    private AddMstUrlRequest addMstUrlRequest;

    @Valid
    private UpdateMstUrlRequest updateMstUrlRequest;


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUrlTypeGuid() {
        return urlTypeGuid;
    }

    public void setUrlTypeGuid(String urlTypeGuid) {
        this.urlTypeGuid = urlTypeGuid;
    }

    public String getUrlTypeCode() {
        return urlTypeCode;
    }

    public void setUrlTypeCode(String urlTypeCode) {
        this.urlTypeCode = urlTypeCode;
    }

    public AddMstUrlRequest getAddMstUrlRequest() {
        return addMstUrlRequest;
    }

    public void setAddMstUrlRequest(AddMstUrlRequest addMstUrlRequest) {
        this.addMstUrlRequest = addMstUrlRequest;
    }

    public UpdateMstUrlRequest getUpdateMstUrlRequest() {
        return updateMstUrlRequest;
    }

    public void setUpdateMstUrlRequest(UpdateMstUrlRequest updateMstUrlRequest) {
        this.updateMstUrlRequest = updateMstUrlRequest;
    }
}
