package com.nic.master.repository.process;

import com.nic.master.entity.process.ProcessDef;
import com.nic.master.entity.process.ProcessDefConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessDefConfigRepository extends JpaRepository<ProcessDefConfig,Long> {

//    boolean existsByProcessDefConfigCodeIgnoreCase(String processDefConfigCode);

    Optional<ProcessDefConfig> findByProcessDefConfigGuid(String processDefConfigGuid);
//
//    Optional<ProcessDefConfig> findByProcessDefConfigCodeIgnoreCase(String processDefConfigCode);

    Optional<ProcessDefConfig> findByProcessDef(ProcessDef processDef);
}
