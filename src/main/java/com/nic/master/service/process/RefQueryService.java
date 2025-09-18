package com.nic.master.service.process;

import com.nic.master.entity.process.RefQuery;
import com.nic.master.param.StatusParam;

public interface RefQueryService {
    StatusParam addRefQuery(String processDefGuid, String processDefDescGuid, String sectionTypeGuid, RefQuery refQuery);
}
