package com.abbtech.service;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.model.enums.SortDirectionEnum;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {

    ResponseItemDto add(RequestItemDto request);

    void saveAll(List<RequestItemDto> request);

    Page<ResponseItemDto> getAll(int pageNumber, int pageSize, SortDirectionEnum sortDirection, String sortField);

    ResponseItemDto getById(Long id);

    void deleteById(Long id);

    ResponseItemDto updateByName(String name, RequestItemDto request);

    Page<ResponseItemDto> getPriceRange(double min, double max, int pageNumber, int pageSize);

    ResponseItemDto partialUpdateByName(String name, String itemDescription);
}
