package com.abbtech.dto.request;


import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RequestItemDto(Long id,
                             String name,
                             BigDecimal price,
                             String image,
                             String description) {
}
