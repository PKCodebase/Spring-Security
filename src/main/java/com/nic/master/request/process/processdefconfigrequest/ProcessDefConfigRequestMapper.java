package com.nic.master.request.process.processdefconfigrequest;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ProcessDefConfigRequestMapper {

    private  String operation;

    private String processDefGuid;

    private String processDefConfigGuid;

    private String processDefConfigCode;

    @Valid
    private AddProcessDefConfigRequest addProcessDefConfigRequest;

    @Valid
    private UpdateProcessDefConfigRequest updateProcessDefConfigRequest;

}
