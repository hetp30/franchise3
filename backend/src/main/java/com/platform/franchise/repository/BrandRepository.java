package com.platform.franchise.repository;

import com.platform.franchise.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {
    Optional<Brand> findByUserId(UUID userId);
    List<Brand> findAllByUserId(UUID userId);
}
