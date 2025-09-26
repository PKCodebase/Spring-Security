package com.nic.master.service.process.impl;

import com.nic.master.entity.process.MstSectionType;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.MstSectionRepository;
import com.nic.master.requestDTO.process.mstsectiontype.AddMstSectionTypeRequest;
import com.nic.master.requestDTO.process.mstsectiontype.UpdateMstSectionTypeRequest;
import com.nic.master.service.process.MstSectionTypeService;
import com.nic.master.util.IpAddressGenerator;
import com.nic.master.util.MacAddressGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MstSectionTypeServiceImpl implements MstSectionTypeService {

    private static final Logger logger = LoggerFactory.getLogger(MstProcessTypeServiceImpl.class);
    private final MstSectionRepository mstSectionRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;
    private final MacAddressGenerator macAddressGenerator;

    public MstSectionTypeServiceImpl(MstSectionRepository mstSectionRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator) {
        this.mstSectionRepository = mstSectionRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
    }

    @Override
    public StatusParam addMstSection(AddMstSectionTypeRequest addMstSectionTypeRequest) {
        logger.info("Adding Section..");
        try{
            if(mstSectionRepository.existsBySectionTypeCodeIgnoreCase(addMstSectionTypeRequest.getSectionTypeCode().trim())) {
                logger.warn("Duplicate SectionType Code . Request{}",addMstSectionTypeRequest.getSectionTypeCode());
                return new StatusParam(false, "SectionType Code already exists : " + addMstSectionTypeRequest.getSectionTypeCode());
            }
            MstSectionType mstSectionType = modelMapper.map(addMstSectionTypeRequest,MstSectionType.class);
            mstSectionType.setSectionTypeGuid(UUID.randomUUID().toString());
            mstSectionType.setCreatedDate(LocalDateTime.now());
            mstSectionType.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstSectionType.setCreatedBy("SYSTEM");
            mstSectionType.setCreatedUri(httpServletRequest.getRequestURI());
            mstSectionType.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            mstSectionRepository.save(mstSectionType);
            logger.info("Adding MstSection successfully");
            return new StatusParam(true,"MstSection added successfully");
        }catch (IllegalArgumentException ex){
            logger.error("Validation failed error . Request{}",addMstSectionTypeRequest,ex);
            throw new RuntimeException("Error while adding MstSection : "+ex.getMessage(),ex);
        }catch (Exception ex){
            logger.error("Error while adding MstSection. Request{}",addMstSectionTypeRequest,ex);
            throw new RuntimeException("Error while adding MstSection : "+ ex.getMessage(),ex);
        }

    }

    @Override
    public List<MstSectionType> getAllMstSections() {
        logger.info("Fetching All Section..");
        return mstSectionRepository.findAll();
    }

    @Override
    public MstSectionType getSectionByGuid(String sectionTypeGuid) {
        logger.info("Fetching Section By Guid..");
        return mstSectionRepository.findBySectionTypeGuid(sectionTypeGuid.trim())
                .orElseThrow(()->{
                    logger.error("Section not found with Guid. Request{}",sectionTypeGuid);
                        return new ResourceNotFoundException("Section not found with Guid : "+sectionTypeGuid);
                });
    }

    @Override
    public SelectOptionParam getSectionByCode(String sectionTypeCode) {
        logger.info("Fetching Section By Code..");
        return mstSectionRepository.findBySectionTypeCodeIgnoreCase(sectionTypeCode.trim())
                .map(mstSectionType -> {
                    return new SelectOptionParam(
                            mstSectionType.getSectionTypeGuid(),
                            mstSectionType.getSectionTypeCode(),
                            mstSectionType.getSectionTypeName()
                    );
                })
                .orElseThrow(()->{
                    logger.error("MstSection not found with Code.Request{}",sectionTypeCode);
                    return new ResourceNotFoundException("MstSection not found with Code : "+ sectionTypeCode);
                });
    }

    @Override
    public StatusParam updateSection(String sectionTypeGuid,UpdateMstSectionTypeRequest updateMstSectionTypeRequest) {
       logger.info("Updating MstSection..");
        try{
            MstSectionType mstSectionType = mstSectionRepository.findBySectionTypeGuid(sectionTypeGuid.trim())
                    .orElseThrow(()->{
                        logger.error("MstSection not found with Guid.Request{}",sectionTypeGuid);
                        return new ResourceNotFoundException("MstSection not found with Guid : "+sectionTypeGuid);
                    });
            if(updateMstSectionTypeRequest.getSectionTypeCode() != null
            && !updateMstSectionTypeRequest.getSectionTypeCode().equalsIgnoreCase(mstSectionType.getSectionTypeCode().trim())
                && mstSectionRepository.existsBySectionTypeCodeIgnoreCase(updateMstSectionTypeRequest.getSectionTypeCode().trim())){
                return new StatusParam(false,"SectionType Code already exists.");
            }
            modelMapper.map(updateMstSectionTypeRequest,mstSectionType);
            mstSectionType.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstSectionType.setModifiedDate(LocalDateTime.now());
            mstSectionType.setModifiedBy("SYSTEM");
            mstSectionType.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
            mstSectionType.setModifiedUri(httpServletRequest.getRequestURI());
            mstSectionRepository.save(mstSectionType);
            logger.info("MstSection updated successfully.");
            return new StatusParam(true,"MstSection updated successfully.");
        }catch (IllegalArgumentException ex){
            logger.error("Validation failed while updating error.Request{}",updateMstSectionTypeRequest,ex);
            throw new RuntimeException("Error while updating MstSection :"+ex.getMessage(),ex);
        }catch (Exception ex){
            logger.error("Error while updating MstSection. Request{} Guid{}",updateMstSectionTypeRequest,sectionTypeGuid);
            throw new RuntimeException("Error while updating MstSection : "+ex.getMessage(),ex);
        }
    }
}
