package com.nic.master.request.adm.msturlrequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.aspectj.bridge.IMessage;

@Data
public class AddMstUrlRequest {

    @NotBlank(message = "UrlType Code is mandatory")
    @Size(max=255, message="UrlType  code cannot exceed 255 characters")
    private String urlTypeCode;

    @NotBlank(message = "UrlType Name is mandatory")
    @Size(max=255, message="UrlType  Name cannot exceed 255 characters")
    private String urlTypeName;

    private String createdRemarks;

    public String getUrlTypeCode() {
        return urlTypeCode;
    }

    public void setUrlTypeCode(String urlTypeCode) {
        this.urlTypeCode = urlTypeCode;
    }

    public String getUrlTypeName() {
        return urlTypeName;
    }

    public void setUrlTypeName(String urlTypeName) {
        this.urlTypeName = urlTypeName;
    }

    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
    }
}
