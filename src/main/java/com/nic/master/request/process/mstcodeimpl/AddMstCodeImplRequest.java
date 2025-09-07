package com.nic.master.request.process.mstcodeimpl;

import com.nic.master.enums.ImplType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddMstCodeImplRequest {

    @NotBlank(message = "QualifiedClass name is required")
    private String qualifiedClassName;

    @NotNull(message = "ImplType is required")
    private ImplType implType;


    private String createdRemarks;
    private String createdUri;

    public String getQualifiedClassName() {
        return qualifiedClassName;
    }

    public void setQualifiedClassName(String qualifiedClassName) {
        this.qualifiedClassName = qualifiedClassName;
    }

    public ImplType getImplType() {
        return implType;
    }

    public void setImplType(ImplType implType) {
        this.implType = implType;
    }

    public String getCreatedRemarks() {
        return createdRemarks;
    }

    public void setCreatedRemarks(String createdRemarks) {
        this.createdRemarks = createdRemarks;
    }

    public String getCreatedUri() {
        return createdUri;
    }

    public void setCreatedUri(String createdUri) {
        this.createdUri = createdUri;
    }
}
