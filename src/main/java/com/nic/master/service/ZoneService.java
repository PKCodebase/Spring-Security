package com.nic.master.service;

import com.nic.master.entity.Zone;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.zonerequest.ZoneAddRequest;
import com.nic.master.request.zonerequest.ZoneUpdateRequest;

import java.util.List;

public interface ZoneService {

    StatusParam addZone(ZoneAddRequest zoneAddRequest);

    Zone getZoneByGuid(String zoneGuid);

    List<Zone>getAllZones();

    List<SelectOptionParam> fetchZoneMaster();

    SelectOptionParam fetchZoneMasterByCode(String zoneCode);


    StatusParam updateZoneByGuid(String guid, ZoneUpdateRequest zoneUpdateRequest);


}
