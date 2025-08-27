package com.nic.master.repository.adm;

import com.nic.master.entity.adm.MstMicroservice;
import com.nic.master.param.SelectOptionParam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MstMicroserviceRepository extends JpaRepository<MstMicroservice,String> {

    boolean existsByMicroserviceCodeIgnoreCase(String microserviceCode);

    Optional<MstMicroservice> findByMicroserviceGuid(String microserviceGuid);

    Optional<MstMicroservice> findByMicroserviceCodeIgnoreCase(String microserviceCode);
}
