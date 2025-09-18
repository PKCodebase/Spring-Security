package com.nic.master.entity.process;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ref_query", schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "ref_query_ref_query_code_uk", columnNames = {"ref_query_code"}),
                @UniqueConstraint(name = "ref_query_ref_query_id_uk", columnNames = {"ref_query_id"}),
                @UniqueConstraint(name = "ref_query_uk", columnNames = {"process_def_guid", "section_type_guid", "process_def_desc_guid"})
        })
public class RefQuery {

    @Id
    @Column(name = "ref_query_guid", nullable = false, length = 36)
    private String refQueryGuid;

    @Column(name = "ref_query_id", nullable = false, unique = true)
    private Long refQueryId;

    // ✅ Foreign Key -> process_def
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "process_def_guid", referencedColumnName = "process_def_guid",
            foreignKey = @ForeignKey(name = "ref_query_process_def_guid_fk"))
    private ProcessDef processDef;

    // ✅ Foreign Key -> mst_section_type
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_type_guid", referencedColumnName = "section_type_guid",
            foreignKey = @ForeignKey(name = "ref_query_section_type_guid_fk"))
    private MstSectionType sectionType;

    // ✅ Foreign Key -> process_def_desc (nullable)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_def_desc_guid", referencedColumnName = "process_def_desc_guid",
            foreignKey = @ForeignKey(name = "ref_query_process_def_desc_guid_fk"))
    private ProcessDefDesc processDefDesc;

    @Column(name = "ref_query_name", nullable = false)
    private String refQueryName;

    @Column(name = "ref_query_code", nullable = false, unique = true)
    private String refQueryCode;

    @Column(name = "have_prefill_data", nullable = false)
    private Boolean havePrefillData = false;

    @Column(name = "prefill_data_result_query")
    private String prefillDataResultQuery;

    @Column(name = "created_by", nullable = false)
    private String createdBy = "SYSTEM";

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_ip_addr", nullable = false)
    private String createdIpAddr;

    @Column(name = "created_mac_addr")
    private String createdMacAddr;

    @Column(name = "created_remarks")
    private String createdRemarks;

    @Column(name = "created_uri")
    private String createdUri;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "modified_ip_addr")
    private String modifiedIpAddr;

    @Column(name = "modified_mac_addr")
    private String modifiedMacAddr;

    @Column(name = "modified_remarks")
    private String modifiedRemarks;

    @Column(name = "modified_uri")
    private String modifiedUri;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "raise_limit")
    private Integer raiseLimit;

    @Column(name = "is_allowed_web_response", nullable = false)
    private Boolean isAllowedWebResponse = true;

	public String getRefQueryGuid() {
		return refQueryGuid;
	}

	public void setRefQueryGuid(String refQueryGuid) {
		this.refQueryGuid = refQueryGuid;
	}

	public Long getRefQueryId() {
		return refQueryId;
	}

	public void setRefQueryId(Long refQueryId) {
		this.refQueryId = refQueryId;
	}

	public ProcessDef getProcessDef() {
		return processDef;
	}

	public void setProcessDef(ProcessDef processDef) {
		this.processDef = processDef;
	}

	public MstSectionType getSectionType() {
		return sectionType;
	}

	public void setSectionType(MstSectionType sectionType) {
		this.sectionType = sectionType;
	}

	public ProcessDefDesc getProcessDefDesc() {
		return processDefDesc;
	}

	public void setProcessDefDesc(ProcessDefDesc processDefDesc) {
		this.processDefDesc = processDefDesc;
	}

	public String getRefQueryName() {
		return refQueryName;
	}

	public void setRefQueryName(String refQueryName) {
		this.refQueryName = refQueryName;
	}

	public String getRefQueryCode() {
		return refQueryCode;
	}

	public void setRefQueryCode(String refQueryCode) {
		this.refQueryCode = refQueryCode;
	}

	public Boolean getHavePrefillData() {
		return havePrefillData;
	}

	public void setHavePrefillData(Boolean havePrefillData) {
		this.havePrefillData = havePrefillData;
	}

	public String getPrefillDataResultQuery() {
		return prefillDataResultQuery;
	}

	public void setPrefillDataResultQuery(String prefillDataResultQuery) {
		this.prefillDataResultQuery = prefillDataResultQuery;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedIpAddr() {
		return createdIpAddr;
	}

	public void setCreatedIpAddr(String createdIpAddr) {
		this.createdIpAddr = createdIpAddr;
	}

	public String getCreatedMacAddr() {
		return createdMacAddr;
	}

	public void setCreatedMacAddr(String createdMacAddr) {
		this.createdMacAddr = createdMacAddr;
	}

	public String getCreatedRemarks() {
		return createdRemarks;
	}

	public void setCreatedRemarks(String createdRemarks) {
		this.createdRemarks = createdRemarks;
	}

	public String getCreatedUri() {
		return createdUri;
	}

	public void setCreatedUri(String createdUri) {
		this.createdUri = createdUri;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedIpAddr() {
		return modifiedIpAddr;
	}

	public void setModifiedIpAddr(String modifiedIpAddr) {
		this.modifiedIpAddr = modifiedIpAddr;
	}

	public String getModifiedMacAddr() {
		return modifiedMacAddr;
	}

	public void setModifiedMacAddr(String modifiedMacAddr) {
		this.modifiedMacAddr = modifiedMacAddr;
	}

	public String getModifiedRemarks() {
		return modifiedRemarks;
	}

	public void setModifiedRemarks(String modifiedRemarks) {
		this.modifiedRemarks = modifiedRemarks;
	}

	public String getModifiedUri() {
		return modifiedUri;
	}

	public void setModifiedUri(String modifiedUri) {
		this.modifiedUri = modifiedUri;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getRaiseLimit() {
		return raiseLimit;
	}

	public void setRaiseLimit(Integer raiseLimit) {
		this.raiseLimit = raiseLimit;
	}

	public Boolean getIsAllowedWebResponse() {
		return isAllowedWebResponse;
	}

	public void setIsAllowedWebResponse(Boolean isAllowedWebResponse) {
		this.isAllowedWebResponse = isAllowedWebResponse;
	}


}
