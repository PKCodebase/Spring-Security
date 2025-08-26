package com.nic.master.request.mst.documentrequest;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentUpdateRequest {


    @Size(min = 2, max = 20, message = "Document code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Document code: only uppercase letters, numbers,hyphens (-) and underscore(_) allowed")
    private String documentCode;

    @Size(min = 2, max = 100, message = "Document name must be between 2-100 characters")
    private String documentName;

//    @NotNull(message = "Modified by cannot be null")
//    @NotEmpty(message = "Modified by is required")
//    @Size(min = 2, max = 50, message = "Modified by must be between 2-50 characters")
    private String modifiedBy;

    @Pattern(regexp = "^(?:\\d{1,3}\\.){3}\\d{1,3}$", message = "Invalid IP address format")
    private String modifiedIpAddr;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String modifiedRemarks;

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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedIpAddr() {
        return modifiedIpAddr;
    }

    public void setModifiedIpAddr(String modifiedIpAddr) {
        this.modifiedIpAddr = modifiedIpAddr;
    }

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }
}
