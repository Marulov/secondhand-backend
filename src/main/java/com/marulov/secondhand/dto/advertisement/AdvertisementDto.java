package com.marulov.secondhand.dto.advertisement;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class AdvertisementDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private String hashtag;
}