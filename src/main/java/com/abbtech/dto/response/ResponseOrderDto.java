package com.abbtech.dto.response;

import com.abbtech.model.enums.OrderStatus;
import com.abbtech.model.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseOrderDto(
        Long id,
        LocalDateTime timestamp,
        OrderStatus status,
        String paymentNotes,
        String paymentId,
        PaymentType paymentType,
        List<ResponseOrderItemDto> orderItems
) {
}
