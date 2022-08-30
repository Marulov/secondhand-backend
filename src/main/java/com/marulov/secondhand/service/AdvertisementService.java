package com.marulov.secondhand.service;

import com.marulov.secondhand.converter.advertisement.AdvertisementDtoConverter;
import com.marulov.secondhand.dto.advertisement.AdvertisementDto;
import com.marulov.secondhand.dto.advertisement.CreateAdvertisementRequest;
import com.marulov.secondhand.exception.UserNotFoundException;
import com.marulov.secondhand.model.Advertisement;
import com.marulov.secondhand.model.User;
import com.marulov.secondhand.model.UserDetails;
import com.marulov.secondhand.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementService.class);

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementDtoConverter advertisementDtoConverter;
    private final UserService userService;

    public List<AdvertisementDto> getAllAdvertisement() {
        return advertisementDtoConverter.convert(advertisementRepository.findAll());
    }

    public AdvertisementDto createAdvertisement(CreateAdvertisementRequest request) {
        User user = userService.findUserById(request.getUserId());

        Advertisement advertisement = Advertisement.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .hashtag(request.getHashtag())
                .user(user)
                .build();
        return advertisementDtoConverter.convert(advertisementRepository.save(advertisement));
    }

    public List<AdvertisementDto> getAdvertisementByUserId(Long userId) {
        return advertisementDtoConverter.convert(advertisementRepository.findByUserId(userId).orElseThrow(() ->
                new UserNotFoundException("Advertisement with user id " + userId + " not found")));
    }
}
