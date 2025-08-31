package com.nic.master.service.admservice.impl;

import com.nic.master.entity.adm.MstUrlType;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.adm.MstUrlRepository;
import com.nic.master.request.adm.msturlrequest.AddMstUrlRequest;
import com.nic.master.response.mstapiresponse.MstApiResponse;
import com.nic.master.response.msturlresponse.MstUrlResponse;
import com.nic.master.service.admservice.MstUrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MstUrlServiceImpl implements MstUrlService {


    private  final MstUrlRepository mstUrlRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;

    public MstUrlServiceImpl(MstUrlRepository mstUrlRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest) {
        this.mstUrlRepository = mstUrlRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public StatusParam addMstUrl(AddMstUrlRequest addMstUrlRequest) {
        try{
            if(mstUrlRepository.existsByUrlTypeCodeIgnoreCase(addMstUrlRequest.getUrlTypeCode().trim())){
                return new StatusParam(false,"MstUrlType Code already exist : " + addMstUrlRequest.getUrlTypeCode());
            }
            MstUrlType mstUrlType = modelMapper.map(addMstUrlRequest,MstUrlType.class);
            mstUrlType.setUrlTypeGuid(UUID.randomUUID().toString());
            mstUrlType.setCreatedIpAddr(getClientIp());
            mstUrlType.setCreatedDate(LocalDateTime.now());
            mstUrlType.setCreatedIpAddr(getClientIp());
            mstUrlType.setCreatedBy("SYSTEM");
            mstUrlType.setIsActive(true);
            mstUrlRepository.save(mstUrlType);
            return new StatusParam(true,"UrlType Added Successfully");
        }catch(Exception ex){
            throw  new RuntimeException("Error while adding MstUrl :");
        }
    }

    @Override
    public List<MstUrlResponse> getAllUrl() {
        return mstUrlRepository.findAll()
                .stream()
                .map(urlType -> modelMapper.map(urlType, MstUrlResponse.class))
                .toList();
    }

    private String getClientIp() {
        String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
        if(clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)){
            clientIp = httpServletRequest.getRemoteAddr();
        }
        return clientIp;
    }
}
