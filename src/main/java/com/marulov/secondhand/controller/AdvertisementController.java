package com.marulov.secondhand.controller;

import com.marulov.secondhand.dto.advertisement.AdvertisementDto;
import com.marulov.secondhand.dto.advertisement.CreateAdvertisementRequest;
import com.marulov.secondhand.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/advertisement")
@RequiredArgsConstructor
public class AdvertisementController {
    private final AdvertisementService advertisementService;

    @GetMapping
    public ResponseEntity<List<AdvertisementDto>> getAllAdvertisements() {
        return ResponseEntity.ok(advertisementService.getAllAdvertisement());
    }

    @PostMapping
    public ResponseEntity<AdvertisementDto> createAdvertisement(@RequestBody CreateAdvertisementRequest request) {
        advertisementService.createAdvertisement(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<AdvertisementDto>> getAdvertisementsByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(advertisementService.getAdvertisementByUserId(userId));
    }
}
