package com.nic.master.requestDTO.adm.mstmicroservicerequest;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MicroserviceUpdateRequest {

    @Size(max = 100, message = "Microservice code must be at most 100 characters")//    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Microservice code: only uppercase letters, numbers, hyphens(-) and underscore(_) allowed")
    private String microserviceCode;

    private String modifiedBy;

    private String modifiedIpAddr;


    @Size(max = 100, message = "Microservice name must be at most 100 characters")
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
