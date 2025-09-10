package com.nic.master.service.process;

import com.nic.master.entity.process.ProcessDef;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.process.processdefrequest.AddProcessDefRequest;
import com.nic.master.request.process.processdefrequest.UpdateProcessDefRequest;
import com.nic.master.response.processdefresponse.ProcessDefResponse;

import java.util.List;

public interface ProcessDefService {

    StatusParam addProcessDef(String processTypeGuid, AddProcessDefRequest addProcessDefRequest);

    List<ProcessDefResponse> getAllProcessDef();

    ProcessDefResponse getProcessByGuid(String processDefGuid);

    SelectOptionParam getProcessDefByCode(String processDefCode);

   StatusParam updateProcessDef(String processTypeGuid,String processDefGuid, UpdateProcessDefRequest updateProcessDefRequest);
}
