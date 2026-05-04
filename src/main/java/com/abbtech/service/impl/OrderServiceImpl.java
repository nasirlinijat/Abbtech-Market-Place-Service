package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestOrderDto;
import com.abbtech.dto.request.RequestOrderItemDto;
import com.abbtech.dto.response.ResponseOrderDto;
import com.abbtech.dto.response.ResponseOrderItemDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Order;
import com.abbtech.model.OrderItem;
import com.abbtech.model.enums.OrderStatus;
import com.abbtech.repository.ItemRepository;
import com.abbtech.repository.OrderRepository;
import com.abbtech.repository.secirity.UserRepository;
import com.abbtech.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseOrderDto> getAll(int pageNumber, int pageSize) {
        return orderRepository.findAll(PageRequest.of(pageNumber, pageSize)).map(this::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseOrderDto getById(Long id) {
        return toResponseDto(findOrderOrThrow(id));
    }

    @Override
    @Transactional
    public ResponseOrderDto add(RequestOrderDto request) {
        Order order = new Order();
        order.setStatus(request.status());
        order.setPaymentNotes(request.paymentNotes());
        order.setPaymentId(request.paymentId());
        order.setPaymentType(request.paymentType());

        List<OrderItem> orderItems = request.items().stream()
                .map(dto -> toOrderItem(dto, order))
                .toList();
        order.setOrderItems(orderItems);

        return toResponseDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public ResponseOrderDto updateStatus(Long id, OrderStatus status) {
        Order order = findOrderOrThrow(id);
        order.setStatus(status);
        return toResponseDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    private Order findOrderOrThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.ORDER_NOT_FOUND));
    }

    private OrderItem toOrderItem(RequestOrderItemDto dto, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setUser(userRepository.findById(dto.userId())
                .orElseThrow(() -> new ProductException(ProductErrorEnum.USER_NOT_FOUND)));
        orderItem.setItem(itemRepository.findById(dto.itemId())
                .orElseThrow(() -> new ProductException(ProductErrorEnum.ITEM_NOT_FOUND)));
        orderItem.setCount(dto.count());
        orderItem.setOrder(order);
        return orderItem;
    }

    private ResponseOrderItemDto toOrderItemDto(OrderItem oi) {
        return new ResponseOrderItemDto(
                oi.getId(),
                oi.getUser().getId(),
                oi.getItem().getId(),
                oi.getItem().getName(),
                oi.getCount(),
                oi.getTimestamp()
        );
    }

    private ResponseOrderDto toResponseDto(Order order) {
        List<ResponseOrderItemDto> items = order.getOrderItems() == null ? List.of() :
                order.getOrderItems().stream().map(this::toOrderItemDto).toList();
        return new ResponseOrderDto(
                order.getId(),
                order.getTimestamp(),
                order.getStatus(),
                order.getPaymentNotes(),
                order.getPaymentId(),
                order.getPaymentType(),
                items
        );
    }
}
