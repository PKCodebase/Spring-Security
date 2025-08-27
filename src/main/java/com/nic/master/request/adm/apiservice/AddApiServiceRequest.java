package com.nic.master.request.adm.apiservice;

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

}
