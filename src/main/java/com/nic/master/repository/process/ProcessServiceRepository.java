package com.nic.master.repository.process;

import com.nic.master.entity.process.ProcessService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcessServiceRepository extends JpaRepository<ProcessService,Long> {

    Optional<ProcessService> findByProcessServiceGuid(String processServiceGuid);
}
