package com.nic.master.service.process;

import com.nic.master.entity.process.ProcessAlert;
import com.nic.master.param.StatusParam;
import com.nic.master.request.process.processalertrequest.AddProcessAlertRequest;
import com.nic.master.request.process.processalertrequest.UpdateProcessAlertRequest;
import com.nic.master.request.process.processdefdescrequest.AddProcessDefDescRequest;
import com.nic.master.request.process.processdefdescrequest.UpdateProcessDefDescRequest;
import com.nic.master.response.Processdefdescresponse.ProcessDefDescResponse;
import com.nic.master.response.processalertresponse.ProcessAlertResponse;

import java.util.List;

public interface ProcessAlertService {
    StatusParam addProcessAlert(String processDefGuid, String actionTypeGuid, AddProcessAlertRequest addProcessAlertRequest);

    List<ProcessAlertResponse> getAllProcessAlert();

    ProcessAlertResponse getProcessAlertByGuid(String processAlertGuid);

//    SelectOptionParam getByRoleCode(String roleCode);

    StatusParam updateProcessAlert(String processDefGuid, String actionTypeGuid, String processAlertGuid, UpdateProcessAlertRequest updateProcessAlertRequest);


}
