package com.nic.master.entity.process;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "process_service",
        schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "process_service_process_service_id_uk", columnNames = "process_service_id")
        }
)
public class ProcessService {


    @Column(name = "process_service_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Long processServiceId;

    @Id
    @Column(name = "process_service_guid", nullable = false, length = 36)
    private String processServiceGuid;

    // Foreign key to process_def
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_def_guid", nullable = false,
            foreignKey = @ForeignKey(name = "process_service_process_def_guid_fk"))
    private ProcessDef processDef;

    @Column(name = "service_url", nullable = false)
    private String serviceUrl;

    @Column(name = "created_by", nullable = false)
    private String createdBy;


    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @NotBlank
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

    // ------------------
    // Getters and Setters
    // ------------------
    public Long getProcessServiceId() { return processServiceId; }
    public void setProcessServiceId(Long processServiceId) { this.processServiceId = processServiceId; }

    public String getProcessServiceGuid() { return processServiceGuid; }
    public void setProcessServiceGuid(String processServiceGuid) { this.processServiceGuid = processServiceGuid; }

    public ProcessDef getProcessDef() { return processDef; }
    public void setProcessDef(ProcessDef processDef) { this.processDef = processDef; }

    public String getServiceUrl() { return serviceUrl; }
    public void setServiceUrl(String serviceUrl) { this.serviceUrl = serviceUrl; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public String getCreatedIpAddr() { return createdIpAddr; }
    public void setCreatedIpAddr(String createdIpAddr) { this.createdIpAddr = createdIpAddr; }

    public String getCreatedMacAddr() { return createdMacAddr; }
    public void setCreatedMacAddr(String createdMacAddr) { this.createdMacAddr = createdMacAddr; }

    public String getCreatedRemarks() { return createdRemarks; }
    public void setCreatedRemarks(String createdRemarks) { this.createdRemarks = createdRemarks; }

    public String getCreatedUri() { return createdUri; }
    public void setCreatedUri(String createdUri) { this.createdUri = createdUri; }

    public String getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; }

    public LocalDateTime getModifiedDate() { return modifiedDate; }
    public void setModifiedDate(LocalDateTime modifiedDate) { this.modifiedDate = modifiedDate; }

    public String getModifiedIpAddr() { return modifiedIpAddr; }
    public void setModifiedIpAddr(String modifiedIpAddr) { this.modifiedIpAddr = modifiedIpAddr; }

    public String getModifiedMacAddr() { return modifiedMacAddr; }
    public void setModifiedMacAddr(String modifiedMacAddr) { this.modifiedMacAddr = modifiedMacAddr; }

    public String getModifiedRemarks() { return modifiedRemarks; }
    public void setModifiedRemarks(String modifiedRemarks) { this.modifiedRemarks = modifiedRemarks; }

    public String getModifiedUri() { return modifiedUri; }
    public void setModifiedUri(String modifiedUri) { this.modifiedUri = modifiedUri; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
