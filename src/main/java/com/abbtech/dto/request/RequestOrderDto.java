package com.abbtech.dto.request;

import com.abbtech.model.enums.OrderStatus;
import com.abbtech.model.enums.PaymentType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestOrderDto(

        @NotNull(message = "status is required")
        OrderStatus status,

        String paymentNotes,

        String paymentId,

        @NotNull(message = "paymentType is required")
        PaymentType paymentType,

        @NotNull
        @NotEmpty(message = "order must contain at least one item")
        @Valid
        List<RequestOrderItemDto> items
) {
}
