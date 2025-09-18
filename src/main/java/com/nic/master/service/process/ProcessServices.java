package com.nic.master.service.process;

import com.nic.master.param.StatusParam;
import com.nic.master.request.process.processservice.AddProcessServiceRequest;
import com.nic.master.request.process.processservice.UpdateProcessServiceRequest;
import com.nic.master.response.processServiceResponse.ProcessServiceResponse;

import java.util.List;

public interface ProcessServices {

    StatusParam addProcessService(String processDefGuid, AddProcessServiceRequest addProcessServiceRequest);

    List<ProcessServiceResponse> getAllProcessServices();

    ProcessServiceResponse getProcessServiceByGuid(String processServiceGuid);

    StatusParam updateProcessService(String processDefGuid,String processServiceGuid, UpdateProcessServiceRequest updateProcessServiceRequest);


}
