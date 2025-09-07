package com.nic.master.repository.process;

import com.nic.master.entity.process.MstActionType;
import com.nic.master.entity.process.MstColumnType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MstColumnTypeRepository extends JpaRepository<MstColumnType,Long> {

    boolean existsByColumnTypeCodeIgnoreCase(String columnTypeCode);

    Optional<MstColumnType> findByColumnTypeGuid(String columnTypeGuid);

    Optional<MstColumnType> findByColumnTypeCodeIgnoreCase(String columnTypeCode);
}
