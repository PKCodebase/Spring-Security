package com.nic.master.repository.mst;

import com.nic.master.entity.mst.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone,String> {

    List<Zone> findByIsActive(Boolean isActive);

    Optional<Zone> findByZoneCode(String zoneCode);

    boolean existsByZoneCodeIgnoreCase(String zoneCode);
}
