package com.nic.master.response.msturlresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MstUrlResponse {

    private String urlTypeGuid;

    private String urlTypeCode;

    private String urlTypeName;

    private String createdBy;

    private LocalDateTime createdDate;

    private String createdIpAddr;

    private String createdRemarks;

    private String modifiedBy;

    private LocalDateTime modifiedDate;

    private String modifiedIpAddr;

    private String modifiedRemarks;

    @JsonProperty("isActive")   // ensures JSON key is "isActive"
    private Boolean active;     // ✅ renamed (not "isActive")
}
