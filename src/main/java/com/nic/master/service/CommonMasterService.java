package com.nic.master.service;
import com.nic.master.param.SelectOptionParam;
import java.util.List;

public interface CommonMasterService {

	List<SelectOptionParam> fetchCommonMaster(String tableCode);

	SelectOptionParam fetchCommonMasterByCode(String tableCode, String codeVal);

}
