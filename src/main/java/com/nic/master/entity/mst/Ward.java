package com.nic.master.entity.mst;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "ward", schema = "mst")
public class Ward {

    @Id
    @Column(name = "ward_guid", length = 36, nullable = false)
    private String wardGuid;


    @Column(name = "ward_id", nullable = false, unique = true,insertable = false,updatable = false)
    private Long wardId;

    @Column(name = "ward_code", length = 100, nullable = false, unique = true)
    private String wardCode;

    @Column(name = "ward_name_en", nullable = false)
    private String wardNameEn;

    @Column(name = "ward_name_hi")
    private String wardNameHi;

    @Column(name = "ward_name_rl")
    private String wardNameRl;

    @Column(name = "ward_description")
    private String wardDescription;

    @Column(name = "org_unit_code", length = 36)
    private String orgUnitCode;

    @Column(name = "created_by")
//        @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate ;


    @Column(name = "created_ip_addr")
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
    private LocalDate modifiedDate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_guid", nullable = false)
    private Zone zone;

    @OneToMany(mappedBy = "ward", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Colony> colonies;

    public String getWardGuid() {
        return wardGuid;
    }

    public void setWardGuid(String wardGuid) {
        this.wardGuid = wardGuid;
    }

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getWardNameEn() {
        return wardNameEn;
    }

    public void setWardNameEn(String wardNameEn) {
        this.wardNameEn = wardNameEn;
    }

    public String getWardNameHi() {
        return wardNameHi;
    }

    public void setWardNameHi(String wardNameHi) {
        this.wardNameHi = wardNameHi;
    }

    public String getWardNameRl() {
        return wardNameRl;
    }

    public void setWardNameRl(String wardNameRl) {
        this.wardNameRl = wardNameRl;
    }

    public String getWardDescription() {
        return wardDescription;
    }

    public void setWardDescription(String wardDescription) {
        this.wardDescription = wardDescription;
    }

    public String getOrgUnitCode() {
        return orgUnitCode;
    }

    public void setOrgUnitCode(String orgUnitCode) {
        this.orgUnitCode = orgUnitCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
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

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
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

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public List<Colony> getColonies() {
        return colonies;
    }

    public void setColonies(List<Colony> colonies) {
        this.colonies = colonies;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
