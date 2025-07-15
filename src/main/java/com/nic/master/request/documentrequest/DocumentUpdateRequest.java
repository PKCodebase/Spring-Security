package com.nic.master.request.documentrequest;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Document code is required")
    @Size(min = 2, max = 20, message = "Document code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Document code: only uppercase letters, numbers,hyphens (-) and underscore(_) allowed")
    private String documentCode;

    @Size(min = 2, max = 100, message = "Document name must be between 2-100 characters")
    private String documentName;


    @Size(min = 2, max = 50, message = "Modified by must be between 2-50 characters")
    private String modifiedBy;

    @Pattern(regexp = "^(?:\\d{1,3}\\.){3}\\d{1,3}$", message = "Invalid IP address format")
    private String modifiedIpAddr;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String modifiedRemarks;
}
