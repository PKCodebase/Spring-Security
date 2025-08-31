package com.nic.master.request.adm.modulerequest;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ModuleRequestMapper {
    private String operation;
    private  String moduleGuid;
    private String moduleCode;

    @Valid
    private MstModuleAddRequest moduleAddRequest;

    @Valid
    private MstModuleUpdateRequest moduleUpdateRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getModuleGuid() {
        return moduleGuid;
    }

    public void setModuleGuid(String moduleGuid) {
        this.moduleGuid = moduleGuid;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public MstModuleAddRequest getModuleAddRequest() {
        return moduleAddRequest;
    }

    public void setModuleAddRequest(MstModuleAddRequest moduleAddRequest) {
        this.moduleAddRequest = moduleAddRequest;
    }

    public MstModuleUpdateRequest getModuleUpdateRequest() {
        return moduleUpdateRequest;
    }

    public void setModuleUpdateRequest(MstModuleUpdateRequest moduleUpdateRequest) {
        this.moduleUpdateRequest = moduleUpdateRequest;
    }
}
