package com.nic.master.entity.adm;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mst_api", schema = "adm",
        uniqueConstraints = {
                @UniqueConstraint(name = "mst_api_api_code_uk", columnNames = "api_code"),
                @UniqueConstraint(name = "mst_api_api_id_uk", columnNames = "api_id"),
                @UniqueConstraint(name = "mst_api_api_uk", columnNames = {"microservice_guid", "url_type_guid"})
        })
public class MstApi {

    @Id
    @Column(name = "api_guid", nullable = false, length = 36)
    private String apiGuid;

    @Column(name = "api_id", nullable = false)
    private Long apiId;

    @Column(name = "api_code", nullable = false)
    private String apiCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "microservice_guid", referencedColumnName = "microservice_guid",
            foreignKey = @ForeignKey(name = "mst_api_microservice_guid_fk"))
    private MstMicroservice microservice;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "url_type_guid", referencedColumnName = "url_type_guid",
//            foreignKey = @ForeignKey(name = "mst_api_url_type_guid_fk"))
//    private MstUrlType urlType;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "sub_url_json", columnDefinition = "json")
    private String subUrlJson;

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
