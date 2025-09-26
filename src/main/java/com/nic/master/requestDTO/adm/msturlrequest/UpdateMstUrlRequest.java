package com.nic.master.requestDTO.adm.msturlrequest;

import lombok.Data;

@Data
public class UpdateMstUrlRequest {

    private String urlTypeCode;

    private String urlTypeName;


    private String modifiedRemarks;

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
    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }
}
