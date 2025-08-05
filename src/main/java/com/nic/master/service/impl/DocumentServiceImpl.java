
package com.nic.master.service.impl;

import com.nic.master.entity.DocumentType;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.DocumentRepository;
import com.nic.master.request.documentrequest.DocumentAddRequest;
import com.nic.master.request.documentrequest.DocumentUpdateRequest;
import com.nic.master.response.documentresponse.DocumentAddResponse;
import com.nic.master.response.documentresponse.DocumentUpdateResponse;
import com.nic.master.service.DocumentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
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
    private  final HttpServletRequest httpServletRequest;


    public DocumentServiceImpl(DocumentRepository documentRepository, HttpServletRequest httpServletRequest) {
        this.documentRepository = documentRepository;
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public List<SelectOptionParam> fetchDocumentMaster() {
        logger.info("Fetching active documents for dropdown...");
        return documentRepository.findByIsActive(true)
                .stream()
                .map(d -> new SelectOptionParam(d.getDocumentGuid(), d.getDocumentCode(), d.getDocumentName()))
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
                .orElseThrow(() -> new RuntimeException("Document not found with GUID: " + guid));
    }



    @Override
    @Transactional
    public DocumentAddResponse addDocument(DocumentAddRequest request) {
        logger.info("Adding new document...");

        try {
            if (documentRepository.existsByDocumentCodeIgnoreCase(request.getDocumentCode())) {
                logger.warn("Duplicate document code: {}", request.getDocumentCode());
                DocumentAddResponse response = new DocumentAddResponse();
                response.setStatus(new StatusParam(false, "Document code already exists: " + request.getDocumentCode()));
                return response;
            }
            String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
            if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
                clientIp = httpServletRequest.getRemoteAddr();
            }

            DocumentType document = new DocumentType();

            document.setDocumentGuid(UUID.randomUUID().toString());
            document.setDocumentCode(request.getDocumentCode());
            document.setDocumentName(request.getDocumentName());
            document.setCreatedBy(request.getCreatedBy());

            document.setCreatedDate(LocalDate.now());
            document.setCreatedIpAddr(clientIp);
            document.setCreatedRemarks(request.getCreatedRemarks());
            document.setIsActive(true);

            documentRepository.save(document);
            logger.info("Document saved successfully with GUID: {}", document.getDocumentGuid());

            DocumentAddResponse response = toAddResponse(document);
            response.setStatus(new StatusParam(true, "Record Added"));
            return response;

        } catch (Exception e) {
            logger.error("Error adding document with code '{}': {}", request.getDocumentCode(), e.getMessage(), e);
            throw new RuntimeException("Error while adding document with code: " + request.getDocumentCode(), e);
        }
    }

    private DocumentAddResponse toAddResponse(DocumentType document) {

        DocumentAddResponse response = new DocumentAddResponse();

        response.setDocumentGuid(document.getDocumentGuid());
        response.setDocumentCode(document.getDocumentCode());
        response.setDocumentName(document.getDocumentName());

        response.setCreatedBy(document.getCreatedBy());
        response.setCreatedIpAddr(document.getCreatedIpAddr());
        response.setCreatedRemarks(document.getCreatedRemarks());
        return response;
    }
    @Override
    public DocumentUpdateResponse updateDocumentByGuid(String guid, DocumentUpdateRequest documentUpdateRequest) {
        logger.info("Updating document with GUID: {}", guid);

        DocumentUpdateResponse documentUpdateResponse = new DocumentUpdateResponse();

        DocumentType document = documentRepository.findById(guid).orElse(null);
        if (document == null) {
            documentUpdateResponse.setStatus(new StatusParam(false, "Document not found with GUID: " + guid));
            return documentUpdateResponse;
        }
        String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getRemoteAddr();
        }

        // Check for duplicate document code if changed
        if (documentUpdateRequest.getDocumentCode() != null
                && !documentUpdateRequest.getDocumentCode().equalsIgnoreCase(document.getDocumentCode())
                && documentRepository.existsByDocumentCodeIgnoreCase(documentUpdateRequest.getDocumentCode())) {
            documentUpdateResponse.setStatus(new StatusParam(false, "Document code already exists: " + documentUpdateRequest.getDocumentCode()));
            return documentUpdateResponse;
        }

        // Only update fields if non-null (to prevent overwriting existing values)
        if (documentUpdateRequest.getDocumentCode() != null) {
            document.setDocumentCode(documentUpdateRequest.getDocumentCode());
        }

        if (documentUpdateRequest.getDocumentName() != null) {
            document.setDocumentName(documentUpdateRequest.getDocumentName());
        }

        if (documentUpdateRequest.getModifiedBy() != null) {
            document.setModifiedBy(documentUpdateRequest.getModifiedBy());
        }

        if (documentUpdateRequest.getModifiedIpAddr() != null) {
            document.setModifiedIpAddr(clientIp);
        }

        if (documentUpdateRequest.getModifiedRemarks() != null) {
            document.setModifiedRemarks(documentUpdateRequest.getModifiedRemarks());
        }

        document.setModifiedDate(LocalDate.now()); // Always update modified date

        documentRepository.save(document);

        logger.info("Document updated successfully for GUID: {}", guid);
        return toUpdateResponse(document);
    }


    private DocumentUpdateResponse toUpdateResponse(DocumentType documentType) {
        DocumentUpdateResponse documentUpdatedResponse = new DocumentUpdateResponse();

        documentUpdatedResponse.setDocumentGuid(documentType.getDocumentGuid());
        documentUpdatedResponse.setDocumentId(documentType.getDocumentId());
        documentUpdatedResponse.setDocumentCode(documentType.getDocumentCode());
        documentUpdatedResponse.setDocumentName(documentType.getDocumentName());

        documentUpdatedResponse.setCreatedBy(documentType.getCreatedBy());
        documentUpdatedResponse.setCreatedIpAddr(documentType.getCreatedIpAddr());
        documentUpdatedResponse.setCreatedRemarks(documentType.getCreatedRemarks());

        documentUpdatedResponse.setModifiedBy(documentType.getModifiedBy());
        documentUpdatedResponse.setModifiedIpAddr(documentType.getModifiedIpAddr());
        documentUpdatedResponse.setModifiedRemarks(documentType.getModifiedRemarks());

        documentUpdatedResponse.setStatus(new StatusParam(true, "Document updated successfully"));
        return documentUpdatedResponse;
    }

}


