package com.nic.master.repository.process;

import com.nic.master.entity.process.RefQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefQueryRepository extends JpaRepository<RefQuery,Long> {

    @Query(value = "SELECT process.add_ref_query(:processDefGuid, :processDefDescGuid, :sectionTypeGuid, " +
            ":refQueryName, :refQueryCode, :prefillDataResultQuery, :createdRemarks, :createdUri, :raiseLimit, :createdIp, :createdMac)",
            nativeQuery = true)
    String addRefQueryNative(
            @Param("processDefGuid") String processDefGuid,
            @Param("processDefDescGuid") String processDefDescGuid,
            @Param("sectionTypeGuid") String sectionTypeGuid,
            @Param("refQueryName") String refQueryName,
            @Param("refQueryCode") String refQueryCode,
            @Param("prefillDataResultQuery") String prefillDataResultQuery,
            @Param("createdRemarks") String createdRemarks,
            @Param("createdUri") String createdUri,
            @Param("raiseLimit") Integer raiseLimit,
            @Param("createdIp") String createdIp,
            @Param("createdMac") String createdMac
    );

}
