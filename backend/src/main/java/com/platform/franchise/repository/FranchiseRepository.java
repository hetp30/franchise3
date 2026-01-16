package com.platform.franchise.repository;

import com.platform.franchise.model.Franchise;
import com.platform.shop.model.BusinessCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, UUID> {
    List<Franchise> findByBrandId(UUID brandId);
    List<Franchise> findByCategoryAndActiveTrue(BusinessCategory category);
    List<Franchise> findByActiveTrue();
}
