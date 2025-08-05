package com.nic.master.request.mstservicerequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;

public class MstUpdateServiceRequest {

    @NotNull(message = "Service code cannot be null")
    @NotEmpty(message = "Service code is required")
    @Size(min = 2, max = 20, message = "Service code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Service code: only uppercase letters, numbers, hyphens(-) and underscore(_) allowed")
    private String serviceCode;

    @NotNull(message = "Service name cannot be null")
    @NotEmpty(message = "Service name is required")
    @Size(min = 2, max = 100, message = "Service name must be between 2-100 characters")
    private String serviceName;

    @NotNull(message = "Created by cannot be null")
    @NotEmpty(message = "Created by is required")
    @Size(min = 2, max = 50, message = "Created by must be between 2-50 characters")
    private String createdBy;

    @NotNull(message = "Created IP address cannot be null")
    @NotEmpty(message = "Created IP address is required")
    @Pattern(regexp = "^(?:\\d{1,3}\\.){3}\\d{1,3}$", message = "Invalid IP address format")
    private String createdIpAddr;

    @Size(max = 500, message = "Created remarks cannot exceed 500 characters")
    private String createdRemarks;

    @NotNull(message = "Modified by cannot be null")
    @NotEmpty(message = "Modified by is required")
    @Size(min = 2, max = 50, message = "Modified by must be between 2-50 characters")
    private String modifiedBy;

    private Date modifiedDate;
    
    @NotNull(message = "Modified IP address cannot be null")
    @NotEmpty(message = "Modified IP address is required")
    @Pattern(regexp = "^(?:\\d{1,3}\\.){3}\\d{1,3}$", message = "Invalid IP address format")
    private String modifiedIpAddr;
    
    @Size(max = 500, message = "Modified remarks cannot exceed 500 characters")
    private String modifiedRemarks;

    @NotNull(message = "Process code cannot be null")
    @NotEmpty(message = "Process code is required")
    @Size(min = 2, max = 20, message = "Process code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Process code: only uppercase letters, numbers, hyphens(-) and underscore(_) allowed")
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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedIpAddr() {
        return modifiedIpAddr;
    }

    public void setModifiedIpAddr(String modifiedIpAddr) {
        this.modifiedIpAddr = modifiedIpAddr;
    }

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }
}