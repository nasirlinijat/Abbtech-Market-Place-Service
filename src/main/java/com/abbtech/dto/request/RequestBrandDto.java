package com.abbtech.dto.request;

import jakarta.validation.constraints.*;
import jdk.jfr.Registered;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record RequestBrandDto(
        @NotBlank(message = "Brand name can not be empty or null") @Length(min = 2, max = 50) String name,
        String description,
        String image,
        @AssertTrue
        Boolean isActive,
        @AssertFalse
        Boolean isDeleted, @Size(min = 1, max = 3) List<String> items,
        @NotNull @Positive @Digits(integer = 1, fraction = 2) BigDecimal price,
        @Pattern(regexp = "^\\+994(50|70)\\d{7}$") String mobileNumber) {
}

