package com.abbtech.service;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.request.RequestBrandItemDto;
import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.model.enums.SortDirectionEnum;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BrandService {
    Page<ResponseBrandDto> getAll(int pageNumber, int pageSize, SortDirectionEnum sortDirection, String sortField);

    ResponseBrandDto getById(Long id);

    ResponseBrandDto add(RequestBrandDto request);

    ResponseBrandDto updateById(Long id, RequestBrandDto request);

    void deleteById(Long id);

    List<ResponseItemDto> getItemsByBrand(Long brandId);

    void saveBrandAndItems(RequestBrandItemDto request);

    void updateBrandItems(Long id, @RequestBody List<RequestItemDto> request);

}

