package com.nic.master.repository.process;

import com.nic.master.entity.process.MstActionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MstActionTypeRepository extends JpaRepository<MstActionType,Long> {

    boolean existsByActionTypeCodeIgnoreCase(String actionTypeCode);

    Optional<MstActionType> findByActionTypeGuid(String actionTypeGuid);

    Optional<MstActionType> findByActionTypeCodeIgnoreCase(String actionTypeCode);
}
