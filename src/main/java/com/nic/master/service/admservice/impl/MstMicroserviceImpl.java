package com.nic.master.service.admservice.impl;

import com.nic.master.entity.adm.MstMicroservice;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.adm.MstMicroserviceRepository;
import com.nic.master.requestDTO.adm.mstmicroservicerequest.MicroserviceAddRequest;
import com.nic.master.requestDTO.adm.mstmicroservicerequest.MicroserviceUpdateRequest;
import com.nic.master.service.admservice.MstMicroserviceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.nic.master.util.IpAddressGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MstMicroserviceImpl implements MstMicroserviceService {

    private static final Logger logger = LoggerFactory.getLogger(MstMicroserviceImpl.class);
    private final MstMicroserviceRepository mstMicroserviceRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;


    public MstMicroserviceImpl(MstMicroserviceRepository mstMicroserviceRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest, IpAddressGenerator ipAddressGenerator) {
        this.mstMicroserviceRepository = mstMicroserviceRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
    }

    @Override
    public StatusParam addMicroservice(MicroserviceAddRequest microserviceAddRequest) {
        logger.info("Adding Microservices..");
        try {
            if(mstMicroserviceRepository.existsByMicroserviceCodeIgnoreCase(microserviceAddRequest.getMicroserviceCode().trim())){
                logger.warn("Duplicate Microservice Code detected: " + microserviceAddRequest.getMicroserviceCode());
                return new StatusParam(false, "Microservice Code Already Exists : " + microserviceAddRequest.getMicroserviceCode());
            }
            MstMicroservice mstMicroservice = modelMapper.map(microserviceAddRequest, MstMicroservice.class);
            mstMicroservice.setMicroserviceGuid(UUID.randomUUID().toString());
            mstMicroservice.setCreatedDate(LocalDateTime.now());
            mstMicroservice.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstMicroservice.setCreatedBy("SYSTEM");
            mstMicroservice.setIsActive(true);
            mstMicroserviceRepository.save(mstMicroservice);
            return new StatusParam(true, "Microservice Added Successfully");
        }catch (IllegalArgumentException ex) {
            logger.error("Validation error while adding microservice. Request: {}", microserviceAddRequest, ex);
            throw new RuntimeException("Error while adding microservice: " + ex.getMessage(), ex);
        }catch (Exception ex) {
            logger.error("Error while adding microservice. Request: {}", microserviceAddRequest, ex);
            throw new RuntimeException("Error while adding microservice: " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<MstMicroservice> getAllMicroservices() {
        logger.info("Fetching all microservices...");
        return mstMicroserviceRepository.findAll();
    }

    @Override
    public MstMicroservice getMicroserviceByGuid(String microserviceGuid) {
        logger.info("Fetching microservice by GUID: {}", microserviceGuid);
        return mstMicroserviceRepository.findByMicroserviceGuid(microserviceGuid.trim())
                .orElseThrow(() -> {
                    logger.error("Microservice not found with GUID: {}", microserviceGuid);
                    return new ResourceNotFoundException("Microservice not found with GUID : " + microserviceGuid);
                });
    }

    @Override
    public SelectOptionParam getMicroserviceByCode(String microserviceCode) {
        logger.info("Fetching microservice by code: {}", microserviceCode);
        return mstMicroserviceRepository.findByMicroserviceCodeIgnoreCase(microserviceCode.trim())
                .map(microservice ->{
                    logger.debug("Microservice found for code {} : ",microserviceCode);
                    return new SelectOptionParam(
                            microservice.getMicroserviceCode(),
                            microservice.getMicroserviceGuid(),
                            microservice.getMicroserviceName()
                    );
                })
                .orElseThrow(()->{
                        logger.warn("Microservice not found with code: {}", microserviceCode);
                return new ResourceNotFoundException("Microservice not found with code : " + microserviceCode);
                });
    }

    @Override
    public StatusParam updateMicroServiceByGuid(String microserviceGuid, MicroserviceUpdateRequest microserviceUpdateRequest) {
  logger.info("Updating microservices..");
        try {
            MstMicroservice mstMicroservice = mstMicroserviceRepository.findByMicroserviceGuid(microserviceGuid.trim())
                    .orElseThrow(() -> {
                        logger.error("Microservice not found with GUID: {}", microserviceGuid);
                        return new ResourceNotFoundException("Microservice not found with GUID : " + microserviceGuid);
                    });

//            if(microserviceUpdateRequest.getMicroserviceCode() != null
//            &&  !microserviceUpdateRequest.getMicroserviceCode().equalsIgnoreCase(mstMicroservice.getMicroserviceCode())){
//                mstMicroserviceRepository.existsByMicroserviceCodeIgnoreCase(mstMicroservice.getMicroserviceCode());
//                logger.warn("Microservice code already exist : " + microserviceUpdateRequest.getMicroserviceCode());
//                return new StatusParam(false,"Microservice code already exists : " +microserviceUpdateRequest.getMicroserviceCode());
//
//            }

            if (microserviceUpdateRequest.getMicroserviceCode() != null
                    && !microserviceUpdateRequest.getMicroserviceCode().equalsIgnoreCase(mstMicroservice.getMicroserviceCode())
                    && mstMicroserviceRepository.existsByMicroserviceCodeIgnoreCase(microserviceUpdateRequest.getMicroserviceCode())) {

                logger.warn("Microservice code already exists : {}", microserviceUpdateRequest.getMicroserviceCode());
                return new StatusParam(false, "Microservice code already exists : " + microserviceUpdateRequest.getMicroserviceCode());
            }


            modelMapper.map(microserviceUpdateRequest, mstMicroservice);
            mstMicroservice.setModifiedDate(LocalDateTime.now());
            mstMicroservice.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstMicroservice.setModifiedBy("SYSTEM");
            mstMicroserviceRepository.save(mstMicroservice);
            return new StatusParam(true, "Microservice Updated Successfully");

        } catch (IllegalArgumentException e) {
            logger.error("Validation error while updating microservice. GUID={}, Request={}", microserviceGuid, microserviceUpdateRequest, e);
            throw new RuntimeException("Error while updating microservice: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Error while updating microservice. GUID={}, Request={}", microserviceGuid, microserviceUpdateRequest, e);
            throw new RuntimeException("Error while updating microservice: " + e.getMessage(), e);
        }

    }


}
