package com.nic.master.requestDTO.process.processservice;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class AddProcessServiceRequest {

    @NotBlank(message = "Service URL cannot be blank")
    private String serviceUrl;




    private String createdRemarks; // optional
    private String createdUri;     // optional

    // --------------------
    // Getters and Setters
    // --------------------
    public String getServiceUrl() { return serviceUrl; }
    public void setServiceUrl(String serviceUrl) { this.serviceUrl = serviceUrl; }

    public String getCreatedRemarks() { return createdRemarks; }
    public void setCreatedRemarks(String createdRemarks) { this.createdRemarks = createdRemarks; }

    public String getCreatedUri() { return createdUri; }
    public void setCreatedUri(String createdUri) { this.createdUri = createdUri; }
}
