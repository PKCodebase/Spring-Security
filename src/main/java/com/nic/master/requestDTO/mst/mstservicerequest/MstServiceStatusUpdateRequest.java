package com.nic.master.requestDTO.mst.mstservicerequest;

import com.nic.master.enums.Status;
import jakarta.validation.constraints.NotNull;


public class MstServiceStatusUpdateRequest {

    @NotNull(message = "Status is mandatory")
    private Status status;

    @NotNull(message = "ModifiedBy is mandatory")
    private String modifiedBy;

    @NotNull(message = "ModifiedIpAddr is mandatory")
    private String modifiedIpAddr;

    private String modifiedRemarks;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedIpAddr() {
        return modifiedIpAddr;
    }

    public void setModifiedIpAddr(String modifiedIpAddr) {
        this.modifiedIpAddr = modifiedIpAddr;
    }

    public String getModifiedRemarks() {
        return modifiedRemarks;
    }

    public void setModifiedRemarks(String modifiedRemarks) {
        this.modifiedRemarks = modifiedRemarks;
    }
}
