package com.nic.master.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class StatusParam {

	private boolean status;
	private String message;
	
	@JsonInclude(Include.NON_NULL)
	private String value;
	
	public StatusParam(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public StatusParam(boolean status, String message, String value) {
		super();
		this.status = status;
		this.message = message;
		this.value = value;
	}

	public StatusParam() {
	}


	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
