package com.nic.master.service.process;


import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.process.reftemplate.AddRefTemplateRequest;
import com.nic.master.request.process.reftemplate.UpdateRefTemplateRequest;
import com.nic.master.response.refresponse.RefTemplateResponse;

import java.util.List;

public interface RefTemplateService {
    StatusParam addRefTemplate(String processDefGuid,String sectionTypeGuid,String actionTypeGuid,AddRefTemplateRequest addRefTemplateRequest);
    List<RefTemplateResponse> getAllRefTemplates();
    RefTemplateResponse getRefTemplateByGuid(String refTemplateGuid);
    SelectOptionParam getRefTemplateByCode(String refTemplateCode);
    StatusParam updateRefTemplate(String processDefGuid,String sectionTypeGuid,String actionTypeGuid,String refTemplateGuid, UpdateRefTemplateRequest updateRefTemplateRequest);
}
