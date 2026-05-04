package com.abbtech.dto.response;

import com.abbtech.model.enums.NotificationStatus;

import java.time.LocalDateTime;

public record ResponseNotificationDto(
        Long id,
        Long userId,
        LocalDateTime timestamp,
        String message,
        NotificationStatus status
) {
}
