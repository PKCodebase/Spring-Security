package com.nic.master.service.admservice.impl;

import com.nic.master.entity.adm.MstApi;
import com.nic.master.entity.adm.MstMicroservice;
import com.nic.master.entity.adm.MstUrlType;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.adm.MstApiRepository;
import com.nic.master.repository.adm.MstMicroserviceRepository;
import com.nic.master.repository.adm.MstUrlRepository;
import com.nic.master.request.adm.apiRequest.AddMstApiRequest;
import com.nic.master.response.mstapiresponse.MstApiResponse;
import com.nic.master.service.admservice.MstApiService;
import jakarta.servlet.http.HttpServletRequest;
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

    public MstApiImpl(MstApiRepository mstApiRepository, ModelMapper modelMapper, MstMicroserviceRepository mstMicroserviceRepository, MstUrlRepository mstUrlRepository, HttpServletRequest httpServletRequest) {
        this.mstApiRepository = mstApiRepository;
        this.modelMapper = modelMapper;
        this.mstMicroserviceRepository = mstMicroserviceRepository;
        this.mstUrlRepository = mstUrlRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public StatusParam addMstApi(String microserviceGuid,String urlTypeGuid,AddMstApiRequest addMstApiRequest) {
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
                logger.warn("ApiCode already exist : " + addMstApiRequest.getApiCode());
                return new StatusParam(false,"ApiCode already exist : " + addMstApiRequest.getApiCode());
            }
//
//            if(mstApiRepository.existsByMicroserviceAndUrlType(mstMicroservice, mstUrlType)) {
//                logger.warn("API already exists for this Microservice and URL Type combination.");
//                return new StatusParam(false, "API already exists for Microservice ["
//                        + mstMicroservice.getMicroserviceName() + "] and UrlType [" + mstUrlType.getUrlTypeName() + "]");
//            }

            if (mstApiRepository.existsByMicroservice(mstMicroservice)) {
                logger.warn("API already exists for this Microservice.");
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
            mstApi.setCreatedIpAddr(getClientIp());
            mstApi.setCreatedBy("SYSTEM");
            mstApi.setMicroservice(mstMicroservice);
            mstApi.setUrlType(mstUrlType);
            mstApiRepository.save(mstApi);
            return new StatusParam(true,"MstApi added successfully ");
        }catch (Exception ex){
            throw new RuntimeException("Unexpected error while adding ward : " + addMstApiRequest,ex);
        }
    }

    @Override
    public List<MstApiResponse> getAllMstApi() {
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


    private String getClientIp(){
        String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
        if(clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)){
            clientIp=httpServletRequest.getRemoteAddr();
        }
        return clientIp;
    }
}
