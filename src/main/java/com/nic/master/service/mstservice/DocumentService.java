package com.nic.master.service.mstservice;

import com.nic.master.entity.mst.DocumentType;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.request.mst.documentrequest.DocumentAddRequest;
import com.nic.master.request.mst.documentrequest.DocumentUpdateRequest;

import java.util.List;

public interface DocumentService {


    DocumentType getDocumentByGuid(String guid);

    List<SelectOptionParam> fetchDocumentMaster();

    SelectOptionParam fetchDocumentMasterByCode(String documentCode);

    List<DocumentType> getAllDocuments();

    StatusParam  addDocument(DocumentAddRequest documentAddRequest);

    StatusParam updateDocumentByGuid(String guid, DocumentUpdateRequest documentUpdateRequest);
    
    

}
