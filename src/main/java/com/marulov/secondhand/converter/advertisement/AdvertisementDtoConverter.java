package com.marulov.secondhand.converter.advertisement;

import com.marulov.secondhand.dto.advertisement.AdvertisementDto;
import com.marulov.secondhand.dto.advertisement.CreateAdvertisementRequest;
import com.marulov.secondhand.model.Advertisement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdvertisementDtoConverter {
    public AdvertisementDto convert(Advertisement from) {
        return AdvertisementDto.builder()
                .id(from.getId())
                .title(from.getTitle())
                .description(from.getDescription())
                .price(from.getPrice())
                .creationDate(from.getCreationDate())
                .updateDate(from.getUpdateDate())
                .hashtag(from.getHashtag())
                .build();
    }

    public List<AdvertisementDto> convert(List<Advertisement> advertisementList) {
        return advertisementList.stream().map(this::convert).collect(Collectors.toList());
    }
}