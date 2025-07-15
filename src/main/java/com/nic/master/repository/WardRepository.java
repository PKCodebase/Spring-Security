package com.nic.master.repository;

import com.nic.master.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WardRepository extends JpaRepository<Ward,String> {

    Optional<Ward> findByWardCode(String wardCode);


    Optional<Ward> findByWardGuid(String wardGuid);

    List<Ward> findByIsActive(Boolean isActive);

    boolean existsByWardCodeIgnoreCase(String wardCode);
}
