
package com.nic.master.service.mstservice.impl;

import com.nic.master.entity.mst.DocumentType;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.mst.DocumentRepository;
import com.nic.master.service.mstservice.DocumentService;
import com.nic.master.request.mst.documentrequest.DocumentAddRequest;
import com.nic.master.request.mst.documentrequest.DocumentUpdateRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

    private final DocumentRepository documentRepository;
    private final HttpServletRequest httpServletRequest;
    private final ModelMapper modelMapper;


    public DocumentServiceImpl(DocumentRepository documentRepository, HttpServletRequest httpServletRequest, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.httpServletRequest = httpServletRequest;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<SelectOptionParam> fetchDocumentMaster() {
        logger.info("Fetching active documents for dropdown...");
        return documentRepository.findByIsActive(true)
                .stream()
                .map(document -> modelMapper.map(document, SelectOptionParam.class))
                .toList();
    }

    @Override
    public SelectOptionParam fetchDocumentMasterByCode(String documentCode) {
        return documentRepository.findByDocumentCode(documentCode)
                .map(document -> new SelectOptionParam(
                        document.getDocumentGuid(),
                        document.getDocumentCode(),
                        document.getDocumentName()
                ))
                .orElseThrow(() -> new RuntimeException("Document not found with code: " + documentCode));
    }

    //Get All Documents
    @Override
    public List<DocumentType> getAllDocuments() {
        logger.info("Fetching paginated documents...");
        return documentRepository.findAll();
    }


    //Get Document By Guid
    @Override
    public DocumentType getDocumentByGuid(String guid) {
        logger.info("Fetching document by GUID: {}", guid);
        return documentRepository.findById(guid)
                .orElseThrow(() -> new RuntimeException("Document not found with GUID : " + guid));
    }


    @Override
    public StatusParam addDocument(DocumentAddRequest documentAddRequest) {
        logger.info("Adding new document... ");
        try {
            if (isDuplicateCode(documentAddRequest.getDocumentCode())) {
                return new StatusParam(false, "Document code already exists : " + documentAddRequest.getDocumentCode());
            }

            // Map request to entity
            DocumentType document = modelMapper.map(documentAddRequest, DocumentType.class);

            document.setDocumentGuid(UUID.randomUUID().toString());
            document.setCreatedDate(LocalDate.now());
            document.setCreatedIpAddr(getClientIp());
            document.setIsActive(true);
            document.setCreatedBy("SYSTEM");

            // Save the document
            documentRepository.save(document);
            logger.info("Document saved successfully with GUID: {}", document.getDocumentGuid());
            return new StatusParam(true, "Saved Successfully");
        } catch (Exception ex) {
            logger.error("Error adding document with code '{}': {}", documentAddRequest.getDocumentCode(), ex.getMessage(), ex);
            throw new RuntimeException("Error while adding document with code: " + documentAddRequest.getDocumentCode(), ex);
        }


    }

    @Override
    public StatusParam updateDocumentByGuid(String guid, DocumentUpdateRequest documentUpdateRequest) {
        logger.info("Updating document with GUID: {}",guid);

        try{

            // Check if document Guid exists
//            DocumentType document = documentRepository.findById(guid).orElse(null);
//            if(document == null){
//                status.setStatus(false);
//                status.setMessage("Document not found with GUID: " + guid);
//                return status;
//            }

            DocumentType existingDoc = documentRepository.findById(guid)
                    .orElseThrow(() -> new RuntimeException("Document not found with GUID: " + guid));



            // Check if the document code is being changed and if it already exists
            if (documentUpdateRequest.getDocumentCode() != null &&
                    !documentUpdateRequest.getDocumentCode().equalsIgnoreCase(existingDoc.getDocumentCode()) &&
                    isDuplicateCode(documentUpdateRequest.getDocumentCode())) {
                return new StatusParam(false, "Document code already exists: " + documentUpdateRequest.getDocumentCode());
            }

            // Only update fields if non-null (to prevent overwriting existing values)
            modelMapper.map(documentUpdateRequest, existingDoc);
            existingDoc.setModifiedDate(LocalDate.now()); // Always update modified date
            existingDoc.setModifiedIpAddr(getClientIp());
            existingDoc.setModifiedBy("SYSTEM");

            // Save the updated document
            documentRepository.save(existingDoc);
            logger.info("Document updated successfully for GUID: {}", guid);
            return new StatusParam(true, "Document updated successfully");
        }catch (Exception ex){
            logger.error("Error updating document with GUID '{}': {}", guid, ex.getMessage(), ex);
            throw new RuntimeException("Error while updating document with GUID: " + guid, ex);
        }

    }

    private boolean isDuplicateCode(String code) {
        return documentRepository.existsByDocumentCodeIgnoreCase(code);
    }
    private String getClientIp() {
        String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getRemoteAddr();
        }
        return clientIp;
    }

}


