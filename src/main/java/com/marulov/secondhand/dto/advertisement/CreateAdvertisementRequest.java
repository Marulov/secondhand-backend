package com.marulov.secondhand.dto.advertisement;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateAdvertisementRequest {
    private String title;
    private String description;
    private BigDecimal price;
    private String hashtag;
    private Long userId;
}