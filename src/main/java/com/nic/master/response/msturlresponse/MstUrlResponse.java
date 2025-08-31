package com.nic.master.response.msturlresponse;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MstUrlResponse {

    private String urlTypeGuid;
    private String urlTypeCode;
    private String urlTypeName;
    private Boolean isActive;
    private String createdBy;
    private LocalDateTime createdDate;
}
