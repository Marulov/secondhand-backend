package com.marulov.secondhand.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
