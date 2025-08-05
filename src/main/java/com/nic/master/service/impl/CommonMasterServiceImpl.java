	package com.nic.master.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.nic.master.enums.Status;
import com.nic.master.param.*;
import com.nic.master.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nic.master.repository.MstServiceRepository;

@Service

public class CommonMasterServiceImpl implements CommonMasterService {

	private static final Logger logger = LoggerFactory.getLogger(CommonMasterServiceImpl.class);
	private final MstServiceRepository mstServiceRepository;
	private final DocumentService documentService;
	private final ZoneService zoneService;
	private final WardService wardService;
	private final ColonyService colonyService;

    public CommonMasterServiceImpl(MstServiceRepository mstServiceRepository, DocumentService documentService, ZoneService zoneService, WardService wardService, ColonyService colonyService) {
        this.mstServiceRepository = mstServiceRepository;
        this.documentService = documentService;
        this.zoneService = zoneService;
        this.wardService = wardService;
        this.colonyService = colonyService;
    }

    @Override
	public List<SelectOptionParam> fetchCommonMaster(String tableCode) {
		List<SelectOptionParam> lst = new ArrayList<>();

		switch (tableCode) {

			case "SERVICE": {
				lst = fetchServiceMaster();
				break;
			}

			case "DOCUMENT":{
				lst = documentService.fetchDocumentMaster();
				break;
			}

			case "WARD":{
				lst = wardService.fetchWardMaster();
				break;
			}
			case "ZONE":{
				lst = zoneService.fetchZoneMaster();
				break;
			}
			case "COLONY":{
				lst = colonyService.fetchColonyMaster();
				break;
			}

			default:
				throw new IllegalArgumentException("Unexpected value: " + tableCode);
		}

		return lst;
	}

	private List<SelectOptionParam> fetchServiceMaster() {
		return mstServiceRepository.findByStatus(Status.ACTIVE)
				.stream()
				.map(s -> new SelectOptionParam(s.getServiceGuid(), s.getServiceCode(), s.getServiceName()))
				.toList();
	}




	@Override
	public SelectOptionParam fetchCommonMasterByCode(String tableCode, String codeVal) {

		SelectOptionParam selectOptionParam = new SelectOptionParam();

		switch (tableCode) {
			case "SERVICE": {
				selectOptionParam = fetchServiceMasterByCode(codeVal);
				break;
			}
			case "DOCUMENT":{
				selectOptionParam = documentService.fetchDocumentMasterByCode(codeVal);
				break;
			}
			case "WARD":{
				selectOptionParam = wardService.fetchWardMasterByCode(codeVal);
				break;
			}
			case "ZONE":{
				selectOptionParam = zoneService.fetchZoneMasterByCode(codeVal);
				break;
			}
			case "COLONY":{
				selectOptionParam = colonyService.fetchColonyMasterByCode(codeVal);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + tableCode);
		}

		return selectOptionParam;
	}



	private SelectOptionParam fetchServiceMasterByCode(String codeVal) {

		SelectOptionParam selectOptionParam = new SelectOptionParam();
		List<SelectOptionParam> lst = mstServiceRepository.findByServiceCode(codeVal).stream()
				.map(o -> new SelectOptionParam(o.getServiceGuid(), o.getProcessCode(),
						o.getServiceName())).toList();
		if (lst != null && !lst.isEmpty()) {
			return lst.get(0);
		}
		return selectOptionParam;
	}



}

