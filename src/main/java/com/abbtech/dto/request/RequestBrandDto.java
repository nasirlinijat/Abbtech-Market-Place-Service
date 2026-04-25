package com.abbtech.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record RequestBrandDto(
        @NotBlank(message = "Brand name can not be empty or null") @Length(min = 2, max = 50) String name,
        String description,
        String image,
        Boolean isActive,
        Boolean isDeleted) {

}

