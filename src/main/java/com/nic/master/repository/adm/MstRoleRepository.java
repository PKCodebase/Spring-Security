package com.nic.master.repository.adm;

import com.nic.master.entity.adm.MstRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MstRoleRepository extends JpaRepository<MstRole,String> {

    boolean existsByRoleCodeIgnoreCase(String roleCode);

    Optional<MstRole> findByRoleCodeIgnoreCase(String roleCode);

    Optional<MstRole> findByRoleGuid(String roleGuid);
}
