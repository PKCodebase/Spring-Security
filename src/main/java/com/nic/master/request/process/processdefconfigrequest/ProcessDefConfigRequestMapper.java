package com.nic.master.request.process.processdefconfigrequest;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ProcessDefConfigRequestMapper {

    private  String operation;

    private String processDefGuid;

    private String processDefConfigGuid;

    private String processDefConfigCode;

    @Valid
    private AddProcessDefConfigRequest addProcessDefConfigRequest;

    @Valid
    private UpdateProcessDefConfigRequest updateProcessDefConfigRequest;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getProcessDefGuid() {
		return processDefGuid;
	}

	public void setProcessDefGuid(String processDefGuid) {
		this.processDefGuid = processDefGuid;
	}

	public String getProcessDefConfigGuid() {
		return processDefConfigGuid;
	}

	public void setProcessDefConfigGuid(String processDefConfigGuid) {
		this.processDefConfigGuid = processDefConfigGuid;
	}

	public String getProcessDefConfigCode() {
		return processDefConfigCode;
	}

	public void setProcessDefConfigCode(String processDefConfigCode) {
		this.processDefConfigCode = processDefConfigCode;
	}

	public AddProcessDefConfigRequest getAddProcessDefConfigRequest() {
		return addProcessDefConfigRequest;
	}

	public void setAddProcessDefConfigRequest(AddProcessDefConfigRequest addProcessDefConfigRequest) {
		this.addProcessDefConfigRequest = addProcessDefConfigRequest;
	}

	public UpdateProcessDefConfigRequest getUpdateProcessDefConfigRequest() {
		return updateProcessDefConfigRequest;
	}

	public void setUpdateProcessDefConfigRequest(UpdateProcessDefConfigRequest updateProcessDefConfigRequest) {
		this.updateProcessDefConfigRequest = updateProcessDefConfigRequest;
	}

	

}
