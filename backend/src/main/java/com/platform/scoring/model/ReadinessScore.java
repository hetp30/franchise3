package com.platform.scoring.model;

import com.platform.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "readiness_scores")
public class ReadinessScore extends BaseEntity {

    @Column(nullable = false, name = "user_id")
    private UUID userId;

    @Column(name = "shop_id")
    private UUID shopId;

    @Column(nullable = false)
    private Double totalScore; // 0.0 to 100.0

    @Column(columnDefinition = "TEXT")
    private String scoreBreakdown; // JSON string with factor-wise scores

    @Column
    private String recommendations; // Suggestions to improve score

    // Getters and Setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getShopId() {
        return shopId;
    }

    public void setShopId(UUID shopId) {
        this.shopId = shopId;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public String getScoreBreakdown() {
        return scoreBreakdown;
    }

    public void setScoreBreakdown(String scoreBreakdown) {
        this.scoreBreakdown = scoreBreakdown;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }
}
