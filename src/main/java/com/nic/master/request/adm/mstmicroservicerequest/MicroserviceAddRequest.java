package com.nic.master.request.adm.mstmicroservicerequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MicroserviceAddRequest {


    @NotBlank(message = "Microservice code is mandatory")
    @Size(max = 255, message = "Microservice code cannot exceed 255 characters")
    private String microserviceCode;

    @NotBlank(message = "Microservice name is mandatory")
    @Size(max = 255, message = "Microservice name cannot exceed 255 characters")
    private String microserviceName;

    private String createdRemarks;

}
