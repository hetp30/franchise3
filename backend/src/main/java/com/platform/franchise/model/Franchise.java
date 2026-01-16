package com.platform.franchise.model;

import com.platform.common.model.BaseEntity;
import com.platform.shop.model.BusinessCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "franchises")
public class Franchise extends BaseEntity {

    @Column(nullable = false)
    private String franchiseName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusinessCategory category;

    @Column(nullable = false)
    private BigDecimal minInvestment;

    @Column(nullable = false)
    private BigDecimal maxInvestment;

    @Column
    private Integer agreementDurationMonths;

    @Column(nullable = false)
    private Boolean experienceRequired = false;

    @Column
    private Integer minExperienceYears;

    @Column
    private Double minReadinessScore;

    @Column(nullable = false)
    private Boolean suitableForBeginners = true;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false, name = "brand_id")
    private UUID brandId;

    // Getters and Setters
    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }

    public BusinessCategory getCategory() {
        return category;
    }

    public void setCategory(BusinessCategory category) {
        this.category = category;
    }

    public BigDecimal getMinInvestment() {
        return minInvestment;
    }

    public void setMinInvestment(BigDecimal minInvestment) {
        this.minInvestment = minInvestment;
    }

    public BigDecimal getMaxInvestment() {
        return maxInvestment;
    }

    public void setMaxInvestment(BigDecimal maxInvestment) {
        this.maxInvestment = maxInvestment;
    }

    public Integer getAgreementDurationMonths() {
        return agreementDurationMonths;
    }

    public void setAgreementDurationMonths(Integer agreementDurationMonths) {
        this.agreementDurationMonths = agreementDurationMonths;
    }

    public Boolean getExperienceRequired() {
        return experienceRequired;
    }

    public void setExperienceRequired(Boolean experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    public Integer getMinExperienceYears() {
        return minExperienceYears;
    }

    public void setMinExperienceYears(Integer minExperienceYears) {
        this.minExperienceYears = minExperienceYears;
    }

    public Double getMinReadinessScore() {
        return minReadinessScore;
    }

    public void setMinReadinessScore(Double minReadinessScore) {
        this.minReadinessScore = minReadinessScore;
    }

    public Boolean getSuitableForBeginners() {
        return suitableForBeginners;
    }

    public void setSuitableForBeginners(Boolean suitableForBeginners) {
        this.suitableForBeginners = suitableForBeginners;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UUID getBrandId() {
        return brandId;
    }

    public void setBrandId(UUID brandId) {
        this.brandId = brandId;
    }
}
