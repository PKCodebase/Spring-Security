package com.nic.master.requestDTO.adm.modulerequest;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MstModuleUpdateRequest {

    @Size(min = 2, max = 100, message = "Module name must be between 2-100 characters")
    private String moduleName;

    @Size(min = 4, max = 20, message = "Module code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Module code: only uppercase letters, numbers, hyphens(-) and underscore(_) allowed")
    private String moduleCode;

    private String modifiedBy;

    private String modifiedIpAddr;

    private String modifiedRemarks;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
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

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }
}
