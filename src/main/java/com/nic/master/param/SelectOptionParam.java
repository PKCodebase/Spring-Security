package com.nic.master.param;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class SelectOptionParam {

	private String guid;
	private String code;
	private String name;
	
	@JsonInclude(Include.NON_NULL)
	private String value;
	
	public SelectOptionParam() {
		super();
	}
	
	public SelectOptionParam(String guid, String code, String name) {
		super();
		this.guid = guid;
		this.code = code;
		this.name = name;
	}
	
	public SelectOptionParam(String guid, String code, String name, String value) {
		super();
		this.guid = guid;
		this.code = code;
		this.name = name;
		this.value = value;
	}

	public String getGuid() {
		return guid;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
