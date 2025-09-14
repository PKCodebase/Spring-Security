package com.nic.master.request.process.processedrequestrole;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ProcessRequestRoleMapper {

    private String operation;

    private String processDefGuid;

    private String processedRequestRoleGuid;

    private String approveProcessedRoleCode;

    private String rejectProcessedRoleCode;

    private String closeProcessedRoleCode;

    private  String processUserRoleCode;

    @Valid
    private AddProcessedRoleRequest addProcessedRoleRequest;

    @Valid
    private UpdateProcessedRoleRequest updateProcessedRoleRequest;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getProcessDefGuid() {
        return processDefGuid;
    }

    public void setProcessDefGuid(String processDefGuid) {
        this.processDefGuid = processDefGuid;
    }

    public String getProcessedRequestRoleGuid() {
        return processedRequestRoleGuid;
    }

    public void setProcessedRequestRoleGuid(String processedRequestRoleGuid) {
        this.processedRequestRoleGuid = processedRequestRoleGuid;
    }

    public String getApproveProcessedRoleCode() {
        return approveProcessedRoleCode;
    }

    public void setApproveProcessedRoleCode(String approveProcessedRoleCode) {
        this.approveProcessedRoleCode = approveProcessedRoleCode;
    }

    public String getRejectProcessedRoleCode() {
        return rejectProcessedRoleCode;
    }

    public void setRejectProcessedRoleCode(String rejectProcessedRoleCode) {
        this.rejectProcessedRoleCode = rejectProcessedRoleCode;
    }

    public String getCloseProcessedRoleCode() {
        return closeProcessedRoleCode;
    }

    public void setCloseProcessedRoleCode(String closeProcessedRoleCode) {
        this.closeProcessedRoleCode = closeProcessedRoleCode;
    }

    public String getProcessUserRoleCode() {
        return processUserRoleCode;
    }

    public void setProcessUserRoleCode(String processUserRoleCode) {
        this.processUserRoleCode = processUserRoleCode;
    }

    public AddProcessedRoleRequest getAddProcessedRoleRequest() {
        return addProcessedRoleRequest;
    }

    public void setAddProcessedRoleRequest(AddProcessedRoleRequest addProcessedRoleRequest) {
        this.addProcessedRoleRequest = addProcessedRoleRequest;
    }

    public UpdateProcessedRoleRequest getUpdateProcessedRoleRequest() {
        return updateProcessedRoleRequest;
    }

    public void setUpdateProcessedRoleRequest(UpdateProcessedRoleRequest updateProcessedRoleRequest) {
        this.updateProcessedRoleRequest = updateProcessedRoleRequest;
    }


}
