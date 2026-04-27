package com.abbtech.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ResponseItemDto(
        Long id,
        String name,
        BigDecimal price,
        String image,
        String description,
        Long brandId,
        Long categoryId,
        Boolean isActive,
        Boolean isDeleted,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
