package com.nic.master.service.process.impl;

import com.nic.master.entity.process.MstSectionType;
import com.nic.master.entity.process.ProcessDef;
import com.nic.master.entity.process.ProcessDefDesc;
import com.nic.master.entity.process.RefQuery;
import com.nic.master.exception.ResourceNotFoundException;
import com.nic.master.param.StatusParam;
import com.nic.master.repository.process.RefQueryRepository;
import com.nic.master.service.process.RefQueryService;
import com.nic.master.util.IpAddressGenerator;
import com.nic.master.util.MacAddressGenerator;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.util.Optional;


@Service
public class RefQueryServiceImpl implements RefQueryService {

    private static  final Logger logger = LoggerFactory.getLogger(RefQueryServiceImpl.class);

    private final EntityManager entityManager;
    private final HttpServletRequest httpServletRequest;
    private final IpAddressGenerator ipAddressGenerator;
    private final MacAddressGenerator macAddressGenerator;
    private final RefQueryRepository refQueryRepository;
    private final ModelMapper modelMapper;

    public RefQueryServiceImpl(EntityManager entityManager, HttpServletRequest httpServletRequest, IpAddressGenerator ipAddressGenerator, MacAddressGenerator macAddressGenerator, RefQueryRepository refQueryRepository, ModelMapper modelMapper) {
        this.entityManager = entityManager;
        this.httpServletRequest = httpServletRequest;
        this.ipAddressGenerator = ipAddressGenerator;
        this.macAddressGenerator = macAddressGenerator;
        this.refQueryRepository = refQueryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public StatusParam addRefQuery(String processDefGuid, String processDefDescGuid, String sectionTypeGuid, RefQuery refQuery) {
        logger.info("Adding Ref Query...");

        try {

            // Fetch ProcessDef with individual exception
            ProcessDef processDef = Optional.ofNullable(
                            entityManager.createQuery(
                                            "SELECT p FROM ProcessDef p WHERE p.processDefGuid = :guid", ProcessDef.class)
                                    .setParameter("guid", processDefGuid)
                                    .getResultStream()
                                    .findFirst()
                                    .orElse(null))
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Process Def not found with Guid : " + processDefGuid));

            // Fetch ProcessDefDesc with individual exception
            ProcessDefDesc processDefDesc = Optional.ofNullable(
                            entityManager.createQuery(
                                            "SELECT d FROM ProcessDefDesc d WHERE d.processDefDescGuid = :guid AND d.processDef = :processDef", ProcessDefDesc.class)
                                    .setParameter("guid", processDefDescGuid)
                                    .setParameter("processDef", processDef)
                                    .getResultStream()
                                    .findFirst()
                                    .orElse(null))
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Process Def Desc not found with Guid : " + processDefDescGuid + " for Process Def Guid : " + processDefGuid));

            // Fetch MstSectionType with individual exception
            MstSectionType mstSectionType = Optional.ofNullable(
                    entityManager.createQuery(
                                    "SELECT s FROM MstSectionType s WHERE s.sectionTypeGuid = :guid", MstSectionType.class)
                            .setParameter("guid", sectionTypeGuid)
                            .getResultStream()
                            .findFirst()
                            .orElse(null))
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Section Type not found with Guid : " + sectionTypeGuid));

            if (refQuery.getRefQueryCode() != null) {
                boolean duplicateExists = !entityManager.createQuery(
                                "SELECT r FROM RefQuery r WHERE LOWER(r.refQueryCode) = :code", RefQuery.class)
                        .setParameter("code", refQuery.getRefQueryCode().toLowerCase())
                        .getResultList()
                        .isEmpty();

                if (duplicateExists) {
                    return new StatusParam(false,
                            "Ref Query already exists with code : " + refQuery.getRefQueryCode());
                }
            }

            String clientIp = ipAddressGenerator.getClientIp(httpServletRequest);
            String clientMac = macAddressGenerator.generateMacAddress();

                // Call the DB function (auto-handles createdBy, createdDate, IP, MAC)
            String result = refQueryRepository.addRefQueryNative(
                    processDefGuid,
                    processDefDescGuid,
                    sectionTypeGuid,
                    refQuery.getRefQueryName(),
                    refQuery.getRefQueryCode(),
                    refQuery.getPrefillDataResultQuery(),
                    refQuery.getCreatedRemarks(),
                    refQuery.getCreatedUri(),
                    refQuery.getRaiseLimit(),
                    clientIp,
                    clientMac
            );

            logger.info("Ref Query created with GUID: {}", result);

            return new StatusParam(true, "Ref Query created successfully");

        } catch (ResourceNotFoundException ex) {
            // Let specific not-found errors propagate
            logger.error("Not found error while creating Ref Query", ex);
            throw ex; // or return StatusParam(false, ex.getMessage(), null) if you prefer
        } catch (Exception ex) {
            logger.error("Error while creating Ref Query", ex);
            return new StatusParam(false, "Error while creating Ref Query: " + ex.getMessage(), null);
        }
    }
}
