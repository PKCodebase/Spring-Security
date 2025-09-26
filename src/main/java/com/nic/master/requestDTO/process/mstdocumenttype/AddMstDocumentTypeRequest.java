package com.nic.master.requestDTO.process.mstdocumenttype;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddMstDocumentTypeRequest {

    @NotBlank(message = "DocumentType Code is required")
    @Pattern(
            regexp = "^[A-Z0-9\\-_]*$",
            message = "DocumentType Code must contain only uppercase letters, numbers, dash (-), or underscore (_)"
    )
    private String documentTypeCode;

    @NotBlank(message = "DocumentType Name is required")
    private String documentTypeName;

    private String createdRemarks;

    private String createdUri;

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
