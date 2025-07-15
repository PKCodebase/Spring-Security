package com.nic.master.request.zonerequest;

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
public class ZoneAddRequest {

    @NotBlank(message = "Zone code is required")
    @Size(min = 4, max = 20, message = "Zone code must be between 4-20 characters")
    @Pattern(regexp = "^[A-Z\\d_-]+$", message = "Zone code: only uppercase letters, numbers,hyphens(-) and underscore(_) allowed")
    private String zoneCode;

    @NotBlank(message = "Zone name (English) is required")
    @Size(min = 3, max = 30, message = "Zone name must be between 3-30 characters")
    private String zoneNameEn;

    @Size(max = 100, message = "Zone name (Hindi) cannot exceed 100 characters")
    private String zoneNameHi;

    @Size(max = 100, message = "Zone name (Regional) cannot exceed 100 characters")
    private String zoneNameRl;

    @Size(max = 500, message = "Zone description cannot exceed 500 characters")
    private String zoneDescription;

    @Size(max = 50, message = "Wrapper code cannot exceed 50 characters")
    private String wrapperCode;

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