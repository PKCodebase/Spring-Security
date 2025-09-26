package com.nic.master.requestDTO.process.processservice;

import lombok.Data;

@Data
public class UpdateProcessServiceRequest {


    private String serviceUrl;

    private String modifiedRemarks; // optional
    private String modifiedUri;     // optional

    // --------------------
    // Getters and Setters
    // --------------------
    public String getServiceUrl() { return serviceUrl; }
    public void setServiceUrl(String serviceUrl) { this.serviceUrl = serviceUrl; }



    public String getModifiedRemarks() { return modifiedRemarks; }
    public void setModifiedRemarks(String modifiedRemarks) { this.modifiedRemarks = modifiedRemarks; }

    public String getModifiedUri() { return modifiedUri; }
    public void setModifiedUri(String modifiedUri) { this.modifiedUri = modifiedUri; }
}
