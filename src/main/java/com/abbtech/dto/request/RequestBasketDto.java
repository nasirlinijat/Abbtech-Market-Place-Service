package com.abbtech.dto.request;

import com.abbtech.model.enums.BasketStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RequestBasketDto(

        @NotNull(message = "userId is required")
        Long userId,

        @NotNull(message = "itemId is required")
        Long itemId,

        @NotNull(message = "count is required")
        @Min(value = 1, message = "count must be at least 1")
        Integer count,

        BasketStatus status
) {
}
