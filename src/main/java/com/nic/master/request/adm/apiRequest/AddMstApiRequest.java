package com.nic.master.request.adm.apiRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;

@Data
public class AddMstApiRequest {

    @NotBlank(message = "ApiCode in mandatory")
    @Size(max=255 , message= "Api  code cannot exceed 255 characters")
    private  String apiCode;

    private String url;

    private Map<String, Object> subUrlJson;

    private  String createRemarks;

    private String microserviceGuid;

    private  String  urlTypeGuid;

    public String getMicroserviceGuid() {
        return microserviceGuid;
    }

    public void setMicroserviceGuid(String microserviceGuid) {
        this.microserviceGuid = microserviceGuid;
    }

    public String getUrlTypeGuid() {
        return urlTypeGuid;
    }

    public void setUrlTypeGuid(String urlTypeGuid) {
        this.urlTypeGuid = urlTypeGuid;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }


    public Map<String, Object> getSubUrlJson() {
        return subUrlJson;
    }

    public void setSubUrlJson(Map<String, Object> subUrlJson) {
        this.subUrlJson = subUrlJson;
    }

    public String getCreateRemarks() {
        return createRemarks;
    }

    public void setCreateRemarks(String createRemarks) {
        this.createRemarks = createRemarks;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
