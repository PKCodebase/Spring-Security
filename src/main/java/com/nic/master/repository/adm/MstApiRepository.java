package com.nic.master.repository.adm;

import com.nic.master.entity.adm.MstApi;
import com.nic.master.entity.adm.MstMicroservice;
import com.nic.master.entity.adm.MstUrlType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MstApiRepository extends JpaRepository<MstApi,String> {

    boolean existsByApiCodeIgnoreCase(String apiCode);

    boolean existsByMicroserviceAndUrlType(MstMicroservice microservice, MstUrlType urlType);


    boolean existsByMicroservice(MstMicroservice mstMicroservice);

    boolean existsByUrlType(MstUrlType mstUrlType);
}
