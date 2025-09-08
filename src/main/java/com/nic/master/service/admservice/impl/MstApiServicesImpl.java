package com.nic.master.service.admservice.impl;

import com.nic.master.entity.adm.MstApiService;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.adm.MstApiServiceRepository;
import com.nic.master.request.adm.apiservicerequest.AddApiServiceRequest;
import com.nic.master.request.adm.apiservicerequest.UpdateApiServiceRequest;
import com.nic.master.service.admservice.MstApiServices;
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
public class MstApiServicesImpl implements MstApiServices {

    private static final Logger logger = LoggerFactory.getLogger(MstApiServicesImpl.class);
    private final MstApiServiceRepository mstApiServiceRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;
    private final IdAddressGenerator idAddressGenerator;


    public MstApiServicesImpl(MstApiServiceRepository mstApiServiceRepository,
                              ModelMapper modelMapper,
                              HttpServletRequest httpServletRequest, IdAddressGenerator idAddressGenerator) {
        this.mstApiServiceRepository = mstApiServiceRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
        this.idAddressGenerator = idAddressGenerator;
    }

    @Override
    public StatusParam addApiService(AddApiServiceRequest addApiServiceRequest) {
        logger.info(" Entering addApiService(), Request={}", addApiServiceRequest);

        try {
            if (mstApiServiceRepository.existsByApiServiceCodeIgnoreCase(addApiServiceRequest.getApiServiceCode().trim())) {
                logger.warn("ApiService Code already exists : {}", addApiServiceRequest.getApiServiceCode());
                return new StatusParam(false, "ApiService Code Already exists : " + addApiServiceRequest.getApiServiceCode());
            }

            MstApiService mstApiService = modelMapper.map(addApiServiceRequest, MstApiService.class);
            mstApiService.setApiServiceGuid(UUID.randomUUID().toString());
            mstApiService.setCreatedDate(LocalDateTime.now());
            mstApiService.setCreatedIpAddr(idAddressGenerator.getClientIp(httpServletRequest));
            mstApiService.setCreatedBy("SYSTEM");
            mstApiService.setIsActive(true);

            mstApiServiceRepository.save(mstApiService);
            logger.info("ApiService Added successfully, GUID={}", mstApiService.getApiServiceGuid());

            return new StatusParam(true, "ApiService Added Successfully");
        } catch (IllegalArgumentException ex) {
            logger.error("Validation error while adding ApiService. Request={}", addApiServiceRequest, ex);
            throw new RuntimeException("Error while adding ApiService : " + ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error("Unexpected error while adding ApiService. Request={}", addApiServiceRequest, ex);
            throw new RuntimeException("Error while adding ApiService : " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<MstApiService> getAllApiServices() {
        logger.info("Entering getAllApiServices()");
        List<MstApiService> services = mstApiServiceRepository.findAll();
        logger.info("Fetched {} ApiServices", services.size());
        return services;
    }

    @Override
    public MstApiService getApiServiceByGuid(String apiServiceGuid) {
        logger.info("➡Entering getApiServiceByGuid(), GUID={}", apiServiceGuid);

        return mstApiServiceRepository.findByApiServiceGuid(apiServiceGuid.trim())
                .orElseThrow(() -> {
                    logger.warn(" Api Service not found with GUID={}", apiServiceGuid);
                    return new ResourceNotFoundException("Api Service not found with GUID : " + apiServiceGuid);
                });
    }

    @Override
    public SelectOptionParam getApiServiceByCode(String apiServiceCode) {
        logger.info("Entering getApiServiceByCode(), Code={}", apiServiceCode);

        return mstApiServiceRepository.findByApiServiceCodeIgnoreCase(apiServiceCode.trim())
                .map(apiService -> {
                    logger.info("Api Service found by Code={}", apiServiceCode);
                    return new SelectOptionParam(
                            apiService.getApiServiceGuid(),
                            apiService.getApiServiceCode(),
                            apiService.getApiServiceName()
                    );
                })
                .orElseThrow(() -> {
                    logger.error("Api Service not found with Code={}", apiServiceCode);
                    return new ResourceNotFoundException("Api Service Code not found with code : " + apiServiceCode);
                });
    }

    @Override
    public StatusParam updateApiService(String apiServiceGuid, UpdateApiServiceRequest updateApiServiceRequest) {
        logger.info("Entering updateApiService(), GUID={}, Request={}", apiServiceGuid, updateApiServiceRequest);

        try {
            MstApiService mstApiService = mstApiServiceRepository.findByApiServiceGuid(apiServiceGuid.trim())
                    .orElseThrow(() -> {
                        logger.warn("Api Service GUID not found: {}", apiServiceGuid);
                        return new ResourceNotFoundException("Api Service GUID not found with GUID : " + apiServiceGuid);
                    });

            if (updateApiServiceRequest.getApiServiceCode() != null
                    && !updateApiServiceRequest.getApiServiceCode().equalsIgnoreCase(mstApiService.getApiServiceCode())
                    && mstApiServiceRepository.existsByApiServiceCodeIgnoreCase(updateApiServiceRequest.getApiServiceCode())) {
                logger.warn("Api Service Code already exists : {}", updateApiServiceRequest.getApiServiceCode());
                return new StatusParam(false, "Api Service Code already exists : " + updateApiServiceRequest.getApiServiceCode());
            }

            modelMapper.map(updateApiServiceRequest, mstApiService);
            mstApiService.setModifiedDate(LocalDateTime.now());
            mstApiService.setModifiedIpAddr(idAddressGenerator.getClientIp(httpServletRequest));
            mstApiService.setModifiedBy("SYSTEM");

            mstApiServiceRepository.save(mstApiService);
            logger.info("ApiService updated successfully, GUID={}", apiServiceGuid);

            return new StatusParam(true, "ApiService Updated successfully");
        } catch (ResourceNotFoundException e) {
            logger.error("Validation error while updating Api Service. GUID={}, Request={}", apiServiceGuid, updateApiServiceRequest, e);
            return  new StatusParam(false,e.getMessage());
        } catch (Exception ex) {
            logger.error("Unexpected error while updating Api Service. GUID={}, Request={}", apiServiceGuid, updateApiServiceRequest, ex);
            throw new RuntimeException("Error while updating Api Service: " + ex.getMessage(), ex);
        }
    }

}