package com.abbtech.service;

import com.abbtech.dto.request.RequestBasketDto;
import com.abbtech.dto.response.ResponseBasketDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BasketService {

    Page<ResponseBasketDto> getAll(int pageNumber, int pageSize);

    ResponseBasketDto getById(Long id);

    List<ResponseBasketDto> getByUserId(Long userId);

    ResponseBasketDto add(RequestBasketDto request);

    ResponseBasketDto updateById(Long id, RequestBasketDto request);

    void deleteById(Long id);
}
