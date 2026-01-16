package com.platform.scoring.model;

import com.platform.common.model.BaseEntity;
import com.platform.shop.model.BusinessCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "score_rules")
public class ScoreRule extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private BusinessCategory category;

    @Column(nullable = false)
    private String factor; // e.g., "years_in_operation", "monthly_customers", "has_parking"

    @Column(nullable = false)
    private Double weight; // Weight of this factor (0.0 to 1.0)

    @Column
    private String condition; // JSON or condition string for rule evaluation

    @Column(nullable = false)
    private Boolean active = true;

    // Getters and Setters
    public BusinessCategory getCategory() {
        return category;
    }

    public void setCategory(BusinessCategory category) {
        this.category = category;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
