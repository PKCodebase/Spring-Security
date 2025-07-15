package com.nic.master.service;

import com.nic.master.entity.DocumentType;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.request.documentrequest.DocumentAddRequest;
import com.nic.master.request.documentrequest.DocumentUpdateRequest;
import com.nic.master.response.documentresponse.DocumentAddResponse;
import com.nic.master.response.documentresponse.DocumentUpdateResponse;
import java.util.List;

public interface DocumentService {


    DocumentType getDocumentByGuid(String guid);

    DocumentAddResponse addDocument(DocumentAddRequest documentAddServiceRequest);

    DocumentUpdateResponse updateDocumentByGuid(String guid, DocumentUpdateRequest documentUpdateRequest);

    List<SelectOptionParam> fetchDocumentMaster();

    SelectOptionParam fetchDocumentMasterByCode(String documentCode);


    List<DocumentType> getAllDocuments();
    
    

}
