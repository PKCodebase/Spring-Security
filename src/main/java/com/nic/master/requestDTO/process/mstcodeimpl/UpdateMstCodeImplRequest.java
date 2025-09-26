package com.nic.master.requestDTO.process.mstcodeimpl;

import com.nic.master.enums.ImplType;
import lombok.Data;

@Data
public class UpdateMstCodeImplRequest {

    private String qualifiedClassName;
    private ImplType implType;

    private String modifiedUri;

    private String modifiedRemarks;

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

    public String getModifiedUri() {
        return modifiedUri;
    }

    public void setModifiedUri(String modifiedUri) {
        this.modifiedUri = modifiedUri;
    }

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }
}
