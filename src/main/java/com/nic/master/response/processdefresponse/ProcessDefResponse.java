    package com.nic.master.response.processdefresponse;

    import lombok.Data;


    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDateTime;

    // Lombok annotations for getters/setters, constructors
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ProcessDefResponse {

        private String processDefGuid;
        private String processDefCode;
        private String processDefName;

        // Related ProcessType info
//        private String processTypeGuid;
//        private String processTypeCode;
        private String processTypeNameEn;

        private Boolean isPrimaryOrgApplicable;
        private Boolean isWrapperOrgApplicable;
        private Boolean isOrgUnitApplicable;
        private Boolean isServiceApplicable;
        private Boolean isCadreApplicable;

        private String createdBy;
        private String createdIpAddr;
        private String createdMacAddr;
        private String createdRemarks;
        private String createdUri;
        private LocalDateTime createdDate;

        private String modifiedBy;
        private String modifiedIpAddr;
        private String modifiedMacAddr;
        private String modifiedRemarks;
        private String modifiedUri;
        private LocalDateTime modifiedDate;


        private Boolean isActive;


		public String getProcessDefGuid() {
			return processDefGuid;
		}


		public void setProcessDefGuid(String processDefGuid) {
			this.processDefGuid = processDefGuid;
		}


		public String getProcessDefCode() {
			return processDefCode;
		}


		public void setProcessDefCode(String processDefCode) {
			this.processDefCode = processDefCode;
		}


		public String getProcessDefName() {
			return processDefName;
		}


		public void setProcessDefName(String processDefName) {
			this.processDefName = processDefName;
		}


		public String getProcessTypeNameEn() {
			return processTypeNameEn;
		}


		public void setProcessTypeNameEn(String processTypeNameEn) {
			this.processTypeNameEn = processTypeNameEn;
		}


		public Boolean getIsPrimaryOrgApplicable() {
			return isPrimaryOrgApplicable;
		}


		public void setIsPrimaryOrgApplicable(Boolean isPrimaryOrgApplicable) {
			this.isPrimaryOrgApplicable = isPrimaryOrgApplicable;
		}


		public Boolean getIsWrapperOrgApplicable() {
			return isWrapperOrgApplicable;
		}


		public void setIsWrapperOrgApplicable(Boolean isWrapperOrgApplicable) {
			this.isWrapperOrgApplicable = isWrapperOrgApplicable;
		}


		public Boolean getIsOrgUnitApplicable() {
			return isOrgUnitApplicable;
		}


		public void setIsOrgUnitApplicable(Boolean isOrgUnitApplicable) {
			this.isOrgUnitApplicable = isOrgUnitApplicable;
		}


		public Boolean getIsServiceApplicable() {
			return isServiceApplicable;
		}


		public void setIsServiceApplicable(Boolean isServiceApplicable) {
			this.isServiceApplicable = isServiceApplicable;
		}


		public Boolean getIsCadreApplicable() {
			return isCadreApplicable;
		}


		public void setIsCadreApplicable(Boolean isCadreApplicable) {
			this.isCadreApplicable = isCadreApplicable;
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


		public LocalDateTime getCreatedDate() {
			return createdDate;
		}


		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
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


		public LocalDateTime getModifiedDate() {
			return modifiedDate;
		}


		public void setModifiedDate(LocalDateTime modifiedDate) {
			this.modifiedDate = modifiedDate;
		}


		public Boolean getIsActive() {
			return isActive;
		}


		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}


	
    }
