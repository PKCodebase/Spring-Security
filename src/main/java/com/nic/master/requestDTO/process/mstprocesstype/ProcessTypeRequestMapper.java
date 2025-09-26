package com.nic.master.requestDTO.process.mstprocesstype;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ProcessTypeRequestMapper {

    private String operation;

    private String processTypeCode;

    private String processTypeGuid;

    @Valid
    private AddMstProcessTypeRequest addMstProcessTypeRequest;

    @Valid
    private UpdateMstProcessTypeRequest updateMstProcessTypeRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getProcessTypeCode() {
        return processTypeCode;
    }

    public void setProcessTypeCode(String processTypeCode) {
        this.processTypeCode = processTypeCode;
    }

    public String getProcessTypeGuid() {
        return processTypeGuid;
    }

    public void setProcessTypeGuid(String processTypeGuid) {
        this.processTypeGuid = processTypeGuid;
    }

    public AddMstProcessTypeRequest getAddMstProcessTypeRequest() {
        return addMstProcessTypeRequest;
    }

    public void setAddMstProcessTypeRequest(AddMstProcessTypeRequest addMstProcessTypeRequest) {
        this.addMstProcessTypeRequest = addMstProcessTypeRequest;
    }

    public UpdateMstProcessTypeRequest getUpdateMstProcessTypeRequest() {
        return updateMstProcessTypeRequest;
    }

    public void setUpdateMstProcessTypeRequest(UpdateMstProcessTypeRequest updateMstProcessTypeRequest) {
        this.updateMstProcessTypeRequest = updateMstProcessTypeRequest;
    }
}
