package com.nic.master.response.mstapiresponse;

import com.nic.master.entity.adm.MstMicroservice;
import com.nic.master.entity.adm.MstUrlType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class MstApiResponse {

    private String apiGuid;

    private Long apiId;

    private String apiCode;

//    private MstMicroservice microservice;
//
//    private MstUrlType urlType;

    private String url;

    private Map<String, Object> subUrlJson;


    private String createdBy;

    private LocalDateTime createdDate;


    private String createdIpAddr;


    private String createdRemarks;


    private String modifiedBy;


    private LocalDateTime modifiedDate;

    private String modifiedIpAddr;

    private String modifiedRemarks;

    private Boolean isActive = true;

    private String microserviceGuid;

    private String microserviceName;

    private String urlTypeGuid;

    private String urlTypeName;


    public String getApiGuid() {
        return apiGuid;
    }

    public void setApiGuid(String apiGuid) {
        this.apiGuid = apiGuid;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getSubUrlJson() {
        return subUrlJson;
    }

    public void setSubUrlJson(Map<String, Object> subUrlJson) {
        this.subUrlJson = subUrlJson;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getMicroserviceGuid() {
        return microserviceGuid;
    }

    public void setMicroserviceGuid(String microserviceGuid) {
        this.microserviceGuid = microserviceGuid;
    }

    public String getMicroserviceName() {
        return microserviceName;
    }

    public void setMicroserviceName(String microserviceName) {
        this.microserviceName = microserviceName;
    }

    public String getUrlTypeGuid() {
        return urlTypeGuid;
    }

    public void setUrlTypeGuid(String urlTypeGuid) {
        this.urlTypeGuid = urlTypeGuid;
    }

    public String getUrlTypeName() {
        return urlTypeName;
    }

    public void setUrlTypeName(String urlTypeName) {
        this.urlTypeName = urlTypeName;
    }
}
