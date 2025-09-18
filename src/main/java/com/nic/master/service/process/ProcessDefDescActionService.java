package com.nic.master.service.process;

import com.nic.master.param.StatusParam;
import com.nic.master.request.process.processdefdescriptionaction.AddProcessDefDescActionRequest;
import com.nic.master.request.process.processdefdescriptionaction.UpdateProcessDefDescActionRequest;
import com.nic.master.response.processdefdescactionresponse.ProcessDefDescActionResponse;

import java.util.List;

public interface ProcessDefDescActionService {

    StatusParam addDescAction(String processDefDescGuid, String actionTypeGuid, String sectionTypeGuid, AddProcessDefDescActionRequest addProcessDefDescActionRequest);

    List<ProcessDefDescActionResponse> getAllDescActions();

    ProcessDefDescActionResponse getDescActionByGuid(String processDefDescActionGuid);

    StatusParam updateDescAction(String processDefDescGuid, String actionTypeGuid, String sectionTypeGuid,String processDefDescActionGuid, UpdateProcessDefDescActionRequest updateProcessDefDescActionRequest);
}
