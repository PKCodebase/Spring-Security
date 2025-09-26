package com.nic.master.requestDTO.process.processalertrequest;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ProcessAlertRequestMapper {

    private String operation;

    private String processAlertGuid;

    private String processDefGuid;

    private String actionTypeGuid;

    @Valid
    private AddProcessAlertRequest addProcessAlertRequest;

    @Valid
    private UpdateProcessAlertRequest updateProcessAlertRequest;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getProcessAlertGuid() {
		return processAlertGuid;
	}

	public void setProcessAlertGuid(String processAlertGuid) {
		this.processAlertGuid = processAlertGuid;
	}

	public String getProcessDefGuid() {
		return processDefGuid;
	}

	public void setProcessDefGuid(String processDefGuid) {
		this.processDefGuid = processDefGuid;
	}

	public String getActionTypeGuid() {
		return actionTypeGuid;
	}

	public void setActionTypeGuid(String actionTypeGuid) {
		this.actionTypeGuid = actionTypeGuid;
	}

	public AddProcessAlertRequest getAddProcessAlertRequest() {
		return addProcessAlertRequest;
	}

	public void setAddProcessAlertRequest(AddProcessAlertRequest addProcessAlertRequest) {
		this.addProcessAlertRequest = addProcessAlertRequest;
	}

	public UpdateProcessAlertRequest getUpdateProcessAlertRequest() {
		return updateProcessAlertRequest;
	}

	public void setUpdateProcessAlertRequest(UpdateProcessAlertRequest updateProcessAlertRequest) {
		this.updateProcessAlertRequest = updateProcessAlertRequest;
	}

	

}
