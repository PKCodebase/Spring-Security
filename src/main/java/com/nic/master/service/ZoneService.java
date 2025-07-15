package com.nic.master.service;

import com.nic.master.entity.Zone;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.request.zonerequest.ZoneAddRequest;
import com.nic.master.request.zonerequest.ZoneUpdateRequest;
import com.nic.master.response.zoneresponse.ZoneAddResponse;
import com.nic.master.response.zoneresponse.ZoneUpdateResponse;
import java.util.List;

public interface ZoneService {

    ZoneAddResponse addZone(ZoneAddRequest zoneAddRequest);

    Zone getZoneByGuid(String zoneGuid);

    List<Zone>getAllZones();

    ZoneUpdateResponse updateZoneByGuid(String guid, ZoneUpdateRequest zoneUpdateRequest);

    List<SelectOptionParam> fetchZoneMaster();

    SelectOptionParam fetchZoneMasterByCode(String zoneCode);


}
