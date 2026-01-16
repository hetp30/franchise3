package com.platform.scoring.repository;

import com.platform.scoring.model.ReadinessScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReadinessScoreRepository extends JpaRepository<ReadinessScore, UUID> {
    Optional<ReadinessScore> findByUserId(UUID userId);
    Optional<ReadinessScore> findByShopId(UUID shopId);
}
