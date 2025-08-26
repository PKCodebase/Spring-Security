	package com.nic.master.repository.mst;
	
	
	import java.util.List;
	import com.nic.master.enums.Status;
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;
	import com.nic.master.entity.mst.MstService;
	
	@Repository
	public interface MstServiceRepository extends JpaRepository<MstService, String>{
	
		List<MstService> findByServiceCode(String serviceCode);
		List<MstService> findByStatus(Status status);

	}