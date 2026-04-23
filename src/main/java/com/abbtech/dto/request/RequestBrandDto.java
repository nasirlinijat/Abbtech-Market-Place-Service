package com.abbtech.dto.request;

import lombok.Builder;

@Builder
public record RequestBrandDto(String name,
                              String description,
                              String image,
                              Boolean isActive,
                              Boolean isDeleted) {

}

