package com.platform.shop.service;

import com.platform.shop.dto.ShopProfileRequest;
import com.platform.shop.model.Shop;
import com.platform.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Transactional
    public Shop saveShopProfile(UUID userId, ShopProfileRequest request) {
        // Check if shop profile already exists for this user
        Shop shop = shopRepository.findByUserId(userId)
                .orElse(new Shop());

        // Map request to entity
        shop.setUserId(userId);
        shop.setShopName(request.getShopName());
        shop.setCategory(request.getCategory());
        shop.setYearsInOperation(request.getYearsInOperation());
        shop.setNumberOfEmployees(request.getNumberOfEmployees());
        shop.setMonthlyCustomers(request.getMonthlyCustomers());
        shop.setShopAreaSqFt(request.getShopAreaSqFt());
        shop.setNumberOfOutlets(request.getNumberOfOutlets());
        shop.setServicesOffered(request.getServicesOffered());
        shop.setCertificationUrls(request.getCertificationUrls());
        shop.setBrandsServiced(request.getBrandsServiced());
        shop.setInterestedFranchiseDomains(request.getInterestedFranchiseDomains());
        shop.setWillingToRelocate(request.getWillingToRelocate() != null ? request.getWillingToRelocate() : false);
        shop.setAddress(request.getAddress());
        shop.setLocality(request.getLocality());
        shop.setLocalityType(request.getLocalityType());
        shop.setLatitude(request.getLatitude());
        shop.setLongitude(request.getLongitude());
        shop.setParkingAvailable(request.getParkingAvailable() != null ? request.getParkingAvailable() : false);
        shop.setPowerBackup(request.getPowerBackup() != null ? request.getPowerBackup() : false);
        shop.setColdStorage(request.getColdStorage() != null ? request.getColdStorage() : false);
        shop.setPhotoUrls(request.getPhotoUrls());
        shop.setLicenseUrl(request.getLicenseUrl());
        shop.setGstUrl(request.getGstUrl());
        shop.setAllowCustomerRatings(request.getAllowCustomerRatings() != null ? request.getAllowCustomerRatings() : true);

        return shopRepository.save(shop);
    }

    public Shop getShopProfile(UUID userId) {
        return shopRepository.findByUserId(userId)
                .orElse(null);
    }
}
