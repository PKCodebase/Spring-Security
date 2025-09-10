package com.nic.master.entity.process;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "process_def",
        schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "process_def_process_def_code_uk", columnNames = "process_def_code"),
                @UniqueConstraint(name = "process_def_process_def_id_uk", columnNames = "process_def_id")
        }
)
@Check(constraints = "char_length(process_def_guid) = 36")
@Check(constraints = "(modified_by IS NOT NULL AND modified_date IS NOT NULL AND modified_ip_addr IS NOT NULL) " +
        "OR (modified_by IS NULL AND modified_date IS NULL AND modified_ip_addr IS NULL)")
@Check(constraints = "modified_date IS NULL OR modified_date >= created_date")
@Data
public class ProcessDef {

    @Id
    @Column(name = "process_def_guid", length = 36, nullable = false, updatable = false)
    private String processDefGuid;

    @Column(name = "process_def_id", nullable = false, unique = true, insertable = false, updatable = false)
    private Long processDefId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "process_type_guid",
            nullable = false,
            foreignKey = @ForeignKey(name = "process_def_process_type_guid_fk")
    )
    private MstProcessType processType;

    @Column(name = "process_def_code", nullable = false, length = 100, unique = true)
    private String processDefCode;

    @Column(name = "process_def_name", nullable = false)
    private String processDefName;

    @OneToOne(mappedBy = "processDef", cascade = CascadeType.ALL, optional = true)
//    @LazyToOne(LazyToOneOption.NO_PROXY)
    private ProcessDefConfig processDefConfig;


    @Column(name = "is_primary_org_applicable")
    private Boolean isPrimaryOrgApplicable;

    @Column(name = "is_wrapper_org_applicable")
    private Boolean isWrapperOrgApplicable;

    @Column(name = "is_org_unit_applicable")
    private Boolean isOrgUnitApplicable;

    @Column(name = "is_service_applicable")
    private Boolean isServiceApplicable;

    @Column(name = "is_cadre_applicable")
    private Boolean isCadreApplicable;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

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

    @Column(name = "can_show_parent", nullable = false)
    private Boolean canShowParent = true;

    @Column(name = "can_show_child", nullable = false)
    private Boolean canShowChild = false;

    @Column(name = "can_draft", nullable = false)
    private Boolean canDraft = false;
}
