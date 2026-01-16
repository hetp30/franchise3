package com.platform.franchise.model;

import com.platform.common.model.BaseEntity;
import com.platform.shop.model.BusinessCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    @Column(nullable = false)
    private String brandName;

    @Column
    private String logoUrl;

    @Column(nullable = false)
    private Integer yearsInOperation;

    @Column(nullable = false)
    private Integer existingOutlets;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusinessCategory category;

    @Column(nullable = false, name = "user_id")
    private java.util.UUID userId;

    // Getters and Setters
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getYearsInOperation() {
        return yearsInOperation;
    }

    public void setYearsInOperation(Integer yearsInOperation) {
        this.yearsInOperation = yearsInOperation;
    }

    public Integer getExistingOutlets() {
        return existingOutlets;
    }

    public void setExistingOutlets(Integer existingOutlets) {
        this.existingOutlets = existingOutlets;
    }

    public BusinessCategory getCategory() {
        return category;
    }

    public void setCategory(BusinessCategory category) {
        this.category = category;
    }

    public java.util.UUID getUserId() {
        return userId;
    }

    public void setUserId(java.util.UUID userId) {
        this.userId = userId;
    }
}
