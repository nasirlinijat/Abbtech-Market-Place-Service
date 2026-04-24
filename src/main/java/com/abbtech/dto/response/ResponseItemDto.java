package com.abbtech.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ResponseItemDto(
        Long id,
        String name,
        BigDecimal price,
        String image,
        String description
) {
}
