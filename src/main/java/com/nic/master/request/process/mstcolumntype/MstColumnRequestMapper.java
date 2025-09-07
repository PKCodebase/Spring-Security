package com.nic.master.request.process.mstcolumntype;

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
}
