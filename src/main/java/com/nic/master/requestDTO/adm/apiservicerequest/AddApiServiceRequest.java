package com.nic.master.requestDTO.adm.apiservicerequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddApiServiceRequest {



    @NotBlank(message = "ApiServiceCode is mandatory")
    @Size(max=255 , message= "MstApiService code cannot exceed 255 characters")
    private String apiServiceCode;

    @NotBlank(message = "ApiServiceName is mandatory")
    @Size(max = 255 , message = "ApiServiceName cannot exceed 255 characters")
    private String apiServiceName;

    @NotBlank(message = "Api service url is mandatory")
    private String apiServiceUrl;
    private String createdRemarks;

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

    public String getApiServiceUrl() {
        return apiServiceUrl;
    }

    public void setApiServiceUrl(String apiServiceUrl) {
        this.apiServiceUrl = apiServiceUrl;
    }

    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
    }
}
