package com.nic.master.service.mstservice.impl;

import com.nic.master.entity.mst.DocumentType;
import com.nic.master.exception.IllegalArgumentException;
import com.nic.master.exception.ResourceNotFoundException;
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
import java.time.LocalDateTime;
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
        List<SelectOptionParam> docs = documentRepository.findByIsActive(true)
                .stream()
                .map(document -> modelMapper.map(document, SelectOptionParam.class))
                .toList();
        logger.debug("Fetched {} active documents", docs.size());
        return docs;
    }

    @Override
    public SelectOptionParam fetchDocumentMasterByCode(String documentCode) {
        logger.info("Fetching document by code: {}", documentCode);
        return documentRepository.findByDocumentCodeIgnoreCase(documentCode.trim())
                .map(document -> {
                    logger.debug("Found document: {} - {}", document.getDocumentCode(), document.getDocumentName());
                    return new SelectOptionParam(
                            document.getDocumentGuid(),
                            document.getDocumentCode(),
                            document.getDocumentName()
                    );
                })
                .orElseThrow(() -> {
                    logger.error("Document not found with code: {}", documentCode);
                    return new ResourceNotFoundException("Document not found with code: " + documentCode);
                });
    }

    @Override
    public List<DocumentType> getAllDocuments() {
        logger.info("Fetching all documents...");
        List<DocumentType> docs = documentRepository.findAll();
        logger.debug("Total documents fetched: {}", docs.size());
        return docs;
    }

    @Override
    public DocumentType getDocumentByGuid(String guid) {
        logger.info("Fetching document by GUID: {}", guid);
        return documentRepository.findById(guid.trim())
                .map(doc -> {
                    logger.debug("Found document with GUID {}: {}", guid, doc.getDocumentName());
                    return doc;
                })
                .orElseThrow(() -> {
                    logger.error("Document not found with GUID: {}", guid);
                    return new ResourceNotFoundException("Document not found with GUID : " + guid);
                });
    }

    @Override
    public StatusParam addDocument(DocumentAddRequest documentAddRequest) {
        logger.info("Adding new document with code: {}", documentAddRequest.getDocumentCode());
        try {
            if (isDuplicateCode(documentAddRequest.getDocumentCode().trim())) {
                logger.warn("Duplicate document code detected: {}", documentAddRequest.getDocumentCode());
                return new StatusParam(false, "Document code already exists : " + documentAddRequest.getDocumentCode());
            }

            DocumentType document = modelMapper.map(documentAddRequest, DocumentType.class);
            document.setDocumentGuid(UUID.randomUUID().toString());
            document.setCreatedDate(LocalDateTime.now());
            document.setCreatedIpAddr(getClientIp());
            document.setIsActive(true);
            document.setCreatedBy("SYSTEM");

            documentRepository.save(document);
            logger.info("Document saved successfully with GUID: {}", document.getDocumentGuid());
            logger.debug("Saved document details: {}", document);
            return new StatusParam(true, "Saved Successfully");
        }catch (IllegalArgumentException ex){
            logger.error("Validation error while adding document [{}]: {}", documentAddRequest.getDocumentCode(), ex.getMessage(), ex);
            throw new RuntimeException("Error while adding document with code: " + documentAddRequest.getDocumentCode(), ex);
        }
        catch (Exception ex) {
            logger.error("Error while adding document [{}]: {}", documentAddRequest.getDocumentCode(), ex.getMessage(), ex);
            throw new RuntimeException("Error while adding document with code: " + documentAddRequest.getDocumentCode(), ex);
        }
    }

    @Override
    public StatusParam updateDocumentByGuid(String guid, DocumentUpdateRequest documentUpdateRequest) {
        logger.info("Updating document with GUID: {}", guid);
        try {
            DocumentType existingDoc = documentRepository.findById(guid.trim())
                    .orElseThrow(() -> {
                        logger.error("Document not found for update, GUID: {}", guid);
                        return new ResourceNotFoundException("Document not found with GUID: " + guid);
                    });

            logger.debug("Existing document before update: {}", existingDoc);

            if (documentUpdateRequest.getDocumentCode() != null &&
                    !documentUpdateRequest.getDocumentCode().equalsIgnoreCase(existingDoc.getDocumentCode().trim()) &&
                    isDuplicateCode(documentUpdateRequest.getDocumentCode())) {
                logger.warn("Attempt to update with duplicate code: {}", documentUpdateRequest.getDocumentCode());
                return new StatusParam(false, "Document code already exists: " + documentUpdateRequest.getDocumentCode());
            }

            modelMapper.map(documentUpdateRequest, existingDoc);
            existingDoc.setModifiedDate(LocalDateTime.now());
            existingDoc.setModifiedIpAddr(getClientIp());
            existingDoc.setModifiedBy("SYSTEM");

            documentRepository.save(existingDoc);
            logger.info("Document updated successfully for GUID: {}", guid);
            logger.debug("Updated document details: {}", existingDoc);

            return new StatusParam(true, "Document updated successfully");
        }catch (IllegalArgumentException ex){
            logger.error("Validation error while updating document [{}]: {}", guid, ex.getMessage(), ex);
            throw new RuntimeException("Error while updating document with GUID: " + guid, ex);
        }catch (Exception ex) {
            logger.error("Error while updating document [{}]: {}", guid, ex.getMessage(), ex);
            throw new RuntimeException("Error while updating document with GUID: " + guid, ex);
        }
    }

    private boolean isDuplicateCode(String code) {
        boolean exists = documentRepository.existsByDocumentCodeIgnoreCase(code);
        logger.debug("Checking duplicate for code '{}': {}", code, exists);
        return exists;
    }

    private String getClientIp() {
        String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getRemoteAddr();
        }
        logger.debug("Resolved client IP: {}", clientIp);
        return clientIp;
    }
}
