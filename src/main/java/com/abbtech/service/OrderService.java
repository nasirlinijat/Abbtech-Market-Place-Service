package com.abbtech.service;

import com.abbtech.dto.request.RequestOrderDto;
import com.abbtech.dto.response.ResponseOrderDto;
import com.abbtech.model.enums.OrderStatus;
import org.springframework.data.domain.Page;

public interface OrderService {

    Page<ResponseOrderDto> getAll(int pageNumber, int pageSize);

    ResponseOrderDto getById(Long id);

    ResponseOrderDto add(RequestOrderDto request);

    ResponseOrderDto updateStatus(Long id, OrderStatus status);

    void deleteById(Long id);
}
