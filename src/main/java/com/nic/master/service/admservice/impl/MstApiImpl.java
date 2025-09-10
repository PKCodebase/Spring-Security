package com.nic.master.service.admservice.impl;

import com.nic.master.entity.adm.MstApi;
import com.nic.master.entity.adm.MstMicroservice;
import com.nic.master.entity.adm.MstUrlType;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.adm.MstApiRepository;
import com.nic.master.repository.adm.MstMicroserviceRepository;
import com.nic.master.repository.adm.MstUrlRepository;
import com.nic.master.request.adm.apiRequest.AddMstApiRequest;
import com.nic.master.request.adm.apiRequest.UpdateMstApiRequest;
import com.nic.master.response.mstapiresponse.MstApiResponse;
import com.nic.master.service.admservice.MstApiService;
import com.nic.master.util.IpAddressGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MstApiImpl implements MstApiService {

    private static final Logger logger  = LoggerFactory.getLogger(MstApiImpl.class);
    private final MstApiRepository mstApiRepository;
    private final ModelMapper modelMapper;
    private final MstMicroserviceRepository mstMicroserviceRepository;
    private final MstUrlRepository mstUrlRepository;
    private final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;

    public MstApiImpl(MstApiRepository mstApiRepository, ModelMapper modelMapper, MstMicroserviceRepository mstMicroserviceRepository, MstUrlRepository mstUrlRepository, HttpServletRequest httpServletRequest, IpAddressGenerator ipAddressGenerator) {
        this.mstApiRepository = mstApiRepository;
        this.modelMapper = modelMapper;
        this.mstMicroserviceRepository = mstMicroserviceRepository;
        this.mstUrlRepository = mstUrlRepository;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
    }


    @Override
    public StatusParam addMstApi(String microserviceGuid,String urlTypeGuid,AddMstApiRequest addMstApiRequest) {
        logger.info("Attempting to add new API. microserviceGuid={}, urlTypeGuid={}, apiCode={}",
                microserviceGuid, urlTypeGuid, addMstApiRequest.getApiCode());
        try {
            MstMicroservice mstMicroservice = mstMicroserviceRepository.findById(microserviceGuid.trim())
                    .orElseThrow(() -> {
                        return new ResourceNotFoundException("Microservice Guid not found : " + microserviceGuid);
                    });
            MstUrlType mstUrlType = mstUrlRepository.findById(urlTypeGuid.trim())
                    .orElseThrow(()->{
                            return new  ResourceNotFoundException("UrlType Guid not found : " + urlTypeGuid);
        });
            if(mstApiRepository.existsByApiCodeIgnoreCase(addMstApiRequest.getApiCode().trim())){
                logger.warn("Duplicate API code detected: {}", addMstApiRequest.getApiCode());
                return new StatusParam(false,"ApiCode already exist : " + addMstApiRequest.getApiCode());
            }
//
//            if(mstApiRepository.existsByMicroserviceAndUrlType(mstMicroservice, mstUrlType)) {
//                logger.warn("API already exists for this Microservice and URL Type combination.");
//                return new StatusParam(false, "API already exists for Microservice ["
//                        + mstMicroservice.getMicroserviceName() + "] and UrlType [" + mstUrlType.getUrlTypeName() + "]");
//            }

            if (mstApiRepository.existsByMicroservice(mstMicroservice)) {
                logger.warn("API already exists for Microservice={} and UrlType={}",
                        mstMicroservice.getMicroserviceName(), mstUrlType.getUrlTypeName());
                return new StatusParam(false,
                        "API already exists for Microservice [" + mstMicroservice.getMicroserviceName() + "]");
            }

            if (mstApiRepository.existsByUrlType(mstUrlType)) {
                logger.warn("API already exists for this URL Type.");
                return new StatusParam(false,
                        "API already exists for UrlType [" + mstUrlType.getUrlTypeName() + "]");
            }

            if (mstApiRepository.existsByMicroserviceAndUrlType(mstMicroservice, mstUrlType)) {
                logger.warn("API already exists for this Microservice and URL Type combination.");
                return new StatusParam(false,
                        "API already exists for Microservice [" + mstMicroservice.getMicroserviceName() +
                                "] and UrlType [" + mstUrlType.getUrlTypeName() + "]");
            }


            MstApi mstApi = modelMapper.map(addMstApiRequest,MstApi.class);
            mstApi.setApiGuid(UUID.randomUUID().toString());
            mstApi.setCreatedDate(LocalDateTime.now());
            mstApi.setUrl(addMstApiRequest.getUrl());
            mstApi.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstApi.setCreatedBy("SYSTEM");
            mstApi.setMicroservice(mstMicroservice);
            mstApi.setUrlType(mstUrlType);
            mstApiRepository.save(mstApi);
            logger.info("API created successfully. apiGuid={}", mstApi.getApiGuid());
            return new StatusParam(true,"MstApi added successfully ");
        }catch (Exception ex){
            logger.error("Unexpected error while adding API. request={}, error={}", addMstApiRequest, ex.getMessage(), ex);
            throw new RuntimeException("Unexpected error while adding ward : " + addMstApiRequest,ex);
        }
    }

    @Override
    public List<MstApiResponse> getAllMstApi() {
        logger.info("Fetching all APIs");
        List<MstApi> mstApis = mstApiRepository.findAll();

        return mstApis.stream()
                .map(mstApi -> {
                    MstApiResponse response = modelMapper.map(mstApi, MstApiResponse.class);

                    // Manually set nested objects
                    if (mstApi.getMicroservice() != null) {
                        response.setMicroserviceGuid(mstApi.getMicroservice().getMicroserviceGuid());
                        response.setMicroserviceName(mstApi.getMicroservice().getMicroserviceName());
                    }

                    if (mstApi.getUrlType() != null) {
                        response.setUrlTypeGuid(mstApi.getUrlType().getUrlTypeGuid());
                        response.setUrlTypeName(mstApi.getUrlType().getUrlTypeName());
                    }

                    return response;
                })
                .toList();
    }

    @Override
    public MstApiResponse getMstApiByGuid(String apiGuid) {
        logger.info("Fetching API by guid={}", apiGuid);
        MstApi mstApi = mstApiRepository.findByApiGuid(apiGuid.trim())
                .orElseThrow(() -> new ResourceNotFoundException("Api Guid not found : " + apiGuid));

        // Convert Entity -> DTO
        return modelMapper.map(mstApi, MstApiResponse.class);
    }

    @Override
    public SelectOptionParam getMstApiByCode(String apiCode) {
        logger.info("Fetching API by code={}", apiCode);
        return mstApiRepository.findByApiCodeIgnoreCase(apiCode.trim())
                .map(mstApi ->{
                    return new SelectOptionParam(
                            mstApi.getApiGuid(),
                            mstApi.getApiCode(),
                            mstApi.getUrl()
                    );
                })
                .orElseThrow(()->{
                     return  new ResourceNotFoundException("Api Code not found with Code : " + apiCode);
                });
    }

    @Override
    @Transactional
    public StatusParam updateMstApiByGuid(String apiGuid, UpdateMstApiRequest updateMstApiRequest) {
        logger.info("Attempting to update API. apiGuid={}, newApiCode={}", apiGuid, updateMstApiRequest.getApiCode());

        try {
            MstApi mstApi = mstApiRepository.findByApiGuid(apiGuid)
                    .orElseThrow(() -> new ResourceNotFoundException("Api Guid not found: " + apiGuid));

            if (updateMstApiRequest.getApiCode() != null
                    && !updateMstApiRequest.getApiCode().equalsIgnoreCase(mstApi.getApiCode())
                    && mstApiRepository.existsByApiCodeIgnoreCase(updateMstApiRequest.getApiCode())) {
                logger.warn("Api code conflict detected. ExistingApiCode={}, RequestedApiCode={}",
                        mstApi.getApiCode(), updateMstApiRequest.getApiCode());
                return new StatusParam(false, "Api Code already exists: " + updateMstApiRequest.getApiCode());
            }

            modelMapper.map(updateMstApiRequest, mstApi);
            mstApi.setModifiedDate(LocalDateTime.now());
            mstApi.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            mstApi.setModifiedBy("SYSTEM");

            mstApiRepository.save(mstApi);
            logger.info("API updated successfully. apiGuid={}", apiGuid);

            return new StatusParam(true, "Api updated successfully");

        } catch (ResourceNotFoundException e) {
            logger.error("Business Exception while updating API. apiGuid={}, error={}", apiGuid, e.getMessage());
            return new StatusParam(false, e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating API. apiGuid={}, error={}", apiGuid, e.getMessage(), e);
            throw new RuntimeException("Unable to update API due to internal error", e);
        }
    }

}