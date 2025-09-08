package com.nic.master.repository.process;

import com.nic.master.entity.process.MstColumnType;
import com.nic.master.entity.process.MstDocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MstDocumentTypeRepository extends JpaRepository<MstDocumentType,Long> {
    boolean existsByDocumentTypeCodeIgnoreCase(String documentTypeCode);

    Optional<MstDocumentType> findByDocumentTypeGuid(String documentTypeGuid);

    Optional<MstDocumentType> findByDocumentTypeCodeIgnoreCase(String documentTypeCode);
}
