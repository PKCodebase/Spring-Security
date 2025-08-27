package com.nic.master.repository.adm;

import com.nic.master.entity.adm.MstApiService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MstApiServiceRepository extends JpaRepository<MstApiService,String> {

    boolean existsByApiServiceCodeIgnoreCase (String apiServiceCode);

    Optional<MstApiService> findByApiServiceCodeIgnoreCase(String apiServiceCode);

    Optional<MstApiService> findByApiServiceGuid(String apiServiceGuid);
}
