package com.nic.master.request.adm.mstmicroservicerequest;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MicroserviceUpdateRequest {

    @Size(min = 4, max = 20, message = "Microservice code must be between 2-20 characters")
//    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Microservice code: only uppercase letters, numbers, hyphens(-) and underscore(_) allowed")
    private String microserviceCode;

    private String modifiedBy;

    private String modifiedIpAddr;

//
//    private String createdRemarks;

    @Size(min = 2, max = 100, message = "Microservice name must be between 2-100 characters")
    private  String microserviceName;

    private String modifiedRemarks;

    public String getMicroserviceCode() {
        return microserviceCode;
    }

    public void setMicroserviceCode(String microserviceCode) {
        this.microserviceCode = microserviceCode;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedIpAddr() {
        return modifiedIpAddr;
    }

    public void setModifiedIpAddr(String modifiedIpAddr) {
        this.modifiedIpAddr = modifiedIpAddr;
    }

    public String getMicroserviceName() {
        return microserviceName;
    }

    public void setMicroserviceName(String microserviceName) {
        this.microserviceName = microserviceName;
    }

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }
}
