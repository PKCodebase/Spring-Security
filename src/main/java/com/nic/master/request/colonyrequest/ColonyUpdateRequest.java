package com.nic.master.request.colonyrequest;

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
public class ColonyUpdateRequest {

    @NotBlank(message = "Colony Code is required")
    @Size(min = 2, max = 20, message = "Colony code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_]+$", message = "Colony code: only uppercase letters, numbers and underscore allowed")
    private String colonyCode;

    @NotBlank(message = "Colony name (English) is required")
    @Size(min = 2, max = 100, message = "Colony name must be between 2-100 characters")
    private String colonyNameEn;

    @Size(max = 100, message = "Colony name (Hindi) cannot exceed 100 characters")
    private String colonyNameHi;

    @Size(max = 100, message = "Colony name (Regional) cannot exceed 100 characters")
    private String colonyNameRl;

    @Size(max = 500, message = "Colony description cannot exceed 500 characters")
    private String colonyDescription;


    @Size(min = 2, max = 50, message = "Modified by must be between 2-50 characters")
    private String modifiedBy;


    @Pattern(regexp = "^(?:\\d{1,3}\\.){3}\\d{1,3}$", message = "Invalid IP address format")
    private String modifiedIpAddr;

    @Size(max = 100, message = "MAC address cannot exceed 100 characters")
    private String modifiedMacAddr;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String modifiedRemarks;

    @Size(max = 200, message = "URI cannot exceed 200 characters")
    private String modifiedUri;
}