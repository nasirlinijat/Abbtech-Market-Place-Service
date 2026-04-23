package com.abbtech.service;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.request.RequestBrandItemDto;
import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.dto.response.ResponseItemDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BrandService {
    List<ResponseBrandDto> getAll();

    ResponseBrandDto getById(Long id);

    ResponseBrandDto add(RequestBrandDto request);

    ResponseBrandDto updateById(Long id, RequestBrandDto request);

    void deleteById(Long id);

    List<ResponseItemDto> getItemsByBrand(Long brandId);
}

