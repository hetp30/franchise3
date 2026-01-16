package com.platform.shop.dto;

import com.platform.shop.model.BusinessCategory;
import com.platform.shop.model.LocalityType;
import com.platform.shop.model.ServiceType;

import java.util.Set;

public class ShopProfileRequest {
    // Business Identity
    private String shopName;
    private BusinessCategory category;
    private Integer yearsInOperation;
    private Integer numberOfEmployees;
    private Integer monthlyCustomers;
    private Double shopAreaSqFt;
    private Integer numberOfOutlets;

    // Services & Capability
    private Set<ServiceType> servicesOffered;
    private Set<String> certificationUrls;
    private Set<String> brandsServiced;

    // Franchise Interest
    private Set<BusinessCategory> interestedFranchiseDomains;
    private Boolean willingToRelocate;

    // Location & Infrastructure
    private String address;
    private String locality;
    private LocalityType localityType;
    private Double latitude;
    private Double longitude;
    private Boolean parkingAvailable;
    private Boolean powerBackup;
    private Boolean coldStorage;

    // Verification & Trust
    private Set<String> photoUrls;
    private String licenseUrl;
    private String gstUrl;
    private Boolean allowCustomerRatings;

    // Getters and Setters
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BusinessCategory getCategory() {
        return category;
    }

    public void setCategory(BusinessCategory category) {
        this.category = category;
    }

    public Integer getYearsInOperation() {
        return yearsInOperation;
    }

    public void setYearsInOperation(Integer yearsInOperation) {
        this.yearsInOperation = yearsInOperation;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public Integer getMonthlyCustomers() {
        return monthlyCustomers;
    }

    public void setMonthlyCustomers(Integer monthlyCustomers) {
        this.monthlyCustomers = monthlyCustomers;
    }

    public Double getShopAreaSqFt() {
        return shopAreaSqFt;
    }

    public void setShopAreaSqFt(Double shopAreaSqFt) {
        this.shopAreaSqFt = shopAreaSqFt;
    }

    public Integer getNumberOfOutlets() {
        return numberOfOutlets;
    }

    public void setNumberOfOutlets(Integer numberOfOutlets) {
        this.numberOfOutlets = numberOfOutlets;
    }

    public Set<ServiceType> getServicesOffered() {
        return servicesOffered;
    }

    public void setServicesOffered(Set<ServiceType> servicesOffered) {
        this.servicesOffered = servicesOffered;
    }

    public Set<String> getCertificationUrls() {
        return certificationUrls;
    }

    public void setCertificationUrls(Set<String> certificationUrls) {
        this.certificationUrls = certificationUrls;
    }

    public Set<String> getBrandsServiced() {
        return brandsServiced;
    }

    public void setBrandsServiced(Set<String> brandsServiced) {
        this.brandsServiced = brandsServiced;
    }

    public Set<BusinessCategory> getInterestedFranchiseDomains() {
        return interestedFranchiseDomains;
    }

    public void setInterestedFranchiseDomains(Set<BusinessCategory> interestedFranchiseDomains) {
        this.interestedFranchiseDomains = interestedFranchiseDomains;
    }

    public Boolean getWillingToRelocate() {
        return willingToRelocate;
    }

    public void setWillingToRelocate(Boolean willingToRelocate) {
        this.willingToRelocate = willingToRelocate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public LocalityType getLocalityType() {
        return localityType;
    }

    public void setLocalityType(LocalityType localityType) {
        this.localityType = localityType;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getParkingAvailable() {
        return parkingAvailable;
    }

    public void setParkingAvailable(Boolean parkingAvailable) {
        this.parkingAvailable = parkingAvailable;
    }

    public Boolean getPowerBackup() {
        return powerBackup;
    }

    public void setPowerBackup(Boolean powerBackup) {
        this.powerBackup = powerBackup;
    }

    public Boolean getColdStorage() {
        return coldStorage;
    }

    public void setColdStorage(Boolean coldStorage) {
        this.coldStorage = coldStorage;
    }

    public Set<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(Set<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getGstUrl() {
        return gstUrl;
    }

    public void setGstUrl(String gstUrl) {
        this.gstUrl = gstUrl;
    }

    public Boolean getAllowCustomerRatings() {
        return allowCustomerRatings;
    }

    public void setAllowCustomerRatings(Boolean allowCustomerRatings) {
        this.allowCustomerRatings = allowCustomerRatings;
    }
}
