//package com.nic.master.entity.process;
//
//import com.nic.master.entity.process.MstActionType;
//import com.nic.master.entity.process.MstSectionType;
//import com.nic.master.entity.process.ProcessDef;
//import jakarta.persistence.*;
//import lombok.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "ref_template", schema = "process",
//        uniqueConstraints = {
//                @UniqueConstraint(name = "ref_template_ref_template_code_uk", columnNames = "ref_template_code"),
//                @UniqueConstraint(name = "ref_template_ref_template_id_uk", columnNames = "ref_template_id"),
//                @UniqueConstraint(name = "ref_template_uk", columnNames = {"process_def_guid", "section_type_guid", "action_type_guid"})
//        })
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class RefTemplate {
//
//    @Id
//    @Column(name = "ref_template_guid", length = 36, nullable = false)
//    private String refTemplateGuid;
//
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ref_template_id", nullable = false)
//    private Long refTemplateId;
//
//    // Foreign key to process_def
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "process_def_guid", referencedColumnName = "process_def_guid", nullable = false)
//    private ProcessDef processDef;
//
//    // Foreign key to mst_section_type
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "section_type_guid", referencedColumnName = "section_type_guid", nullable = false)
//    private MstSectionType sectionType;
//
//    // Foreign key to mst_action_type
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "action_type_guid", referencedColumnName = "action_type_guid", nullable = false)
//    private MstActionType actionType;
//
//    @Column(name = "have_prefill_data", nullable = false)
//    private Boolean havePrefillData = false;
//
//    @Column(name = "prefill_data_result_query")
//    private String prefillDataResultQuery;
//
//    @Column(name = "created_by", nullable = false)
//    private String createdBy;
//
//    @Column(name = "created_date", nullable = false)
//    private LocalDateTime createdDate;
//
//    @Column(name = "created_ip_addr", nullable = false)
//    private String createdIpAddr;
//
//    @Column(name = "created_mac_addr")
//    private String createdMacAddr;
//
//    @Column(name = "created_remarks")
//    private String createdRemarks;
//
//    @Column(name = "created_uri")
//    private String createdUri;
//
//    @Column(name = "modified_by")
//    private String modifiedBy;
//
//    @Column(name = "modified_date")
//    private LocalDateTime modifiedDate;
//
//    @Column(name = "modified_ip_addr")
//    private String modifiedIpAddr;
//
//    @Column(name = "modified_mac_addr")
//    private String modifiedMacAddr;
//
//    @Column(name = "modified_remarks")
//    private String modifiedRemarks;
//
//    @Column(name = "modified_uri")
//    private String modifiedUri;
//
//    @Column(name = "is_active", nullable = false)
//    private Boolean isActive = true;
//
//    @Column(name = "ref_template_name", nullable = false)
//    private String refTemplateName;
//
//    @Column(name = "ref_template_code", nullable = false)
//    private String refTemplateCode;
//
//    @Column(name = "is_query_raise_check",