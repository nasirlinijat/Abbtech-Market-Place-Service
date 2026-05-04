package com.abbtech.dto.request;

import com.abbtech.model.enums.NotificationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestNotificationDto(

        @NotNull(message = "userId is required")
        Long userId,

        @NotBlank(message = "message is required")
        String message,

        @NotNull(message = "status is required")
        NotificationStatus status
) {
}
