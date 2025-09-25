package com.nic.master.service.process.impl;

import com.nic.master.entity.process.MstColumnType;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.MstColumnTypeRepository;
import com.nic.master.request.process.mstcolumntype.AddMstColumnTypeRequest;
import com.nic.master.request.process.mstcolumntype.UpdateMstColumnTypeRequest;
import com.nic.master.service.process.MstCodeService;
import com.nic.master.service.process.MstColumnService;
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
public class MstColumnServiceImpl implements MstColumnService {

    private static  final Logger logger = LoggerFactory.getLogger(MstColumnServiceImpl.class);
    private final MstColumnTypeRepository mstColumnTypeRepository;
    private  final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;
    private  final MacAddressGenerator macAddressGenerator;

    public MstColumnServiceImpl(MstColumnTypeRepository mstColumnTypeRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator) {
        this.mstColumnTypeRepository = mstColumnTypeRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
    }


    @Override
    public StatusParam addMstColumn(AddMstColumnTypeRequest addMstColumnTypeRequest) {
        logger.info("Adding MstColumn..");
        try{
            if(mstColumnTypeRepository.existsByColumnTypeCodeIgnoreCase(addMstColumnTypeRequest.getColumnTypeCode().trim())){
                logger.warn("Duplicate Column Code. Request{} ",addMstColumnTypeRequest.getColumnTypeCode());
                return new StatusParam(false,"MstColumn Code already exists : "+addMstColumnTypeRequest.getColumnTypeCode());
            }
            MstColumnType mstColumnType = modelMapper.map(addMstColumnTypeRequest,MstColumnType.class);
            mstColumnType.setColumnTypeGuid(UUID.randomUUID().toString());
            mstColumnType.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstColumnType.setCreatedDate(LocalDateTime.now());
            mstColumnType.setCreatedBy("SYSTEM");
            mstColumnType.setCreatedUri(httpServletRequest.getRequestURI());
            mstColumnType.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            mstColumnTypeRepository.save(mstColumnType);
            logger.info("MstColumn added successfully");
            return new StatusParam(true,"MstColumn added successfully.");
        }catch (IllegalArgumentException e){
            logger.error("Validation failed while adding MstColumn.Request{}",addMstColumnTypeRequest,e);
            throw new RuntimeException("Error while adding MstColumn."+e.getMessage(),e);
        }catch (Exception ex){
            logger.error("Error while adding MstColumn. Request{}",addMstColumnTypeRequest,ex);
            throw new RuntimeException("Error while adding MstColumn. Request{}"+ex.getMessage(),ex);
        }
    }

    @Override
    public List<MstColumnType> getAllMstColumns() {
        return mstColumnTypeRepository.findAll();
    }

    @Override
    public MstColumnType getMstColumnByGuid(String columnTypeGuid) {
        logger.info("Fetching MstColumn By Guid : {}",columnTypeGuid);
        return mstColumnTypeRepository.findByColumnTypeGuid(columnTypeGuid.trim())
                .orElseThrow(()->{
                    logger.error("MstColumn not found with Guid : {} ",columnTypeGuid);
                    return new ResourceNotFoundException("MstColumn not found with Guid : "+columnTypeGuid);
                });
    }

    @Override
    public SelectOptionParam getMstColumnByCode(String columnTypeCode) {
        logger.info("Fetching Column By Code.. {}",columnTypeCode);
        return mstColumnTypeRepository.findByColumnTypeCodeIgnoreCase(columnTypeCode.trim())
                    .map(mstColumnType ->{
                        logger.debug("MstColumn found with code.{}",columnTypeCode);
                        return new SelectOptionParam(
                                mstColumnType.getColumnTypeGuid(),
                                mstColumnType.getColumnTypeCode(),
                                mstColumnType.getDefaultLabel()
                        );
                    })
       .orElseThrow(()->{
            logger.error("MstAction not found with code.{}",columnTypeCode);
            return new ResourceNotFoundException("MstAction not found with code :" +columnTypeCode);
        });
    }

    @Override
    public StatusParam updateMstColumn(String columnTypeGuid, UpdateMstColumnTypeRequest updateMstColumnTypeRequest) {
        logger.info("Updating MstColumn with Guid : Request{})",columnTypeGuid);
        try{
            MstColumnType mstColumnType = mstColumnTypeRepository.findByColumnTypeGuid(columnTypeGuid)
                    .orElseThrow(()->{
                        logger.error("MstColumn not found with Guid : Request{}",columnTypeGuid);
                        return new ResourceNotFoundException("MstColumn not found with Guid : "+columnTypeGuid);
                    });

            if(updateMstColumnTypeRequest.getColumnTypeCode() != null
                && !updateMstColumnTypeRequest.getColumnTypeCode().equalsIgnoreCase(mstColumnType.getColumnTypeCode().trim())
                    && mstColumnTypeRepository.existsByColumnTypeCodeIgnoreCase(updateMstColumnTypeRequest.getColumnTypeCode().trim())){
                logger.warn("MstColumn Code already exist.{}",updateMstColumnTypeRequest.getColumnTypeCode());
                return new StatusParam(false,"MstColumn Code already exists : "+updateMstColumnTypeRequest.getColumnTypeCode());
            }
            modelMapper.map(updateMstColumnTypeRequest,mstColumnType);
            mstColumnType.setModifiedDate(LocalDateTime.now());
            mstColumnType.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstColumnType.setModifiedBy("SYSTEM");
            mstColumnType.setModifiedUri(httpServletRequest.getRequestURI());
            mstColumnType.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
            mstColumnTypeRepository.save(mstColumnType);
            logger.info("Mst Column Updated successfully");
            return new StatusParam(true,"Mst Column Updated successfully");
        }catch (IllegalArgumentException ex){
              logger.error("Validation failed while updating error.Request{}",updateMstColumnTypeRequest,ex);
              throw new RuntimeException("Error while updating MstColumn :"+ex.getMessage(),ex);
        }catch (Exception ex){
            logger.error("Error while updating MstColumn. Request{} Guid{}",updateMstColumnTypeRequest,columnTypeGuid);
            throw new RuntimeException("Error while updating MstColumns : "+ex.getMessage(),ex);
        }
    }


}
