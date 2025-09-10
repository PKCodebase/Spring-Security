package com.nic.master.repository.process;

import com.nic.master.entity.process.MstSectionType;
import com.nic.master.entity.process.ProcessDef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessDefRepository extends JpaRepository<ProcessDef,Long> {
    boolean existsByProcessDefCodeIgnoreCase(String processDefCode);

    Optional<ProcessDef> findByProcessDefCodeIgnoreCase(String processDefCode);

    Optional<ProcessDef> findByProcessDefGuid(String processDefGuid);

}
