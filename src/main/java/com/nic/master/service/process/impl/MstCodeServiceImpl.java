package com.nic.master.service.process.impl;

import com.nic.master.entity.process.MstCodeImpl;
import com.nic.master.enums.ImplType;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.MstCodeImplRepository;
import com.nic.master.request.process.mstcodeimpl.AddMstCodeImplRequest;
import com.nic.master.request.process.mstcodeimpl.UpdateMstCodeImplRequest;
import com.nic.master.service.process.MstCodeService;
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
public class MstCodeServiceImpl implements MstCodeService {

    private static final Logger logger = LoggerFactory.getLogger(MstCodeServiceImpl.class);
    private final MstCodeImplRepository mstCodeImplRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;
    private final MacAddressGenerator macAddressGenerator;

    public MstCodeServiceImpl(MstCodeImplRepository mstCodeImplRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator) {
        this.mstCodeImplRepository = mstCodeImplRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
    }

    @Override
    public StatusParam addMstCode(AddMstCodeImplRequest addMstCodeImplRequest) {
        logger.info("Adding MstCodeImpl..");
        try{

            boolean exists = mstCodeImplRepository.existsByQualifiedClassNameAndImplType(
                    addMstCodeImplRequest.getQualifiedClassName(),
                    addMstCodeImplRequest.getImplType()
            );

            if (exists) {
                logger.warn("Duplicate MstCodeImpl found for class {} and implType {}",
                        addMstCodeImplRequest.getQualifiedClassName(),
                        addMstCodeImplRequest.getImplType());

                return new StatusParam(false,
                        String.format("MstCodeImpl already exists for class [%s] with implType [%s]",
                                addMstCodeImplRequest.getQualifiedClassName(),
                                addMstCodeImplRequest.getImplType()));
            }

            MstCodeImpl mstCodeImpl = modelMapper.map(addMstCodeImplRequest,MstCodeImpl.class);
            mstCodeImpl.setCodeImplGuid(UUID.randomUUID().toString());
            mstCodeImpl.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstCodeImpl.setCreatedDate(LocalDateTime.now());
            mstCodeImpl.setCreatedBy("SYSTEM");
            mstCodeImpl.setCreatedUri(httpServletRequest.getRequestURI());
            mstCodeImpl.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            mstCodeImplRepository.save(mstCodeImpl);
            logger.info("MstCodeImpl added successfully.");
            return new StatusParam(true,"MstCodeImpl added successfully.");
        }catch (IllegalArgumentException ex){
            logger.error("Validation error while adding MstCoeImpl. Request:{}",addMstCodeImplRequest,ex);
            throw new RuntimeException("Error wile adding MstCodeImpl:"+ex.getMessage(),ex);
        }catch (Exception ex){
            logger.error("Error while adding MstCodeImpl : {} ",addMstCodeImplRequest,ex);
            throw new RuntimeException("Error while adding MstCodeImpl : "+ex.getMessage(),ex);
        }


    }

    @Override
    public List<MstCodeImpl> getAllMstCodeImpl() {
        logger.info("fetching all MstCodeImpl..");
        return mstCodeImplRepository.findAll();
    }

    @Override
    public MstCodeImpl getByGuid(String codeImplGuid) {
        logger.info("Fetching MstCodeImpl By Guid : {}",codeImplGuid);
        return mstCodeImplRepository.findByCodeImplGuid(codeImplGuid.trim())
                .orElseThrow(()->{
                    logger.error("MstCodeImpl not found with Guid : {}",codeImplGuid);
                    return new ResourceNotFoundException("MstCodeImpl not found with Guid : "+codeImplGuid);
                });
    }

    @Override
    public StatusParam updateMstCodeImpl(String codeImplGuid, UpdateMstCodeImplRequest updateMstCodeImplRequest) {
        logger.info("Updating MstCodeImpl...");
        try{
            MstCodeImpl mstCodeImpl = mstCodeImplRepository.findByCodeImplGuid(codeImplGuid.trim())
                    .orElseThrow(()->{
                        logger.error("MstCodeImpl not found with Guid : {}",codeImplGuid);
                        return new ResourceNotFoundException("MstCodeImpl not found with Guid : "+codeImplGuid);
                    });

                modelMapper.map(updateMstCodeImplRequest,mstCodeImpl);
                mstCodeImpl.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
                mstCodeImpl.setModifiedDate(LocalDateTime.now());
                mstCodeImpl.setModifiedBy("SYSTEM");
                mstCodeImpl.setModifiedUri(httpServletRequest.getRequestURI());
                mstCodeImpl.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
                mstCodeImplRepository.save(mstCodeImpl);
                logger.info("MstCodeImpl updated successfully");
                return new StatusParam(true,"MstCodeImpl updated successfully");
        }catch (IllegalArgumentException ex){
            logger.error("Validation failed while updating error . Request {} Guid{}",updateMstCodeImplRequest,codeImplGuid);
            throw new RuntimeException("Error while updating MstCodeImpl :"+ex.getMessage(),ex);

        }catch (Exception ex){
            logger.error("Error while updating MstCodeImpl.Request{} Guid{} :",updateMstCodeImplRequest,codeImplGuid);
            throw  new RuntimeException("Error while updating details :"+ ex.getMessage(),ex);
        }
    }

}
