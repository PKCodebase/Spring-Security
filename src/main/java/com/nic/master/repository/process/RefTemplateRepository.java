package com.nic.master.repository.process;

import com.nic.master.entity.process.RefTemplate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository  // optional, but good practice
public interface RefTemplateRepository extends JpaRepository<RefTemplate, Long> {


    @Transactional
    @Modifying
    @Query(value = "SELECT process.fn_add_ref_template(:processDefGuid, :sectionTypeGuid, :actionTypeGuid, " +
            ":refTemplateCode, :refTemplateName, :havePrefillData, :prefillDataResultQuery, :isQueryRaiseCheck, " +
            ":queryCheckMessage, :createdBy, :createdRemarks, :createdIpAddr, :createdMacAddr, :createdUri)", nativeQuery = true)
    String addRefTemplateNative(
            @Param("processDefGuid") String processDefGuid,
            @Param("sectionTypeGuid") String sectionTypeGuid,
            @Param("actionTypeGuid") String actionTypeGuid,
            @Param("refTemplateCode") String refTemplateCode,
            @Param("refTemplateName") String refTemplateName,
            @Param("havePrefillData") Boolean havePrefillData,
            @Param("prefillDataResultQuery") String prefillDataResultQuery,
            @Param("isQueryRaiseCheck") Boolean isQueryRaiseCheck,
            @Param("queryCheckMessage") String queryCheckMessage,
            @Param("createdBy") String createdBy,
            @Param("createdRemarks") String createdRemarks,
            @Param("createdIpAddr") String createdIpAddr,
            @Param("createdMacAddr") String createdMacAddr,
            @Param("createdUri") String createdUri
    );

    @Transactional
    @Query(value = "SELECT process.fn_update_ref_template(:processDefGuid, :sectionTypeGuid, :actionTypeGuid, :refTemplateGuid, " +
            ":refTemplateCode, :refTemplateName, :havePrefillData, :prefillDataResultQuery, " +
            ":isQueryRaiseCheck, :queryCheckMessage, :modifiedBy, :modifiedRemarks, " +
            ":modifiedIpAddr, :modifiedMacAddr, :modifiedUri, :isActive)", nativeQuery = true)
    String updateRefTemplateNative(
            @Param("processDefGuid") String processDefGuid,
            @Param("sectionTypeGuid") String sectionTypeGuid,
            @Param("actionTypeGuid") String actionTypeGuid,
            @Param("refTemplateGuid") String refTemplateGuid,
            @Param("refTemplateCode") String refTemplateCode,
            @Param("refTemplateName") String refTemplateName,
            @Param("havePrefillData") Boolean havePrefillData,
            @Param("prefillDataResultQuery") String prefillDataResultQuery,
            @Param("isQueryRaiseCheck") Boolean isQueryRaiseCheck,
            @Param("queryCheckMessage") String queryCheckMessage,
            @Param("modifiedBy") String modifiedBy,
            @Param("modifiedRemarks") String modifiedRemarks,
            @Param("modifiedIpAddr") String modifiedIpAddr,
            @Param("modifiedMacAddr") String modifiedMacAddr,
            @Param("modifiedUri") String modifiedUri,
            @Param("isActive") Boolean isActive
    );

}
