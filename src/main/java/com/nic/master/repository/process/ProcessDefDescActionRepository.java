package com.nic.master.repository.process;

import com.nic.master.entity.process.ProcessDefDescAction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ProcessDefDescActionRepository extends JpaRepository<ProcessDefDescAction,Long> {


    boolean existsByProcessDefDesc_ProcessDefDescGuidAndActionType_ActionTypeGuid(String processDefDescGuid, String actionTypeGuid);

    Optional<ProcessDefDescAction> findByProcessDefDescActionGuid(String processDefDescActionGuid);}
