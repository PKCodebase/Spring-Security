package com.nic.master.service.process;

import com.nic.master.entity.process.MstDocumentType;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.requestDTO.process.mstdocumenttype.AddMstDocumentTypeRequest;
import com.nic.master.requestDTO.process.mstdocumenttype.UpdateMstDocumentTypeRequest;

import java.util.List;

public interface MstDocumentTypeService {

    StatusParam addMstDocument(AddMstDocumentTypeRequest addMstDocumentTypeRequest);

    List<MstDocumentType> getAllDocuments();

    MstDocumentType getDocumentTypeByGuid(String documentTypeGuid);

    SelectOptionParam getDocumentTypeByCode(String documentTypeCode);

    StatusParam updateDocument(String documentTypeGuid, UpdateMstDocumentTypeRequest updateMstDocumentTypeRequest);
}
