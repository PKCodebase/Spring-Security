package com.nic.master.repository;

import com.nic.master.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentType, String> {

    Optional<DocumentType> findByDocumentCode(String documentCode);

    List<DocumentType> findByIsActive(Boolean isActive);

    boolean existsByDocumentCodeIgnoreCase(String documentCode);
}
