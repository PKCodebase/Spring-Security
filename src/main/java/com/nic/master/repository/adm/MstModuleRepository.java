package com.nic.master.repository.adm;

import com.nic.master.entity.adm.MstModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MstModuleRepository extends JpaRepository<MstModule,String> {

    boolean existsByModuleCodeIgnoreCase(String moduleCode);

    Optional<MstModule> findByModuleCode(String moduleCode);

    Optional<MstModule> findByModuleGuid(String moduleGuid);
}
