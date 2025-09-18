package com.nic.master.request.process.processdefdescriptionaction;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ProcessDefDescActionRequestMapper {

    private  String operation;

    private String processDefDescGuid;

    private String sectionTypeGuid;

    private  String actionTypeGuid;

    private String processDefDescActionGuid;

    @Valid
    private AddProcessDefDescActionRequest addProcessDefDescActionRequest;

    @Valid
    private UpdateProcessDefDescActionRequest updateProcessDefDescActionRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getProcessDefDescGuid() {
        return processDefDescGuid;
    }

    public void setProcessDefDescGuid(String processDefDescGuid) {
        this.processDefDescGuid = processDefDescGuid;
    }

    public String getSectionTypeGuid() {
        return sectionTypeGuid;
    }

    public void setSectionTypeGuid(String sectionTypeGuid) {
        this.sectionTypeGuid = sectionTypeGuid;
    }

    public String getActionTypeGuid() {
        return actionTypeGuid;
    }

    public void setActionTypeGuid(String actionTypeGuid) {
        this.actionTypeGuid = actionTypeGuid;
    }

    public String getProcessDefDescActionGuid() {
        return processDefDescActionGuid;
    }

    public void setProcessDefDescActionGuid(String processDefDescActionGuid) {
        this.processDefDescActionGuid = processDefDescActionGuid;
    }

    public AddProcessDefDescActionRequest getAddProcessDefDescActionRequest() {
        return addProcessDefDescActionRequest;
    }

    public void setAddProcessDefDescActionRequest(AddProcessDefDescActionRequest addProcessDefDescActionRequest) {
        this.addProcessDefDescActionRequest = addProcessDefDescActionRequest;
    }

    public UpdateProcessDefDescActionRequest getUpdateProcessDefDescActionRequest() {
        return updateProcessDefDescActionRequest;
    }

    public void setUpdateProcessDefDescActionRequest(UpdateProcessDefDescActionRequest updateProcessDefDescActionRequest) {
        this.updateProcessDefDescActionRequest = updateProcessDefDescActionRequest;
    }
}
