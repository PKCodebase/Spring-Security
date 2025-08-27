package com.nic.master.entity.adm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "mst_api_service",
        schema = "adm",
        uniqueConstraints = {
                @UniqueConstraint(name = "mst_api_service_api_service_code_active_uk", columnNames = {"api_service_code", "is_active"}),
                @UniqueConstraint(name = "mst_api_service_api_service_code_uk", columnNames = {"api_service_code"}),
                @UniqueConstraint(name = "mst_api_service_api_service_id_uk", columnNames = {"api_service_id"})
        }
)
@Getter
@Setter
public class MstApiService {

    @Id
    @Column(name = "api_service_guid", nullable = false, length = 36)
    private String apiServiceGuid;   // Primary Key

    @Column(name = "api_service_id", nullable = false,insertable = false,updatable = false)
    private Long apiServiceId;

    @Column(name = "api_service_code", nullable = false)
    private String apiServiceCode;

    @Column(name = "api_service_name", nullable = false)
    private String apiServiceName;

    @Column(name = "api_service_url", nullable = false)
    private String apiServiceUrl;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_ip_addr", nullable = false)
    private String createdIpAddr;

    @Column(name = "created_remarks")
    private String createdRemarks;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "modified_ip_addr")
    private String modifiedIpAddr;

    @Column(name = "modified_remarks")
    private String modifiedRemarks;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    public String getApiServiceGuid() {
        return apiServiceGuid;
    }

    public void setApiServiceGuid(String apiServiceGuid) {
        this.apiServiceGuid = apiServiceGuid;
    }

    public Long getApiServiceId() {
        return apiServiceId;
    }

    public void setApiServiceId(Long apiServiceId) {
        this.apiServiceId = apiServiceId;
    }

    public String getApiServiceCode() {
        return apiServiceCode;
    }

    public void setApiServiceCode(String apiServiceCode) {
        this.apiServiceCode = apiServiceCode;
    }

    public String getApiServiceName() {
        return apiServiceName;
    }

    public void setApiServiceName(String apiServiceName) {
        this.apiServiceName = apiServiceName;
    }

    public String getApiServiceUrl() {
        return apiServiceUrl;
    }

    public void setApiServiceUrl(String apiServiceUrl) {
        this.apiServiceUrl = apiServiceUrl;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
