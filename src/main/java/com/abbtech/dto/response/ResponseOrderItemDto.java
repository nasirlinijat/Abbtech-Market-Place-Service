package com.abbtech.dto.response;

import java.time.LocalDateTime;

public record ResponseOrderItemDto(
        Long id,
        Long userId,
        Long itemId,
        String itemName,
        Integer count,
        LocalDateTime timestamp
) {
}
