package com.abbtech.dto.response;

import java.time.LocalDateTime;

public record ResponseBrandDto(Long id,
                               String name,
                               String description,
                               String image,
                               Boolean isActive,
                               Boolean isDeleted,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
}

