package com.nic.master.request.documentrequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentAddRequest {

    @NotBlank(message = "Document code is required")
    @Size(min = 4, max = 20, message = "Document code must be between 4 and 20 characters")
    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Document code must contain only uppercase letters, digits, hyphens (-), or underscores (_)")
    private String documentCode;

    @NotBlank(message = "Document name is required")
    @Size(min = 3, max = 30, message = "Document name must be between 3-30 characters")
    private String documentName;

    @NotBlank(message = "Created by is required")
    @Size(min = 4, max = 50, message = "Created by must be between 4-50 characters")
    private String createdBy;

//    @NotBlank(message = "IP address is required")
//    @Pattern(regexp = "^(?:\\d{1,3}\\.){3}\\d{1,3}$", message = "Invalid IP address format")
    private String createdIpAddr;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String createdRemarks;

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedIpAddr() {
        return createdIpAddr;
    }

    public void setCreatedIpAddr(String createdIpAddr) {
        this.createdIpAddr = createdIpAddr;
    }

    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
    }
}
