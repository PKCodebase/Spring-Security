package com.nic.master.entity.adm;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "mst_microservice",
        schema = "adm",
        uniqueConstraints = {
                @UniqueConstraint(name = "mst_microservice_microservice_code_uk", columnNames = "microservice_code"),
                @UniqueConstraint(name = "mst_microservice_microservice_id_uk", columnNames = "microservice_id")
        }
)
public class MstMicroservice {

    @Id
    @Column(name = "microservice_guid", nullable = false, length = 36)
    private String microserviceGuid;  // Primary Key UUID

    @Column(name = "microservice_id", nullable = false, unique = true,updatable = false,insertable = false)
    private Long microserviceId;

    @Column(name = "microservice_code", nullable = false, unique = true)
    private String microserviceCode;

    @Column(name = "microservice_name", nullable = false)
    private String microserviceName;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
    private LocalDateTime createdDate;

    @Column(name = "created_ip_addr", nullable = false)
    private String createdIpAddr;

    @Column(name = "created_remarks")
    private String createdRemarks;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
    private LocalDateTime modifiedDate;

    @Column(name = "modified_ip_addr")
    private String modifiedIpAddr;

    @Column(name = "modified_remarks")
    private String modifiedRemarks;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    public String getMicroserviceGuid() {
        return microserviceGuid;
    }

    public void setMicroserviceGuid(String microserviceGuid) {
        this.microserviceGuid = microserviceGuid;
    }

    public Long getMicroserviceId() {
        return microserviceId;
    }

    public void setMicroserviceId(Long microserviceId) {
        this.microserviceId = microserviceId;
    }

    public String getMicroserviceCode() {
        return microserviceCode;
    }

    public void setMicroserviceCode(String microserviceCode) {
        this.microserviceCode = microserviceCode;
    }

    public String getMicroserviceName() {
        return microserviceName;
    }

    public void setMicroserviceName(String microserviceName) {
        this.microserviceName = microserviceName;
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
