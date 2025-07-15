package com.nic.master.request.mstservicerequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class MstAddServiceRequest {

    @NotBlank(message = "Service Code cannot be blank")
    private String serviceCode;

    @NotBlank(message = "Service name cannot be blank")
    private String serviceName;

    @NotBlank(message = "CreatedBy cannot be blank")
    private String createdBy;

     @NotNull(message = "Date cannot be blank")
     private String  createdDate;

    @NotBlank(message = "IpAddress cannot be blank")
    private String createdIpAddr;

    private String createdRemarks;


    @NotBlank(message = "Process code is required")
    private String processCode;

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedIpAddr() {
        return createdIpAddr;
    }

    public void setCreatedIpAddr(String createdIpAddr) {
        this.createdIpAddr = createdIpAddr;
    }

    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }
}