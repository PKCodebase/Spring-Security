package com.nic.master.entity.adm;




import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;


import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "mst_module", schema = "adm",
        uniqueConstraints = {
                @UniqueConstraint(name = "mst_module_module_code_active_uk", columnNames = {"module_code", "is_active"}),
                @UniqueConstraint(name = "mst_module_module_code_uk", columnNames = "module_code"),
                @UniqueConstraint(name = "mst_module_module_id_uk", columnNames = "module_id")
        })
public class MstModule {

    @Id
    @Column(name = "module_guid", nullable = false, length = 36)
    private String moduleGuid;

    @Column(name = "module_id",nullable = false, unique = true,updatable = false,insertable = false)
    private Long moduleId;

    @Column(name = "module_code", nullable = false)
    private String moduleCode;

    @Column(name = "module_name", nullable = false)
    private String moduleName;

    @Type(JsonType.class)
    @Column(name = "module_config",columnDefinition = "jsonb") // PostgreSQL: jsonb / MySQL: json
    private Map<String, Object> moduleConfig;


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

    public String getModuleGuid() {
        return moduleGuid;
    }

    public void setModuleGuid(String moduleGuid) {
        this.moduleGuid = moduleGuid;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }



    public Map<String, Object> getModuleConfig() {
        return moduleConfig;
    }

    public void setModuleConfig(Map<String, Object> moduleConfig) {
        this.moduleConfig = moduleConfig;
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

