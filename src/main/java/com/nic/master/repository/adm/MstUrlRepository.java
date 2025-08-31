package com.nic.master.repository.adm;

import com.nic.master.entity.adm.MstUrlType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MstUrlRepository extends JpaRepository<MstUrlType,String> {
    boolean existsByUrlTypeCodeIgnoreCase(String apiCode);



}
