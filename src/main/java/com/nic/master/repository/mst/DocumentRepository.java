package com.nic.master.repository.mst;

import com.nic.master.entity.mst.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentType, String> {

    Optional<DocumentType> findByDocumentCodeIgnoreCase(String documentCode);

    List<DocumentType> findByIsActive(Boolean isActive);

    boolean existsByDocumentCodeIgnoreCase(String documentCode);
}
