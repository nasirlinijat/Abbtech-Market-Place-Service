package com.abbtech.service.abstracts;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {
    ResponseItemDto add(RequestItemDto request);

    List<ResponseItemDto> getAll();

    ResponseItemDto getByName(String name);

    void deleteByName(String name);

    ResponseItemDto updateByName(String name, RequestItemDto requestItemDto);

    List<ResponseItemDto> getPriceRange(double min, double max);

    ResponseItemDto partialUpdateByName(String name, String itemDescription);
}
