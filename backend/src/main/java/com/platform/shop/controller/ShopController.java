package com.platform.shop.controller;

import com.platform.shop.dto.ShopProfileRequest;
import com.platform.shop.model.Shop;
import com.platform.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/profile")
    public ResponseEntity<Shop> saveShopProfile(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody ShopProfileRequest request) {
        Shop shop = shopService.saveShopProfile(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(shop);
    }

    @GetMapping("/profile")
    public ResponseEntity<Shop> getShopProfile(@RequestHeader("X-User-Id") UUID userId) {
        Shop shop = shopService.getShopProfile(userId);
        if (shop == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shop);
    }
}
