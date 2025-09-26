package com.nic.master.requestDTO.process.mstcolumntype;

import jakarta.validation.Valid;
import lombok.Data;



@Data
public class MstColumnRequestMapper {

    private String operation;

    private String columnTypeGuid;

    private String columnTypeCode;

    @Valid
    private AddMstColumnTypeRequest addMstColumnTypeRequest;


    @Valid
    private UpdateMstColumnTypeRequest updateMstColumnTypeRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getColumnTypeGuid() {
        return columnTypeGuid;
    }

    public void setColumnTypeGuid(String columnTypeGuid) {
        this.columnTypeGuid = columnTypeGuid;
    }

    public String getColumnTypeCode() {
        return columnTypeCode;
    }

    public void setColumnTypeCode(String columnTypeCode) {
        this.columnTypeCode = columnTypeCode;
    }

    public AddMstColumnTypeRequest getAddMstColumnTypeRequest() {
        return addMstColumnTypeRequest;
    }

    public void setAddMstColumnTypeRequest(AddMstColumnTypeRequest addMstColumnTypeRequest) {
        this.addMstColumnTypeRequest = addMstColumnTypeRequest;
    }

    public UpdateMstColumnTypeRequest getUpdateMstColumnTypeRequest() {
        return updateMstColumnTypeRequest;
    }

    public void setUpdateMstColumnTypeRequest(UpdateMstColumnTypeRequest updateMstColumnTypeRequest) {
        this.updateMstColumnTypeRequest = updateMstColumnTypeRequest;
    }
}
