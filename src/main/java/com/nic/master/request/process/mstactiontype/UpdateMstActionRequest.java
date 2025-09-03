package com.nic.master.request.process.mstactiontype;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateMstActionRequest {

    @Size(max=100, message = "MstAction Code cannot exceed 100 characters.")
    private String actionTypeCode;

    private String actionTypeName;


    private String modifiedRemarks;

    private String modifiedUri;



}
