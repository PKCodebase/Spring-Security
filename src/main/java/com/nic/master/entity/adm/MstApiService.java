package com.nic.master.entity.adm;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mst_api_service", schema = "adm",
        uniqueConstraints = {
                @UniqueConstraint(name = "mst_api_service_api_service_code_active_uk", columnNames = {"api_service_code", "is_active"}),
                @UniqueConstraint(name = "mst_api_service_api_service_code_uk", columnNames = "api_service_code"),
                @UniqueConstraint(name = "mst_api_service_api_service_id_uk", columnNames = "api_service_id")
        })
public class MstApiService {

    @Id
    @Column(name = "api_service_guid", nullable = false, length = 36)
    private String apiServiceGuid;

    @Column(name = "api_service_id", nullable = false)
    private Long apiServiceId;

    @Column(name = "api_service_code", nullable = false)
    private String apiServiceCode;

    @Column(name = "api_service_name", nullable = false)
    private String apiServiceName;

    @Column(name = "api_service_url", nullable = false)
    private String apiServiceUrl;

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