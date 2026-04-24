package com.abbtech.dto.request;

import lombok.Builder;

@Builder
public record RequestCategoryDto(
        String name,
        String description,
        String image,
        Integer parentId,
        Integer categoryOrder,
        Boolean isActive,
        Boolean isDeleted
) {
}
