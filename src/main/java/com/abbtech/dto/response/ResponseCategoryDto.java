package com.abbtech.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResponseCategoryDto(
        Long id,
        String name,
        String description,
        String image,
        Integer parentId,
        Integer categoryOrder,
        Boolean isActive,
        Boolean isDeleted,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
