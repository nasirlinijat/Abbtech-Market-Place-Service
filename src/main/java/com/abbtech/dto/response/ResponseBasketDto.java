package com.abbtech.dto.response;

import com.abbtech.model.enums.BasketStatus;

import java.time.LocalDateTime;

public record ResponseBasketDto(
        Long id,
        Long userId,
        Long itemId,
        String itemName,
        Integer count,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        BasketStatus status
) {
}
