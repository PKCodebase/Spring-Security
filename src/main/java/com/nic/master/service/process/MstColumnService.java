package com.nic.master.service.process;

import com.nic.master.entity.process.MstColumnType;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.process.mstcolumntype.AddMstColumnTypeRequest;
import com.nic.master.request.process.mstcolumntype.UpdateMstColumnTypeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MstColumnService  {

    StatusParam addMstColumn(AddMstColumnTypeRequest addMstColumnTypeRequest);

    List<MstColumnType> getAllMstColumns();

    MstColumnType getMstColumnByGuid(String columnTypeGuid);

    SelectOptionParam getMstColumnByCode(String columnTypeCode);

    StatusParam updateMstColumn (String columnTypeGuid, UpdateMstColumnTypeRequest updateMstColumnTypeRequest);
}
