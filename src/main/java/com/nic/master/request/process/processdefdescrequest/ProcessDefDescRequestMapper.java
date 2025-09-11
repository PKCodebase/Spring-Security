package com.nic.master.request.process.processdefdescrequest;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ProcessDefDescRequestMapper {

    private String operation;

    private String processDefDescGuid;

    private String processDefGuid;

    private String sectionTypeGuid;

    private String roleCode;

    @Valid
    private AddProcessDefDescRequest addProcessDefDescRequest;

    @Valid
    private UpdateProcessDefDescRequest updateProcessDefDescRequest;
}
