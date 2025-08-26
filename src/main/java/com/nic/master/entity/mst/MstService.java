package com.nic.master.entity.mst;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "mst_service", schema = "logging") 
public class MstService {

	
	@Id
	@Column(name = "service_guid", unique = true, nullable = false, length = 36, updatable = false)
	private String serviceGuid;


	@Column(name = "service_id", nullable = false,unique = true)
	private String serviceId;
	
	@Column(name = "service_code", nullable = false)
	private String serviceCode;
	
	@Column(name = "service_name", nullable = false)
	private String serviceName;

	@Column(name = "created_by", nullable = false)
	private String createdBy;


	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;
	
	@Column(name = "created_ip_addr")
	private String createdIpAddr;


	@Column(name = "created_remarks")
	private String createdRemarks;

	
	@Column(name = "modified_by")	
	private String modifiedBy;
	

	@Column(name = "modified_date")
	private LocalDateTime modifiedDate;
	
	@Column(name = "modified_ip_addr")
    private String modifiedIpAddr;
	
	@Column(name = "modified_remarks")
	private String modifiedRemarks;


	@Column(name = "is_active", nullable = false)
	private Boolean status=true;
	
	@Column(name = "process_code")
	private String processCode;


	public String getServiceGuid() {
		return serviceGuid;
	}

	public void setServiceGuid(String serviceGuid) {
		this.serviceGuid = serviceGuid;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getCreatedIpAddr() {
		return createdIpAddr;
	}

	public void setCreatedIpAddr(String createdIpAddr) {
		this.createdIpAddr = createdIpAddr;
	}

	public String getCreatedRemarks() {
		return createdRemarks;
	}

	public void setCreatedRemarks(String createdRemarks) {
		this.createdRemarks = createdRemarks;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedIpAddr() {
		return modifiedIpAddr;
	}

	public void setModifiedIpAddr(String modifiedIpAddr) {
		this.modifiedIpAddr = modifiedIpAddr;
	}

	public String getModifiedRemarks() {
		return modifiedRemarks;
	}

	public void setModifiedRemarks(String modifiedRemarks) {
		this.modifiedRemarks = modifiedRemarks;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}


	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
