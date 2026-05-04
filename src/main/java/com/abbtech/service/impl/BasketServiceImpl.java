package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestBasketDto;
import com.abbtech.dto.response.ResponseBasketDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Basket;
import com.abbtech.model.enums.BasketStatus;
import com.abbtech.repository.BasketRepository;
import com.abbtech.repository.ItemRepository;
import com.abbtech.repository.secirity.UserRepository;
import com.abbtech.service.BasketService;
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
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseBasketDto> getAll(int pageNumber, int pageSize) {
        return basketRepository.findAll(PageRequest.of(pageNumber, pageSize)).map(this::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseBasketDto getById(Long id) {
        return toResponseDto(findBasketOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseBasketDto> getByUserId(Long userId) {
        return basketRepository.findByUser_Id(userId).stream().map(this::toResponseDto).toList();
    }

    @Override
    @Transactional
    public ResponseBasketDto add(RequestBasketDto request) {
        Basket basket = new Basket();
        basket.setUser(userRepository.findById(request.userId())
                .orElseThrow(() -> new ProductException(ProductErrorEnum.USER_NOT_FOUND)));
        basket.setItem(itemRepository.findById(request.itemId())
                .orElseThrow(() -> new ProductException(ProductErrorEnum.ITEM_NOT_FOUND)));
        basket.setCount(request.count());
        basket.setStatus(request.status() != null ? request.status() : BasketStatus.ACTIVE);
        return toResponseDto(basketRepository.save(basket));
    }

    @Override
    @Transactional
    public ResponseBasketDto updateById(Long id, RequestBasketDto request) {
        Basket basket = findBasketOrThrow(id);
        basket.setCount(request.count());
        if (request.status() != null) {
            basket.setStatus(request.status());
        }
        if (request.itemId() != null) {
            basket.setItem(itemRepository.findById(request.itemId())
                    .orElseThrow(() -> new ProductException(ProductErrorEnum.ITEM_NOT_FOUND)));
        }
        return toResponseDto(basketRepository.save(basket));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        basketRepository.deleteById(id);
    }

    private Basket findBasketOrThrow(Long id) {
        return basketRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.BASKET_NOT_FOUND));
    }

    private ResponseBasketDto toResponseDto(Basket basket) {
        return new ResponseBasketDto(
                basket.getId(),
                basket.getUser().getId(),
                basket.getItem().getId(),
                basket.getItem().getName(),
                basket.getCount(),
                basket.getCreatedDate(),
                basket.getUpdatedDate(),
                basket.getStatus()
        );
    }
}
