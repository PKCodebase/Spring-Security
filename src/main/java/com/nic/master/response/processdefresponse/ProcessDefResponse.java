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
    }
