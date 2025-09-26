package com.nic.master.requestDTO.process.mstactiontype;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateMstActionRequest {

    @Size(max=100, message = "MstAction Code cannot exceed 100 characters.")
    private String actionTypeCode;

    private String actionTypeName;


    private String modifiedRemarks;

    private String modifiedUri;


    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public String getActionTypeName() {
        return actionTypeName;
    }

    public void setActionTypeName(String actionTypeName) {
        this.actionTypeName = actionTypeName;
    }

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }

    public String getModifiedUri() {
        return modifiedUri;
    }

    public void setModifiedUri(String modifiedUri) {
        this.modifiedUri = modifiedUri;
    }
}
