package com.nic.master.request.process.mstcolumntype;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddMstColumnTypeRequest {

    @NotBlank(message = "ColumnType Code is required")
    private String columnTypeCode;

    @NotBlank(message = "Default label is required")
    private String defaultLabel;

    private String createdRemarks;

    private String createdUri;

    public String getColumnTypeCode() {
        return columnTypeCode;
    }

    public void setColumnTypeCode(String columnTypeCode) {
        this.columnTypeCode = columnTypeCode;
    }

    public String getDefaultLabel() {
        return defaultLabel;
    }

    public void setDefaultLabel(String defaultLabel) {
        this.defaultLabel = defaultLabel;
    }

    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
    }

    public String getCreatedUri() {
        return createdUri;
    }

    public void setCreatedUri(String createdUri) {
        this.createdUri = createdUri;
    }
}
