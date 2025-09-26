package com.nic.master.service.process.impl;

import com.nic.master.entity.process.RefTemplate;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.SelectOptionParam;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.RefTemplateRepository;
import com.nic.master.requestDTO.process.reftemplate.AddRefTemplateRequest;
import com.nic.master.requestDTO.process.reftemplate.UpdateRefTemplateRequest;
import com.nic.master.responseDTO.refresponse.RefTemplateResponse;
import com.nic.master.service.process.RefTemplateService;
import com.nic.master.util.IpAddressGenerator;
import com.nic.master.util.MacAddressGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefTemplateServiceImpl implements RefTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(RefTemplateServiceImpl.class);

    private final EntityManager em;
    private final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;
    private final RefTemplateRepository refTemplateRepository;
    private final MacAddressGenerator macAddressGenerator;
    private final ModelMapper modelMapper;

    public RefTemplateServiceImpl(EntityManager em,
                                  HttpServletRequest httpServletRequest,
                                  IpAddressGenerator ipAddressGenerator,
                                  RefTemplateRepository refTemplateRepository,
                                  MacAddressGenerator macAddressGenerator,
                                  ModelMapper modelMapper) {
        this.em = em;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
        this.refTemplateRepository = refTemplateRepository;
        this.macAddressGenerator = macAddressGenerator;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public StatusParam addRefTemplate(String processDefGuid, String sectionTypeGuid, String actionTypeGuid,
                                      AddRefTemplateRequest request) {
        try {
            logger.info("Starting addRefTemplate - ProcessDefGUID: {}, SectionTypeGUID: {}, ActionTypeGUID: {}, RefTemplateCode: {}",
                    processDefGuid, sectionTypeGuid, actionTypeGuid, request.getRefTemplateCode());

            if (request.getCreatedIpAddr() == null)
                request.setCreatedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            if (request.getCreatedMacAddr() == null)
                request.setCreatedMacAddr(macAddressGenerator.generateMacAddress());
            request.setCreatedUri(httpServletRequest.getRequestURI());

            Query q = em.createNativeQuery(
                    "SELECT process.fn_add_ref_template(" +
                            ":processDefGuid, :sectionTypeGuid, :actionTypeGuid, " +
                            ":refTemplateCode, :refTemplateName, :havePrefillData, :prefillDataResultQuery, " +
                            ":isQueryRaiseCheck, :queryCheckMessage, :createdBy, :createdRemarks, " +
                            ":createdIpAddr, :createdMacAddr, :createdUri)"
            );

            q.setParameter("processDefGuid", processDefGuid);
            q.setParameter("sectionTypeGuid", sectionTypeGuid);
            q.setParameter("actionTypeGuid", actionTypeGuid);
            q.setParameter("refTemplateCode", request.getRefTemplateCode());
            q.setParameter("refTemplateName", request.getRefTemplateName());
            q.setParameter("havePrefillData", request.getHavePrefillData());
            q.setParameter("prefillDataResultQuery", request.getPrefillDataResultQuery());
            q.setParameter("isQueryRaiseCheck", request.getIsQueryRaiseCheck());
            q.setParameter("queryCheckMessage", request.getQueryCheckMessage());
            q.setParameter("createdBy", request.getCreatedBy());
            q.setParameter("createdRemarks", request.getCreatedRemarks());
            q.setParameter("createdIpAddr", request.getCreatedIpAddr());
            q.setParameter("createdMacAddr", request.getCreatedMacAddr());
            q.setParameter("createdUri", request.getCreatedUri());

            String result = (String) q.getSingleResult();
            logger.info("addRefTemplate result: {}", result);

            return switch (result) {
                case "SUCCESS" -> new StatusParam(true, "RefTemplate added successfully.");
                case "DUPLICATE: RefTemplateCode" ->
                        new StatusParam(false, "RefTemplate already exists with code: " + request.getRefTemplateCode());
                case "DUPLICATE: Combination" ->
                        new StatusParam(false, "RefTemplate already exists for this ProcessDef + SectionType + ActionType combination");
                case "NOT_FOUND: ProcessDef" ->
                        new StatusParam(false, "ProcessDef not found with GUID: " + processDefGuid);
                case "NOT_FOUND: SectionType" ->
                        new StatusParam(false, "SectionType not found with GUID: " + sectionTypeGuid);
                case "NOT_FOUND: ActionType" ->
                        new StatusParam(false, "ActionType not found with GUID: " + actionTypeGuid);
                case "ERROR: prefillDataResultQuery required" ->
                        new StatusParam(false, "Prefill Data Result Query is required when havePrefillData is true");
                case "ERROR: queryCheckMessage required" ->
                        new StatusParam(false, "Query Check Message is required when isQueryRaiseCheck is true");
                default ->
                        new StatusParam(false, "Unknown error: " + result);
            };

        } catch (Exception ex) {
            logger.error("Exception in addRefTemplate - RefTemplateCode: {}, ProcessDefGUID: {}",
                    request.getRefTemplateCode(), processDefGuid, ex);
            return new StatusParam(false, "Error while adding RefTemplate: " + ex.getMessage());
        }
    }

    @Override
    public List<RefTemplateResponse> getAllRefTemplates() {
        logger.info("Fetching all RefTemplates...");
        List<RefTemplate> refTemplates = em.createQuery("SELECT r FROM RefTemplate r", RefTemplate.class)
                .getResultList();

        logger.info("Fetched {} RefTemplates", refTemplates.size());
        return refTemplates.stream()
                .map(refTemplate -> modelMapper.map(refTemplate, RefTemplateResponse.class))
                .toList();
    }

    @Override
    public RefTemplateResponse getRefTemplateByGuid(String refTemplateGuid) {
        logger.info("Fetching RefTemplate by GUID: {}", refTemplateGuid);
        RefTemplate refTemplate = em.createQuery(
                        "SELECT r FROM RefTemplate r WHERE r.refTemplateGuid = :refTemplateGuid", RefTemplate.class)
                .setParameter("refTemplateGuid", refTemplateGuid)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> {
                    logger.warn("RefTemplate not found with GUID: {}", refTemplateGuid);
                    return new ResourceNotFoundException("RefTemplate not found with GUID: " + refTemplateGuid);
                });

        logger.info("Found RefTemplate: {} - {}", refTemplate.getRefTemplateCode(), refTemplate.getRefTemplateName());
        return modelMapper.map(refTemplate, RefTemplateResponse.class);
    }

    @Override
    public SelectOptionParam getRefTemplateByCode(String refTemplateCode) {
        logger.info("Fetching RefTemplate by Code: {}", refTemplateCode);
        return em.createQuery(
                        "SELECT r FROM RefTemplate r WHERE r.refTemplateCode = :refTemplateCode", RefTemplate.class)
                .setParameter("refTemplateCode", refTemplateCode)
                .getResultStream()
                .findFirst()
                .map(refTemplate -> {
                    logger.info("Found RefTemplate by code: {} - GUID: {}", refTemplateCode, refTemplate.getRefTemplateGuid());
                    return new SelectOptionParam(
                            refTemplate.getRefTemplateCode(),
                            refTemplate.getRefTemplateName(),
                            refTemplate.getRefTemplateGuid()
                    );
                })
                .orElseThrow(() -> {
                    logger.warn("RefTemplate not found with code: {}", refTemplateCode);
                    return new ResourceNotFoundException("RefTemplate not found with code: " + refTemplateCode);
                });
    }

    @Override
    @Transactional
    public StatusParam updateRefTemplate(String processDefGuid,
                                         String sectionTypeGuid,
                                         String actionTypeGuid,
                                         String refTemplateGuid,
                                         UpdateRefTemplateRequest request) {
        try {
            logger.info("Starting updateRefTemplate - GUID: {}, Code: {}, ProcessDefGUID: {}",
                    refTemplateGuid, request.getRefTemplateCode(), processDefGuid);

            // Generate IP/MAC/URI if null
            if (request.getModifiedIpAddr() == null)
                request.setModifiedIpAddr(ipAddressGenerator.getClientIp(httpServletRequest));
            if (request.getModifiedMacAddr() == null)
                request.setModifiedMacAddr(macAddressGenerator.generateMacAddress());
            if (request.getModifiedUri() == null)
                request.setModifiedUri(httpServletRequest.getRequestURI());

            // Nullify query fields if boolean flags are false
            if (Boolean.FALSE.equals(request.getHavePrefillData())) {
                request.setPrefillDataResultQuery(null);
            }
            if (Boolean.FALSE.equals(request.getIsQueryRaiseCheck())) {
                request.setQueryCheckMessage(null);
            }

            // Call native function
            String result = refTemplateRepository.updateRefTemplateNative(
                    processDefGuid,
                    sectionTypeGuid,
                    actionTypeGuid,
                    refTemplateGuid,
                    request.getRefTemplateCode(),
                    request.getRefTemplateName(),
                    request.getHavePrefillData(),
                    request.getPrefillDataResultQuery(),
                    request.getIsQueryRaiseCheck(),
                    request.getQueryCheckMessage(),
                    request.getModifiedBy(),
                    request.getModifiedRemarks(),
                    request.getModifiedIpAddr(),
                    request.getModifiedMacAddr(),
                    request.getModifiedUri(),
                    request.getIsActive()
            );

            logger.info("updateRefTemplate result for GUID {}: {}", refTemplateGuid, result);

            return switch (result) {
                case "Updated Successfully" -> {
                    logger.info("RefTemplate updated successfully - GUID: {}, Code: {}", refTemplateGuid, request.getRefTemplateCode());
                    yield new StatusParam(true, "RefTemplate updated successfully.");
                }
                case "NOT_FOUND: RefTemplate" -> {
                    logger.warn("RefTemplate not found - GUID: {}", refTemplateGuid);
                    yield new StatusParam(false, "RefTemplate not found with GUID: " + refTemplateGuid);
                }
                case "DUPLICATE: RefTemplateCode" -> {
                    logger.warn("Duplicate RefTemplate code attempted - Code: {}, GUID: {}", request.getRefTemplateCode(), refTemplateGuid);
                    yield new StatusParam(false, "RefTemplate already exists with code: " + request.getRefTemplateCode());
                }
                case "NOT_FOUND: ProcessDef" -> {
                    logger.warn("ProcessDef not found - GUID: {}", processDefGuid);
                    yield new StatusParam(false, "ProcessDef not found with GUID: " + processDefGuid);
                }
                case "NOT_FOUND: SectionType" -> {
                    logger.warn("SectionType not found - GUID: {}", sectionTypeGuid);
                    yield new StatusParam(false, "SectionType not found with GUID: " + sectionTypeGuid);
                }
                case "NOT_FOUND: ActionType" -> {
                    logger.warn("ActionType not found - GUID: {}", actionTypeGuid);
                    yield new StatusParam(false, "ActionType not found with GUID: " + actionTypeGuid);
                }
                default -> {
                    logger.error("Unknown error while updating RefTemplate - Result: {}, GUID: {}", result, refTemplateGuid);
                    yield new StatusParam(false, "Unknown error: " + result);
                }
            };

        } catch (Exception ex) {
            logger.error("Exception while updating RefTemplate - GUID: {}, Code: {}, ProcessDefGUID: {}",
                    refTemplateGuid, request.getRefTemplateCode(), processDefGuid, ex);
            return new StatusParam(false, "Error while updating RefTemplate: " + ex.getMessage());
        }
    }

}
