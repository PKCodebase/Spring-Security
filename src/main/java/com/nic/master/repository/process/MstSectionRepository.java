package com.nic.master.repository.process;

import com.nic.master.entity.process.MstSectionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MstSectionRepository extends JpaRepository<MstSectionType,Long> {

    boolean existsBySectionTypeCodeIgnoreCase(String sectionTypeCode);

    Optional<MstSectionType> findBySectionTypeCodeIgnoreCase(String sectionTypeCode);

    Optional<MstSectionType> findBySectionTypeGuid(String sectionTypeGuid);


}
