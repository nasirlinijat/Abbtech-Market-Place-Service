package com.abbtech.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record RequestBrandItemDto(
        String name,
        String description,
        String image,
        Boolean active,
        Boolean deleted,
        List<RequestItemDto> items
) {
}

