package com.nic.master.request.adm.apiservicerequest;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateApiServiceRequest {

    @Size(min = 4, max = 20, message = "Module code must be between 2-20 characters")
    private String apiServiceCode;


    @Size(min = 2, max = 100, message = "Module name must be between 2-100 characters")
    private String apiServiceName;

    private String modifiedRemarks;

    public String getApiServiceCode() {
        return apiServiceCode;
    }

    public void setApiServiceCode(String apiServiceCode) {
        this.apiServiceCode = apiServiceCode;
    }

    public String getApiServiceName() {
        return apiServiceName;
    }

    public void setApiServiceName(String apiServiceName) {
        this.apiServiceName = apiServiceName;
    }

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }
}
