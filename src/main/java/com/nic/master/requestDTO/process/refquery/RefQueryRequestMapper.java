package com.nic.master.requestDTO.process.refquery;

import com.nic.master.entity.process.RefQuery;
import jakarta.validation.Valid;
import lombok.Data;

@Data
public class RefQueryRequestMapper {

    private String operation;

    @Valid
    private RefQuery refQuery;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public RefQuery getRefQuery() {
		return refQuery;
	}

	public void setRefQuery(RefQuery refQuery) {
		this.refQuery = refQuery;
	}

	

}
