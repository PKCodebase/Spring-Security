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
public class ColonyAddRequest {

    @NotBlank(message = "Colony code is required")
    @Size(min = 4, max = 20, message = "Colony code must be between 4-20 characters")
    @Pattern(regexp = "^[A-Z\\d_]+$", message = "Colony code: only uppercase letters, numbers and underscore allowed")
    private String colonyCode;

    @NotBlank(message = "Colony name (English) is required")
    @Size(min = 3, max = 30, message = "Colony name must be between 3-30 characters")
    private String colonyNameEn;

    @Size(max = 100, message = "Colony name (Hindi) cannot exceed 100 characters")
    private String colonyNameHi;

    @Size(max = 100, message = "Colony name (Regional) cannot exceed 100 characters")
    private String colonyNameRl;

    @Size(max = 500, message = "Colony description cannot exceed 500 characters")
    private String colonyDescription;

    @NotBlank(message = "Created by is required")
    @Size(min = 4, max = 50, message = "Created by must be between 4-50 characters")
    private String createdBy;

    @NotBlank(message = "IP address is required")
    @Pattern(regexp = "^(?:\\d{1,3}\\.){3}\\d{1,3}$", message = "Invalid IP address format")
    private String createdIpAddr;

    @Size(max = 100, message = "MAC address cannot exceed 100 characters")
    private String createdMacAddr;

    @Size(max = 500, message = "Remarks cannot exceed 500 characters")
    private String createdRemarks;

    @Size(max = 200, message = "URI cannot exceed 200 characters")
    private String createdUri;
}