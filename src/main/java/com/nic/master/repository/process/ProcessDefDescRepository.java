package com.nic.master.repository.process;

import com.nic.master.entity.process.MstSectionType;
import com.nic.master.entity.process.ProcessDef;
import com.nic.master.entity.process.ProcessDefDesc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessDefDescRepository extends JpaRepository<ProcessDefDesc,Long> {
    boolean existsByRoleCodeIgnoreCase(String roleCode);

    Optional<ProcessDefDesc> findByRoleCodeIgnoreCase(String roleCode);

    Optional<ProcessDefDesc> findByProcessDefDescGuid(String processDefDescGuid);

    boolean existsByProcessDefAndLevelNum(ProcessDef processDef, Integer levelNum);

//    boolean existsByProcessDefAndMstSectionType(ProcessDef processDef, MstSectionType mstSectionType);
}
