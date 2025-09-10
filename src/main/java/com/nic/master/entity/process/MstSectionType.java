package com.nic.master.entity.process;


import com.nic.master.enums.SectionCategory;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mst_section_type", schema = "process",
        uniqueConstraints = {
                @UniqueConstraint(name = "mst_section_type_section_type_code_uk", columnNames = "section_type_code"),
                @UniqueConstraint(name = "mst_section_type_section_type_id_uk", columnNames = "section_type_id")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstSectionType {

    @Id
    @Column(name = "section_type_guid", nullable = false, length = 36, unique = true)
    private String sectionTypeGuid;

    @Column(name = "section_type_id", nullable = false, unique = true,updatable = false,insertable = false)
    private Long sectionTypeId;

    @Column(name = "section_type_code", nullable = false, length = 100)
    private String sectionTypeCode;

    @Column(name = "section_type_name", nullable = false)
    private String sectionTypeName;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "section_category", nullable = false)
    private SectionCategory sectionCategory = SectionCategory.FORM;

    public String getSectionTypeGuid() {
        return sectionTypeGuid;
    }

    public void setSectionTypeGuid(String sectionTypeGuid) {
        this.sectionTypeGuid = sectionTypeGuid;
    }

    public Long getSectionTypeId() {
        return sectionTypeId;
    }

    public void setSectionTypeId(Long sectionTypeId) {
        this.sectionTypeId = sectionTypeId;
    }

    public String getSectionTypeCode() {
        return sectionTypeCode;
    }

    public void setSectionTypeCode(String sectionTypeCode) {
        this.sectionTypeCode = sectionTypeCode;
    }

    public String getSectionTypeName() {
        return sectionTypeName;
    }

    public void setSectionTypeName(String sectionTypeName) {
        this.sectionTypeName = sectionTypeName;
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

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public SectionCategory getSectionCategory() {
        return sectionCategory;
    }

    public void setSectionCategory(SectionCategory sectionCategory) {
        this.sectionCategory = sectionCategory;
    }
}
