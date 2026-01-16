package com.platform.scoring.service;

import com.platform.scoring.model.ReadinessScore;
import com.platform.scoring.model.ScoreRule;
import com.platform.scoring.repository.ReadinessScoreRepository;
import com.platform.scoring.repository.ScoreRuleRepository;
import com.platform.shop.model.BusinessCategory;
import com.platform.shop.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ScoringService {

    @Autowired
    private ScoreRuleRepository scoreRuleRepository;

    @Autowired
    private ReadinessScoreRepository readinessScoreRepository;

    /**
     * Calculate readiness score for a shop based on rule-based scoring
     * This is designed to be easily replaceable with AI later
     */
    @Transactional
    public ReadinessScore calculateReadinessScore(UUID userId, Shop shop) {
        BusinessCategory category = shop.getCategory();
        List<ScoreRule> rules = scoreRuleRepository.findByCategoryAndActiveTrue(category);

        // If no rules for category, use general rules
        if (rules.isEmpty()) {
            rules = scoreRuleRepository.findByActiveTrue();
        }

        Map<String, Double> breakdown = new HashMap<>();
        double totalScore = 0.0;
        double totalWeight = 0.0;

        for (ScoreRule rule : rules) {
            double factorScore = calculateFactorScore(rule, shop);
            breakdown.put(rule.getFactor(), factorScore);
            totalScore += factorScore * rule.getWeight();
            totalWeight += rule.getWeight();
        }

        // Normalize score to 0-100
        if (totalWeight > 0) {
            totalScore = (totalScore / totalWeight) * 100;
        }

        // Generate recommendations
        String recommendations = generateRecommendations(breakdown, shop);

        // Save or update score
        ReadinessScore readinessScore = readinessScoreRepository.findByShopId(shop.getId())
                .orElse(new ReadinessScore());
        readinessScore.setUserId(userId);
        readinessScore.setShopId(shop.getId());
        readinessScore.setTotalScore(Math.min(100.0, Math.max(0.0, totalScore)));
        readinessScore.setScoreBreakdown(convertBreakdownToJson(breakdown));
        readinessScore.setRecommendations(recommendations);

        return readinessScoreRepository.save(readinessScore);
    }

    private double calculateFactorScore(ScoreRule rule, Shop shop) {
        String factor = rule.getFactor();
        double score = 0.0;

        switch (factor) {
            case "years_in_operation":
                score = Math.min(100.0, shop.getYearsInOperation() * 10.0);
                break;
            case "monthly_customers":
                if (shop.getMonthlyCustomers() >= 1000) score = 100.0;
                else if (shop.getMonthlyCustomers() >= 500) score = 75.0;
                else if (shop.getMonthlyCustomers() >= 200) score = 50.0;
                else score = 25.0;
                break;
            case "number_of_employees":
                if (shop.getNumberOfEmployees() >= 10) score = 100.0;
                else if (shop.getNumberOfEmployees() >= 5) score = 75.0;
                else if (shop.getNumberOfEmployees() >= 2) score = 50.0;
                else score = 25.0;
                break;
            case "shop_area":
                if (shop.getShopAreaSqFt() >= 1000) score = 100.0;
                else if (shop.getShopAreaSqFt() >= 500) score = 75.0;
                else if (shop.getShopAreaSqFt() >= 200) score = 50.0;
                else score = 25.0;
                break;
            case "has_parking":
                score = shop.getParkingAvailable() ? 100.0 : 0.0;
                break;
            case "has_power_backup":
                score = shop.getPowerBackup() ? 100.0 : 0.0;
                break;
            case "has_cold_storage":
                score = shop.getColdStorage() ? 100.0 : 0.0;
                break;
            case "has_certifications":
                score = shop.getCertificationUrls() != null && !shop.getCertificationUrls().isEmpty() ? 100.0 : 0.0;
                break;
            case "has_photos":
                score = shop.getPhotoUrls() != null && shop.getPhotoUrls().size() >= 3 ? 100.0 : 50.0;
                break;
            case "number_of_outlets":
                if (shop.getNumberOfOutlets() > 1) score = 100.0;
                else score = 50.0;
                break;
            default:
                score = 50.0; // Default neutral score
        }

        return score;
    }

    private String generateRecommendations(Map<String, Double> breakdown, Shop shop) {
        StringBuilder recommendations = new StringBuilder();
        
        if (breakdown.getOrDefault("has_parking", 0.0) == 0.0 && shop.getCategory() == BusinessCategory.AUTOMOBILE) {
            recommendations.append("Consider adding parking facilities for better franchise eligibility. ");
        }
        if (breakdown.getOrDefault("has_cold_storage", 0.0) == 0.0 && shop.getCategory() == BusinessCategory.DAIRY) {
            recommendations.append("Cold storage is recommended for dairy franchises. ");
        }
        if (breakdown.getOrDefault("has_certifications", 0.0) == 0.0) {
            recommendations.append("Adding certifications can improve your readiness score. ");
        }
        if (shop.getPhotoUrls() == null || shop.getPhotoUrls().size() < 3) {
            recommendations.append("Upload at least 3 shop photos to increase trust and score. ");
        }
        if (breakdown.getOrDefault("monthly_customers", 0.0) < 50.0) {
            recommendations.append("Focus on increasing customer footfall to improve readiness. ");
        }

        return recommendations.length() > 0 ? recommendations.toString() : "Your profile looks good! Keep maintaining quality standards.";
    }

    private String convertBreakdownToJson(Map<String, Double> breakdown) {
        // Simple JSON conversion (in production, use Jackson ObjectMapper)
        StringBuilder json = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<String, Double> entry : breakdown.entrySet()) {
            if (!first) json.append(",");
            json.append("\"").append(entry.getKey()).append("\":").append(entry.getValue());
            first = false;
        }
        json.append("}");
        return json.toString();
    }

    public ReadinessScore getReadinessScore(UUID userId) {
        return readinessScoreRepository.findByUserId(userId).orElse(null);
    }
}
