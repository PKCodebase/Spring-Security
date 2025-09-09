package com.nic.master.repository.process;

import com.nic.master.entity.process.MstProcessType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MstProcessTypeRepository extends JpaRepository<MstProcessType,Long> {

    boolean existsByProcessTypeCodeIgnoreCase(String processTypeCode);

    Optional<MstProcessType> findByProcessTypeCodeIgnoreCase(String processTypeCode);

    Optional<MstProcessType> findByProcessTypeGuid(String processTypeGuid);
}
