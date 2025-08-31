package com.nic.master.repository.adm;

import com.nic.master.entity.adm.MstApi;
import com.nic.master.entity.adm.MstUrlType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MstUrlRepository extends JpaRepository<MstUrlType,String> {
    boolean existsByUrlTypeCodeIgnoreCase(String apiCode);

    Optional<MstUrlType> findByUrlTypeGuid(String urlGuid);



    Optional<MstUrlType> findByUrlTypeCodeIgnoreCase(String urlCode);


}
