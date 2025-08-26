package com.nic.master.repository.mst;

import com.nic.master.entity.mst.Colony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ColonyRepository extends JpaRepository<Colony,String> {

    Optional<Colony> findByColonyCodeIgnoreCase(String colonyCode);

    List<Colony> findByIsActive(Boolean isActive);

    boolean existsByColonyCodeIgnoreCase(String colonyCode);
}
