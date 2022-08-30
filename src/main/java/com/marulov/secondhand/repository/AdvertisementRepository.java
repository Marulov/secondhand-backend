package com.marulov.secondhand.repository;

import com.marulov.secondhand.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    Optional<List<Advertisement>> findByUserId(Long userId);
}