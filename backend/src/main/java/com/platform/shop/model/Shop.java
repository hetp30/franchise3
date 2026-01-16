package com.platform.shop.model;

import com.platform.common.model.BaseEntity;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shops")
public class Shop extends BaseEntity {

    @Column(nullable = false)
    private String shopName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusinessCategory category;

    @Column(nullable = false)
    private Integer yearsInOperation;

    @Column(nullable = false)
    private Integer numberOfEmployees;

    @Column(nullable = false)
    private Integer monthlyCustomers;

    @Column(nullable = false)
    private Double shopAreaSqFt;

    @Column(nullable = false)
    private Integer numberOfOutlets;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "shop_services", joinColumns = @JoinColumn(name = "shop_id"))
    private Set<ServiceType> servicesOffered = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "shop_certifications", joinColumns = @JoinColumn(name = "shop_id"))
    @Column(name = "certification_url")
    private Set<String> certificationUrls = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "shop_brands_serviced", joinColumns = @JoinColumn(name = "shop_id"))
    @Column(name = "brand_name")
    private Set<String> brandsServiced = new HashSet<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "shop_franchise_interests", joinColumns = @JoinColumn(name = "shop_id"))
    private Set<BusinessCategory> interestedFranchiseDomains = new HashSet<>();

    @Column(nullable = false)
    private Boolean willingToRelocate = false;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String locality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LocalityType localityType;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Boolean parkingAvailable = false;

    @Column(nullable = false)
    private Boolean powerBackup = false;

    @Column(nullable = false)
    private Boolean coldStorage = false;

    @ElementCollection
    @CollectionTable(name = "shop_photos", joinColumns = @JoinColumn(name = "shop_id"))
    @Column(name = "photo_url")
    private Set<String> photoUrls = new HashSet<>();

    @Column(name = "license_url")
    private String licenseUrl;

    @Column(name = "gst_url")
    private String gstUrl;

    @Column(nullable = false)
    private Boolean allowCustomerRatings = true;

    @Column(nullable = false, name = "user_id")
    private java.util.UUID userId;

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

    public java.util.UUID getUserId() {
        return userId;
    }

    public void setUserId(java.util.UUID userId) {
        this.userId = userId;
    }
}
