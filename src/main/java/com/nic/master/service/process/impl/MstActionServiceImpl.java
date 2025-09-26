package com.nic.master.service.process.impl;

import com.nic.master.entity.process.MstActionType;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.MstActionTypeRepository;
import com.nic.master.requestDTO.process.mstactiontype.AddMstActionRequest;
import com.nic.master.requestDTO.process.mstactiontype.UpdateMstActionRequest;
import com.nic.master.service.process.MstActionService;
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
public class MstActionServiceImpl implements MstActionService {

    private static  final Logger logger = LoggerFactory.getLogger(MstActionServiceImpl.class);
    private final MstActionTypeRepository mstActionTypeRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;
    private final MacAddressGenerator macAddressGenerator;

    public MstActionServiceImpl(MstActionTypeRepository mstActionTypeRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator) {
        this.mstActionTypeRepository = mstActionTypeRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
    }

    @Override
    public StatusParam addMstAction(AddMstActionRequest addMstActionRequest) {
        logger.info("Adding Action..");
        try{
            if(mstActionTypeRepository.existsByActionTypeCodeIgnoreCase(addMstActionRequest.getActionTypeCode().trim())) {
                logger.warn("Duplicate Action Code : " + addMstActionRequest.getActionTypeCode());
                return new StatusParam(false, "MstAction Code already exists : " + addMstActionRequest.getActionTypeCode());
            }
            MstActionType mstActionType = modelMapper.map(addMstActionRequest,MstActionType.class);
            mstActionType.setActionTypeGuid(UUID.randomUUID().toString());
            mstActionType.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstActionType.setCreatedDate(LocalDateTime.now());
            mstActionType.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            mstActionType.setCreatedBy("SYSTEM");
            mstActionType.setCreatedUri(httpServletRequest.getRequestURI());
            mstActionTypeRepository.save(mstActionType);
            logger.info("MstAction Added Successfully! ");
            return new StatusParam(true,"MstAction Added Successfully! ");
        }catch(IllegalArgumentException e){
            logger.error("Validation error while adding MstAction. Request: {}", addMstActionRequest, e);
            throw  new RuntimeException("Error while adding MstAction : "+ e.getMessage(),e);
        }catch(Exception e){
            logger.error("Error while adding MstAction.Request: {} ",addMstActionRequest,e);
            throw new RuntimeException("Error while adding MstAction : " + e.getMessage(),e);
        }
    }

    @Override
    public List<MstActionType> getAllMstActions() {
        logger.info("Fetching All Actions...");
        List<MstActionType> mstActions =mstActionTypeRepository.findAll();
        return mstActions;
    }

    @Override
    public MstActionType getMstActionByGuid(String actionTypeGuid) {
        logger.info("Fetching MstAction By Guid: {}",actionTypeGuid);
        return mstActionTypeRepository.findByActionTypeGuid(actionTypeGuid.trim())
                .orElseThrow(()->{
                    logger.error("MstAction not found with Guid: {}",actionTypeGuid);
                    return new ResourceNotFoundException("MstAction not found with Guid :"+actionTypeGuid);
                });
    }

    @Override
    public SelectOptionParam getMstActionByCode(String actionTypeCode) {
        logger.info("Fetching MstAction By Code : {}",actionTypeCode);
        return mstActionTypeRepository.findByActionTypeCodeIgnoreCase(actionTypeCode.trim())
                .map( mstActionType -> {
                    logger.debug("MstAction found for code.{}",actionTypeCode);
                    return new SelectOptionParam(
                            mstActionType.getActionTypeGuid(),
                            mstActionType.getActionTypeCode(),
                            mstActionType.getActionTypeName()
                    );
                })
                .orElseThrow(()->{
                    logger.error("MstAction not found with code.{}",actionTypeCode);
                    return new ResourceNotFoundException("MstAction not found with code :" +actionTypeCode);
                });
    }

    @Override
    public StatusParam updateMstAction(String actionTypeGuid, UpdateMstActionRequest updateMstActionRequest) {
        logger.error("Updating MstAction..with Guid : " + actionTypeGuid);
        try{
            MstActionType mstActionType =mstActionTypeRepository.findByActionTypeGuid(actionTypeGuid.trim())
                    .orElseThrow(()->{
                        logger.error("MstAction found with Guid. {} ",actionTypeGuid);
                        return new ResourceNotFoundException("MstAction found for Guid :"+actionTypeGuid);
                    });
            if(updateMstActionRequest.getActionTypeCode() != null
            && !updateMstActionRequest.getActionTypeCode().equalsIgnoreCase(mstActionType.getActionTypeCode())
            && mstActionTypeRepository.existsByActionTypeCodeIgnoreCase(updateMstActionRequest.getActionTypeCode())){
                logger.warn("MstAction Code already exist.{}",updateMstActionRequest.getActionTypeCode());
            return new StatusParam(false,"MstAction Code already exist : "+updateMstActionRequest.getActionTypeCode());
            }
            modelMapper.map(updateMstActionRequest,mstActionType);
            mstActionType.setModifiedDate(LocalDateTime.now());
            mstActionType.setModifiedBy("SYSTEM");
            mstActionType.setModifiedUri(httpServletRequest.getRequestURI());
            mstActionType.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstActionType.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
            mstActionTypeRepository.save(mstActionType);
            logger.info("MstActionType Updated successfully.");
            return new StatusParam(true,"MstActionType Updated successfully.");

        }catch (IllegalArgumentException e){
            logger.error("Validation failed while updating error. Request{}  Guid{}",updateMstActionRequest,actionTypeGuid,e);
            throw  new RuntimeException("Error while updating details : "+ e.getMessage(),e);
        }
        catch (Exception e){
            logger.error("Error while updating MstAction.Request{}  Guid{} : ",updateMstActionRequest,actionTypeGuid,e);
            throw  new RuntimeException("Error while updating details : "+ e.getMessage(),e) ;
        }
    }

}
