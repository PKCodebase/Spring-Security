package com.nic.master.response.mstserviceresponse;

import com.nic.master.enums.Status;


public class MstServiceStatusResponse {
    private  String serviceCode;
    private String serviceName;

    private Status status;

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
