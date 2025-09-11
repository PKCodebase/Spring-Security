package com.nic.master.repository.process;

import com.nic.master.entity.process.MstActionType;
import com.nic.master.entity.process.ProcessAlert;
import com.nic.master.entity.process.ProcessDef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessAlertRepository extends JpaRepository<ProcessAlert,Long> {

    Optional<ProcessAlert> findByProcessAlertGuid(String processAlertGuid);

    boolean existsByProcessDefAndMstActionType(ProcessDef processDef, MstActionType mstActionType);

//    boolean existsByProcessDefAndMstActionType(ProcessDef processDef, MstActionType mstActionType, String processAlertGuid);
}
