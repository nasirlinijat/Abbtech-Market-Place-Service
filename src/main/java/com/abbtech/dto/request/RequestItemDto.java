package com.abbtech.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Builder
public record RequestItemDto(
        Long id,

        @NotBlank(message = "item.name.not.empty")
        @Length(min = 2, max = 100, message = "Item name must be between 2 and 100 characters")
        String name,

        @NotNull(message = "item.price.not.null")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        BigDecimal price,

        @NotBlank(message = "Item image cannot be empty")
        String image,

        @Length(max = 250, message = "Description cannot exceed 250 characters")
        String description
) {
}
