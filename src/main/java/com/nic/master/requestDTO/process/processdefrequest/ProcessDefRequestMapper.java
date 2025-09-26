package com.nic.master.requestDTO.process.processdefrequest;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ProcessDefRequestMapper {


    private String operation;

    private String processTypeGuid;

    private String processDefGuid;

    @Valid
    private AddProcessDefRequest addProcessDefRequest;

    @Valid
    private UpdateProcessDefRequest updateProcessDefRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getProcessTypeGuid() {
        return processTypeGuid;
    }

    public void setProcessTypeGuid(String processTypeGuid) {
        this.processTypeGuid = processTypeGuid;
    }

    public String getProcessDefGuid() {
        return processDefGuid;
    }

    public void setProcessDefGuid(String processDefGuid) {
        this.processDefGuid = processDefGuid;
    }

    public AddProcessDefRequest getAddProcessDefRequest() {
        return addProcessDefRequest;
    }

    public void setAddProcessDefRequest(AddProcessDefRequest addProcessDefRequest) {
        this.addProcessDefRequest = addProcessDefRequest;
    }

    public UpdateProcessDefRequest getUpdateProcessDefRequest() {
        return updateProcessDefRequest;
    }

    public void setUpdateProcessDefRequest(UpdateProcessDefRequest updateProcessDefRequest) {
        this.updateProcessDefRequest = updateProcessDefRequest;
    }
}
