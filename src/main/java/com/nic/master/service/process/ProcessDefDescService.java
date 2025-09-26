package com.nic.master.service.process;

import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.processdefdescrequest.AddProcessDefDescRequest;
import com.nic.master.requestDTO.process.processdefdescrequest.UpdateProcessDefDescRequest;
import com.nic.master.responseDTO.Processdefdescresponse.ProcessDefDescResponse;

import java.util.List;

public interface ProcessDefDescService {
    StatusParam addProcessDefDesc(String processDefGuid, String sectionTypeGuid, AddProcessDefDescRequest addProcessDefDescRequest);

    List<ProcessDefDescResponse> getAllProcessDefDesc();

    ProcessDefDescResponse getProcessDefDescByGuid(String processDefDescGuid);

//    SelectOptionParam getByRoleCode(String roleCode);

    StatusParam updateProcessDefDesc(String processDefGuid, String sectionTypeGuid, String processDefDescGuid, UpdateProcessDefDescRequest updateProcessDefDescRequest);




}
