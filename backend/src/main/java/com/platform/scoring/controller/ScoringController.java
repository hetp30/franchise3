package com.platform.scoring.controller;

import com.platform.scoring.model.ReadinessScore;
import com.platform.scoring.service.ScoringService;
import com.platform.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/scoring")
public class ScoringController {

    @Autowired
    private ScoringService scoringService;

    @Autowired
    private ShopService shopService;

    @PostMapping("/calculate")
    public ResponseEntity<ReadinessScore> calculateScore(@RequestHeader("X-User-Id") UUID userId) {
        com.platform.shop.model.Shop shop = shopService.getShopProfile(userId);
        if (shop == null) {
            return ResponseEntity.badRequest().build();
        }
        ReadinessScore score = scoringService.calculateReadinessScore(userId, shop);
        return ResponseEntity.ok(score);
    }

    @GetMapping("/score")
    public ResponseEntity<ReadinessScore> getScore(@RequestHeader("X-User-Id") UUID userId) {
        ReadinessScore score = scoringService.getReadinessScore(userId);
        if (score == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(score);
    }
}
