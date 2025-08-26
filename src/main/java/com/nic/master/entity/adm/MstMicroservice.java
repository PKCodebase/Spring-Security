package com.nic.master.entity.adm;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mst_microservice", schema = "adm",
        uniqueConstraints = {
                @UniqueConstraint(name = "mst_microservice_microservice_code_uk", columnNames = "microservice_code"),
                @UniqueConstraint(name = "mst_microservice_microservice_id_uk", columnNames = "microservice_id")
        })
public class MstMicroservice {

    @Id
    @Column(name = "microservice_guid", nullable = false, length = 36)
    private String microserviceGuid;

    @Column(name = "microservice_id", nullable = false)
    private Long microserviceId;

    @Column(name = "microservice_code", nullable = false)
    private String microserviceCode;

    @Column(name = "microservice_name", nullable = false)
    private String microserviceName;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "created_ip_addr", nullable = false)
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
    private Boolean isActive = true;

    // getters and setters
}