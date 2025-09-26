package com.nic.master.service.process.impl;

import com.nic.master.entity.process.MstProcessType;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.MstProcessTypeRepository;
import com.nic.master.requestDTO.process.mstprocesstype.AddMstProcessTypeRequest;
import com.nic.master.requestDTO.process.mstprocesstype.UpdateMstProcessTypeRequest;
import com.nic.master.service.process.MstProcessTypeService;
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
public class MstProcessTypeServiceImpl implements MstProcessTypeService {

    private static  final Logger logger = LoggerFactory.getLogger(MstProcessTypeServiceImpl.class);
    private final MstProcessTypeRepository mstProcessTypeRepository;
    private final ModelMapper modelMapper;
    private  final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;
    private final MacAddressGenerator macAddressGenerator;

    public MstProcessTypeServiceImpl(MstProcessTypeRepository mstProcessTypeRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator) {
        this.mstProcessTypeRepository = mstProcessTypeRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
    }

    @Override
    public StatusParam addMstProcess(AddMstProcessTypeRequest addMstProcessTypeRequest) {
        logger.info("Adding ProcessType..");
        try{
            if(mstProcessTypeRepository.existsByProcessTypeCodeIgnoreCase(addMstProcessTypeRequest.getProcessTypeCode().trim())){
                logger.warn("Duplicate ProcessType Code.Request{}",addMstProcessTypeRequest.getProcessTypeCode());
                return new StatusParam(false,"ProcessType Code already exists"+addMstProcessTypeRequest.getProcessTypeCode());
            }
            MstProcessType mstProcessType = modelMapper.map(addMstProcessTypeRequest,MstProcessType.class);
            mstProcessType.setProcessTypeGuid(UUID.randomUUID().toString());
            mstProcessType.setCreatedDate(LocalDateTime.now());
            mstProcessType.setCreatedBy("SYSTEM");
            mstProcessType.setCreatedUri(httpServletRequest.getRequestURI());
            mstProcessType.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstProcessType.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            mstProcessTypeRepository.save(mstProcessType);
            return new StatusParam(true,"MstProcess added successfully.");
        }catch (IllegalArgumentException ex){
            logger.error("Validation failed error. Request{}",addMstProcessTypeRequest,ex);
            throw new RuntimeException("Error while adding MstProcess."+ex.getMessage(),ex);
        }catch (Exception ex){
            logger.error("Error while adding MstProcess. Request{}",addMstProcessTypeRequest,ex);
            throw new RuntimeException("Error while adding MstProcess"+ex.getMessage(),ex);
        }
    }

    @Override
    public List<MstProcessType> getAllMstProcess() {
        logger.info("Fetching All Process");
        return mstProcessTypeRepository.findAll();
    }

    @Override
    public MstProcessType getByProcessTypeGuid(String processTypeGuid) {
        logger.info("Fetching Process By Guid");
        return mstProcessTypeRepository.findByProcessTypeGuid(processTypeGuid.trim())
                .orElseThrow(()->{
                    logger.error("MstProcess not found with Guid:{}",processTypeGuid);
                    return  new ResourceNotFoundException("MstProcess not found with Guid : "+ processTypeGuid);
                });
    }

    @Override
    public SelectOptionParam getByProcessTypeCode(String processTypeCode) {
        logger.info("Fetching Document with code..");
        return mstProcessTypeRepository.findByProcessTypeCodeIgnoreCase(processTypeCode.trim())
                .map(mstProcessType -> {
                    return  new SelectOptionParam(
                            mstProcessType.getProcessTypeGuid(),
                            mstProcessType.getProcessTypeCode(),
                            mstProcessType.getProcessTypeNameEn()
                    );
                })
                .orElseThrow(()->{
                    logger.error("MstProcess Not found with code.Request{}",processTypeCode);
                    return new ResourceNotFoundException("MstProcess Not found with code : "+processTypeCode);
                });
    }

    @Override
    public StatusParam updateMstProcess(String processTypeGuid, UpdateMstProcessTypeRequest updateMstProcessTypeRequest) {
        logger.info("Updating Process..");
        try{
            MstProcessType mstProcessType = mstProcessTypeRepository.findByProcessTypeGuid(processTypeGuid)
                    .orElseThrow(()->{
                        logger.error("MstProcess not found with Guid. Request{}",processTypeGuid);
                        return new ResourceNotFoundException("MstProcess not found with Guid : "+processTypeGuid);
                    });
            if(updateMstProcessTypeRequest.getProcessTypeCode() != null
              && !updateMstProcessTypeRequest.getProcessTypeCode().equalsIgnoreCase(mstProcessType.getProcessTypeCode().trim())
            && mstProcessTypeRepository.existsByProcessTypeCodeIgnoreCase(updateMstProcessTypeRequest.getProcessTypeCode().trim())){
                return new StatusParam(false,"ProcessTypeCode already exists");
            }
            modelMapper.map(updateMstProcessTypeRequest,mstProcessType);
            mstProcessType.setModifiedDate(LocalDateTime.now());
            mstProcessType.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstProcessType.setModifiedBy("SYSTEM");
            mstProcessType.setModifiedUri(httpServletRequest.getRequestURI());
            mstProcessType.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
            mstProcessTypeRepository.save(mstProcessType);
            return new StatusParam(true,"MstProcess updated successfully.");
        }catch (IllegalArgumentException ex){
            logger.error("Validation failed while updating error.Request{}",updateMstProcessTypeRequest,ex);
            throw new RuntimeException("Error while updating MstProcess :"+ex.getMessage(),ex);
        }catch (Exception ex){
            logger.error("Error while updating MstProcess. Request{} Guid{}",updateMstProcessTypeRequest,processTypeGuid);
            throw new RuntimeException("Error while updating MstProcess : "+ex.getMessage(),ex);
        }
    }
}
