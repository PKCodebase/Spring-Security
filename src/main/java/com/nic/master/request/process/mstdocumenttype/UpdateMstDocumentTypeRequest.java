package com.nic.master.request.process.mstdocumenttype;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateMstDocumentTypeRequest {

    @Pattern(
            regexp = "^[A-Z0-9\\-_]*$",
            message = "DocumentType Code must contain only uppercase letters, numbers, dash (-), or underscore (_)"
    )
    private String documentTypeCode;

    private String documentTypeName;

    private String modifiedRemarks;

    private String modifiedUri;

    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
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
