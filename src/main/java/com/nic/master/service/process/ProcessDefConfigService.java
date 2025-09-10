package com.nic.master.service.process;

import com.nic.master.entity.process.ProcessDefConfig;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.process.processdefconfigrequest.AddProcessDefConfigRequest;
import com.nic.master.request.process.processdefconfigrequest.UpdateProcessDefConfigRequest;
import com.nic.master.response.processdefconfigresponse.ProcessDefConfigResponse;

import java.util.List;

public interface ProcessDefConfigService {

    StatusParam addProcessDefConfig(String processDefGuid, AddProcessDefConfigRequest addProcessDefConfigRequest);

    List<ProcessDefConfig> getAllProcessDefConfig();

//    SelectOptionParam getProcessDefConfigByCode(String processDefConfigCode);

    ProcessDefConfigResponse getProcessDefConfigByGuid(String processDefConfigGuid);

    StatusParam updateProcessDefConfig(String processDefGuid, String processDefConfigGuid, UpdateProcessDefConfigRequest updateProcessDefConfigRequest);
}
