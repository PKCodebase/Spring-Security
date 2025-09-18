package com.nic.master.request.process.processdefdescrequest;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ProcessDefDescRequestMapper {

    private String operation;

    private String processDefDescGuid;

    private String processDefGuid;

    private String sectionTypeGuid;

    private String roleCode;

    @Valid
    private AddProcessDefDescRequest addProcessDefDescRequest;

    @Valid
    private UpdateProcessDefDescRequest updateProcessDefDescRequest;

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

	public String getProcessDefGuid() {
		return processDefGuid;
	}

	public void setProcessDefGuid(String processDefGuid) {
		this.processDefGuid = processDefGuid;
	}

	public String getSectionTypeGuid() {
		return sectionTypeGuid;
	}

	public void setSectionTypeGuid(String sectionTypeGuid) {
		this.sectionTypeGuid = sectionTypeGuid;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public AddProcessDefDescRequest getAddProcessDefDescRequest() {
		return addProcessDefDescRequest;
	}

	public void setAddProcessDefDescRequest(AddProcessDefDescRequest addProcessDefDescRequest) {
		this.addProcessDefDescRequest = addProcessDefDescRequest;
	}

	public UpdateProcessDefDescRequest getUpdateProcessDefDescRequest() {
		return updateProcessDefDescRequest;
	}

	public void setUpdateProcessDefDescRequest(UpdateProcessDefDescRequest updateProcessDefDescRequest) {
		this.updateProcessDefDescRequest = updateProcessDefDescRequest;
	}

	
}
