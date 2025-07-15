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
public class ZoneUpdateRequest {

    @NotBlank(message = "Zone Code is required")
    @Size(min = 2, max = 20, message = "Zone code must be between 2-20 characters")
    @Pattern(regexp = "^[A-Z\\d_]+$", message = "Zone code: only uppercase letters, numbers and underscore allowed")
    private String zoneCode;

    @NotBlank(message = "Zone name English is required")
    @Size(min = 2, max = 100, message = "Zone name must be between 2-100 characters")
    private String zoneNameEn;

    @Size(max = 100, message = "Zone name (Hindi) cannot exceed 100 characters")
    private String zoneNameHi;

    @Size(max = 100, message = "Zone name (Regional) cannot exceed 100 characters")
    private String zoneNameRl;

    @Size(max = 500, message = "Zone description cannot exceed 500 characters")
    private String zoneDescription;

    @Size(max = 50, message = "Wrapper code cannot exceed 50 characters")
    private String wrapperCode;


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