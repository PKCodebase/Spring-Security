package com.nic.master.repository.process;

//import com.nic.master.entity.process.ProcessedRequestRole;
import com.nic.master.entity.process.ProcessedRequestRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessRequestRoleRepository  extends JpaRepository<ProcessedRequestRole,Long> {

    boolean existsByProcessDef_ProcessDefGuid(String processDefGuid);

  Optional<ProcessedRequestRole> findByProcessedRequestRoleGuid(String processedRequestRoleGuid);

}
