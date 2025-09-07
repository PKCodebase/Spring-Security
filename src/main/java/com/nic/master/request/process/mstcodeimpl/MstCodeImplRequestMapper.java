package com.nic.master.request.process.mstcodeimpl;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class MstCodeImplRequestMapper {


    private String operation;

    private String codeImplGuid;

    @Valid
    private AddMstCodeImplRequest addMstCodeImplRequest;

    @Valid
    private UpdateMstCodeImplRequest updateMstCodeImplRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getCodeImplGuid() {
        return codeImplGuid;
    }

    public void setCodeImplGuid(String codeImplGuid) {
        this.codeImplGuid = codeImplGuid;
    }

    public AddMstCodeImplRequest getAddMstCodeImplRequest() {
        return addMstCodeImplRequest;
    }

    public void setAddMstCodeImplRequest(AddMstCodeImplRequest addMstCodeImplRequest) {
        this.addMstCodeImplRequest = addMstCodeImplRequest;
    }

    public UpdateMstCodeImplRequest getUpdateMstCodeImplRequest() {
        return updateMstCodeImplRequest;
    }

    public void setUpdateMstCodeImplRequest(UpdateMstCodeImplRequest updateMstCodeImplRequest) {
        this.updateMstCodeImplRequest = updateMstCodeImplRequest;
    }
}
