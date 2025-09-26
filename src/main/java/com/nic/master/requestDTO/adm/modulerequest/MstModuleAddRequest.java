package com.nic.master.requestDTO.adm.modulerequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;

@Data
public class MstModuleAddRequest {

    @NotBlank(message = "Module code is mandatory")
    @Size(max = 100, message = "Module code cannot exceed 255 characters")
    private String moduleCode;

    @NotBlank(message = "Module name is mandatory")
    @Size(max = 255, message = "Module name cannot exceed 255 characters")
    private String moduleName;

    private Map<String, Object> moduleConfig;

//    @NotBlank(message = "Created by is mandatory")
//    private String createdBy;
//
//    private String createdIpAddr;

    private String createdRemarks;

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


    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
    }
}
