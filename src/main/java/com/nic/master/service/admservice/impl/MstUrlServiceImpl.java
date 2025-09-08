package com.nic.master.service.admservice.impl;

import com.nic.master.entity.adm.MstUrlType;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.adm.MstUrlRepository;
import com.nic.master.request.adm.msturlrequest.AddMstUrlRequest;
import com.nic.master.request.adm.msturlrequest.UpdateMstUrlRequest;
import com.nic.master.response.msturlresponse.MstUrlResponse;
import com.nic.master.service.admservice.MstUrlService;
import com.nic.master.util.IdAddressGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MstUrlServiceImpl implements MstUrlService {

    private static final Logger logger  = LoggerFactory.getLogger(MstUrlServiceImpl.class);
    private  final MstUrlRepository mstUrlRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;
    private final IdAddressGenerator idAddressGenerator;


    public MstUrlServiceImpl(MstUrlRepository mstUrlRepository, ModelMapper modelMapper, HttpServletRequest httpServletRequest, IdAddressGenerator idAddressGenerator) {
        this.mstUrlRepository = mstUrlRepository;
        this.modelMapper = modelMapper;
        this.httpServletRequest = httpServletRequest;
        this.idAddressGenerator = idAddressGenerator;
    }

    @Override
    public StatusParam addMstUrl(AddMstUrlRequest addMstUrlRequest) {
        try{
            if(mstUrlRepository.existsByUrlTypeCodeIgnoreCase(addMstUrlRequest.getUrlTypeCode().trim())){
                return new StatusParam(false,"MstUrlType Code already exist : " + addMstUrlRequest.getUrlTypeCode());
            }
            MstUrlType mstUrlType = modelMapper.map(addMstUrlRequest,MstUrlType.class);
            mstUrlType.setUrlTypeGuid(UUID.randomUUID().toString());
            mstUrlType.setCreatedIpAddr(idAddressGenerator.getClientIp(httpServletRequest));
            mstUrlType.setCreatedDate(LocalDateTime.now());
            mstUrlType.setCreatedIpAddr(idAddressGenerator.getClientIp(httpServletRequest));
            mstUrlType.setCreatedBy("SYSTEM");
            mstUrlRepository.save(mstUrlType);
            return new StatusParam(true,"UrlType Added Successfully");
        }
        catch(Exception ex){
            throw  new RuntimeException("Error while adding MstUrl :");
        }
    }

    @Override
    public List<MstUrlResponse> getAllUrl() {
        List<MstUrlType> mstUrlTypes = mstUrlRepository.findAll();

        return mstUrlTypes.stream()
                .map(mstUrl -> modelMapper.map(mstUrl, MstUrlResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public MstUrlResponse getApiUrlByGuid(String urlGuid){
        MstUrlType mstUrlType = mstUrlRepository.findByUrlTypeGuid(urlGuid.trim())
                .orElseThrow(()-> new ResourceNotFoundException("Url Guid not found : "+ urlGuid));
        return modelMapper.map(mstUrlType, MstUrlResponse.class);

    }


    @Override
    public SelectOptionParam getApiUrlByCode(String urlCode) {
        return mstUrlRepository.findByUrlTypeCodeIgnoreCase(urlCode.trim())
                .map(mstUrlType -> {
                    return new SelectOptionParam(
                            mstUrlType.getUrlTypeGuid(),
                            mstUrlType.getUrlTypeCode(),
                            mstUrlType.getUrlTypeName()
                    );
                })
                .orElseThrow(()->{
                    return new ResourceNotFoundException("Ap not found with guid :" + urlCode);
                });
    }
    @Override
    public StatusParam updateMstUrlByGuid(String urlGuid, UpdateMstUrlRequest updateMstUrlRequest) {
        try {
            MstUrlType mstUrlType = mstUrlRepository.findByUrlTypeGuid(urlGuid)
                    .orElseThrow(() -> new ResourceNotFoundException("Url Guid not found: " + urlGuid));

            // Check duplicate UrlTypeCode
            if (updateMstUrlRequest.getUrlTypeCode() != null
                    && !updateMstUrlRequest.getUrlTypeCode().equalsIgnoreCase(mstUrlType.getUrlTypeCode())
                    && mstUrlRepository.existsByUrlTypeCodeIgnoreCase(updateMstUrlRequest.getUrlTypeCode())) {
                return new StatusParam(false, "Url Code already exists: " + updateMstUrlRequest.getUrlTypeCode());
            }

            // Map updated values
            modelMapper.map(updateMstUrlRequest, mstUrlType);
            mstUrlType.setModifiedBy("SYSTEM");
            mstUrlType.setModifiedDate(LocalDateTime.now());
            mstUrlType.setModifiedIpAddr(idAddressGenerator.getClientIp(httpServletRequest));

            mstUrlRepository.save(mstUrlType);

            return new StatusParam(true, "Url updated successfully.");

        } catch (ResourceNotFoundException e) {
            return new StatusParam(false, e.getMessage());
        } catch (Exception e) {
            // Log full stack trace for debugging
            // Return real error message instead of generic one
            return new StatusParam(false, "Failed to update Url: " + e.getMessage());
        }
    }

}
