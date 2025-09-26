package com.nic.master.requestDTO.process.mstactiontype;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class MstActionRequestMapper {

    private String operation;

    private String actionTypeGuid;

    private String actionTypeCode;

    @Valid
    private AddMstActionRequest addMstActionRequest;

    @Valid
    private UpdateMstActionRequest updateMstActionRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getActionTypeGuid() {
        return actionTypeGuid;
    }

    public void setActionTypeGuid(String actionTypeGuid) {
        this.actionTypeGuid = actionTypeGuid;
    }

    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public AddMstActionRequest getAddMstActionRequest() {
        return addMstActionRequest;
    }

    public void setAddMstActionRequest(AddMstActionRequest addMstActionRequest) {
        this.addMstActionRequest = addMstActionRequest;
    }

    public UpdateMstActionRequest getUpdateMstActionRequest() {
        return updateMstActionRequest;
    }

    public void setUpdateMstActionRequest(UpdateMstActionRequest updateMstActionRequest) {
        this.updateMstActionRequest = updateMstActionRequest;
    }
}
