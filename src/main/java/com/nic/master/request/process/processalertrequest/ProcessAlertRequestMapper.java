package com.nic.master.request.process.processalertrequest;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ProcessAlertRequestMapper {

    private String operation;

    private String processAlertGuid;

    private String processDefGuid;

    private String actionTypeGuid;

    @Valid
    private AddProcessAlertRequest addProcessAlertRequest;

    @Valid
    private UpdateProcessAlertRequest updateProcessAlertRequest;

}
