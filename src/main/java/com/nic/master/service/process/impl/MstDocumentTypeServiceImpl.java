package com.nic.master.service.process.impl;

import com.nic.master.entity.process.MstDocumentType;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.MstDocumentTypeRepository;
import com.nic.master.request.process.mstdocumenttype.AddMstDocumentTypeRequest;
import com.nic.master.request.process.mstdocumenttype.MstDocumentTypeRequestMapper;
import com.nic.master.request.process.mstdocumenttype.UpdateMstDocumentTypeRequest;
import com.nic.master.service.process.MstDocumentTypeService;
import com.nic.master.util.IdAddressGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MstDocumentTypeServiceImpl implements MstDocumentTypeService {
    private static  final Logger logger = LoggerFactory.getLogger(MstDocumentTypeRequestMapper.class);
    private final MstDocumentTypeRepository mstDocumentTypeRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;
    private final IdAddressGenerator idAddressGenerator;

    public MstDocumentTypeServiceImpl(MstDocumentTypeRepository mstDocumentTypeRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest, IdAddressGenerator idAddressGenerator) {
        this.mstDocumentTypeRepository = mstDocumentTypeRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
        this.idAddressGenerator = idAddressGenerator;
    }

    @Override
    public StatusParam addMstDocument(AddMstDocumentTypeRequest addMstDocumentTypeRequest) {
        logger.info("Adding Mst Document..");
        try{
            if(mstDocumentTypeRepository.existsByDocumentTypeCodeIgnoreCase(addMstDocumentTypeRequest.getDocumentTypeCode().trim())){
                logger.warn("Duplicate Document Code.Request{} ",addMstDocumentTypeRequest.getDocumentTypeCode());
                return new StatusParam(false,"MstDocument Code already exists : " + addMstDocumentTypeRequest.getDocumentTypeCode());
            }
            MstDocumentType mstDocumentType = modelMapper.map(addMstDocumentTypeRequest,MstDocumentType.class);
            mstDocumentType.setDocumentTypeGuid(UUID.randomUUID().toString());
            mstDocumentType.setCreatedIpAddr(idAddressGenerator.getClientIp(httpServletRequest));
            mstDocumentType.setCreatedDate(LocalDateTime.now());
            mstDocumentType.setCreatedBy("SYSTEM");
            mstDocumentType.setCreatedMacAddr(idAddressGenerator.getClientIp(httpServletRequest));
            mstDocumentTypeRepository.save(mstDocumentType);
            logger.info("MstDocument added successfully.  ");
            return new StatusParam(true,"MstDocument added successfully.");
        }catch (IllegalArgumentException ex){
            logger.error("Validation failed error. Request{}",addMstDocumentTypeRequest,ex);
            throw  new RuntimeException("Error while adding MstDocumet."+ ex.getMessage(),ex);
        }catch (Exception ex){
            logger.error("Error while adding MstDocument.Request{}",addMstDocumentTypeRequest,ex);
            throw  new RuntimeException("Error while adding MstDocument"+ex.getMessage(),ex);
        }
    }

    @Override
    public List<MstDocumentType> getAllDocuments() {
        logger.info("Fetching All Documents.");
        return mstDocumentTypeRepository.findAll();
    }

    @Override
    public MstDocumentType getDocumentTypeByGuid(String documentTypeGuid) {
        logger.info("Fetching Document By Guid..");
        return mstDocumentTypeRepository.findByDocumentTypeGuid(documentTypeGuid.trim())
                .orElseThrow(()->{
                    logger.error("MstDocumentType not found with Guid. Request{}",documentTypeGuid);
                    return new ResourceNotFoundException("MstDocumentType not found with Guid : "+documentTypeGuid);
                });
    }

    @Override
    public SelectOptionParam getDocumentTypeByCode(String documentTypeCode) {
        logger.info("Fetching Document By Code..");
        return mstDocumentTypeRepository.findByDocumentTypeCodeIgnoreCase(documentTypeCode.trim())
                .map(mstDocumentType -> {
                    logger.debug("MstDocument found with code.Request{}",documentTypeCode);
                    return new SelectOptionParam(
                            mstDocumentType.getDocumentTypeGuid(),
                            mstDocumentType.getDocumentTypeCode(),
                            mstDocumentType.getDocumentTypeName()
                    );
        })
        .orElseThrow(()->{
            logger.error("MstDocument not found with code.Request{}",documentTypeCode);
            return new ResourceNotFoundException("MstDocument not found with code."+documentTypeCode);
        });
    }

    @Override
    public StatusParam updateDocument(String documentTypeGuid, UpdateMstDocumentTypeRequest updateMstDocumentTypeRequest) {
        logger.info("Updating Document with Guid : Request{}",documentTypeGuid);
        try{
            MstDocumentType mstDocumentType = mstDocumentTypeRepository.findByDocumentTypeGuid(documentTypeGuid.trim())
                    .orElseThrow(()->{
                        logger.error("MstDocument not found with Guid.Request{}",documentTypeGuid);
                        return new ResourceNotFoundException("MstDocument Not found with Guid : "+documentTypeGuid);
                    });
            if(updateMstDocumentTypeRequest.getDocumentTypeCode() != null
            && !updateMstDocumentTypeRequest.getDocumentTypeCode().equalsIgnoreCase(mstDocumentType.getDocumentTypeCode().trim())
            &&mstDocumentTypeRepository.existsByDocumentTypeCodeIgnoreCase(updateMstDocumentTypeRequest.getDocumentTypeCode().trim())){
                return new StatusParam(false,"DocumentType code already exists");
            }

            modelMapper.map(updateMstDocumentTypeRequest,mstDocumentType);
            mstDocumentType.setModifiedDate(LocalDateTime.now());
            mstDocumentType.setModifiedIpAddr(idAddressGenerator.getClientIp(httpServletRequest));
            mstDocumentType.setModifiedMacAddr(idAddressGenerator.getClientIp(httpServletRequest));
            mstDocumentType.setModifiedBy("SYSTEM");
            mstDocumentTypeRepository.save(mstDocumentType);
            return new StatusParam(true,"MstDocumentType updated Successfully");
        }catch (IllegalArgumentException ex){
            logger.error("Validation failed while updating error.Request{}",updateMstDocumentTypeRequest,ex);
            throw new RuntimeException("Error while updating MstDocument :"+ex.getMessage(),ex);
        }catch (Exception ex){
            logger.error("Error while updating MstDocument. Request{} Guid{}",updateMstDocumentTypeRequest,documentTypeGuid);
            throw new RuntimeException("Error while updating MstDocument : "+ex.getMessage(),ex);
        }
    }

}
