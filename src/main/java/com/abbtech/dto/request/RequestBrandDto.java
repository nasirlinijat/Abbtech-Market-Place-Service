package com.abbtech.dto.request;

import com.abbtech.exception.base.BaseErrorEnum;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record RequestBrandDto(
        @NotBlank(message = "brand.name.not.empty") @Length(min = 2, max = 50) String name,
        @NotBlank(message = "brand.description.not.empty")
        String description,
        @NotBlank(message = "Brand image can not be empty or null")
        String image,
        @AssertTrue
        Boolean isActive,
        @AssertFalse
        Boolean isDeleted) {
}

