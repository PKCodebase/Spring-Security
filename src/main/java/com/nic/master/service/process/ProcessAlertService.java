package com.nic.master.service.process;

import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.processalertrequest.AddProcessAlertRequest;
import com.nic.master.requestDTO.process.processalertrequest.UpdateProcessAlertRequest;
import com.nic.master.responseDTO.processalertresponse.ProcessAlertResponse;

import java.util.List;

public interface ProcessAlertService {
    StatusParam addProcessAlert(String processDefGuid, String actionTypeGuid, AddProcessAlertRequest addProcessAlertRequest);

    List<ProcessAlertResponse> getAllProcessAlert();

    ProcessAlertResponse getProcessAlertByGuid(String processAlertGuid);

//    SelectOptionParam getByRoleCode(String roleCode);

    StatusParam updateProcessAlert(String processDefGuid, String actionTypeGuid, String processAlertGuid, UpdateProcessAlertRequest updateProcessAlertRequest);


}
