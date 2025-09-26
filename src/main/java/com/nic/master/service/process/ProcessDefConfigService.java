package com.nic.master.service.process;

import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.processdefconfigrequest.AddProcessDefConfigRequest;
import com.nic.master.requestDTO.process.processdefconfigrequest.UpdateProcessDefConfigRequest;
import com.nic.master.responseDTO.processdefconfigresponse.ProcessDefConfigResponse;

import java.util.List;

public interface ProcessDefConfigService {

    StatusParam addProcessDefConfig(String processDefGuid, AddProcessDefConfigRequest addProcessDefConfigRequest);

    List<ProcessDefConfigResponse> getAllProcessDefConfig();

//    SelectOptionParam getProcessDefConfigByCode(String processDefConfigCode);

    ProcessDefConfigResponse getProcessDefConfigByGuid(String processDefConfigGuid);

    StatusParam updateProcessDefConfig(String processDefGuid, String processDefConfigGuid, UpdateProcessDefConfigRequest updateProcessDefConfigRequest);
}
