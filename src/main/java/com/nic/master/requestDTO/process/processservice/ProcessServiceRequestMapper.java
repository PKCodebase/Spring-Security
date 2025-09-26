package com.nic.master.requestDTO.process.processservice;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ProcessServiceRequestMapper {

    private String operation;

    private String processServiceGuid;

    @Valid
    private  AddProcessServiceRequest addProcessServiceRequest;

    @Valid
    private  UpdateProcessServiceRequest updateProcessServiceRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getProcessServiceGuid() {
        return processServiceGuid;
    }

    public void setProcessServiceGuid(String processServiceGuid) {
        this.processServiceGuid = processServiceGuid;
    }

    public AddProcessServiceRequest getAddProcessServiceRequest() {
        return addProcessServiceRequest;
    }

    public void setAddProcessServiceRequest(AddProcessServiceRequest addProcessServiceRequest) {
        this.addProcessServiceRequest = addProcessServiceRequest;
    }

    public UpdateProcessServiceRequest getUpdateProcessServiceRequest() {
        return updateProcessServiceRequest;
    }

    public void setUpdateProcessServiceRequest(UpdateProcessServiceRequest updateProcessServiceRequest) {
        this.updateProcessServiceRequest = updateProcessServiceRequest;
    }
}
