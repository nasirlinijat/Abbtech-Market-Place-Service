package com.abbtech.service;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.ResponseBrandDto;

import java.util.List;

public interface BrandService {
    List<ResponseBrandDto> getAll();

    ResponseBrandDto getById(Long id);

    ResponseBrandDto add(RequestBrandDto request);

    ResponseBrandDto updateById(Long id, RequestBrandDto request);

    void deleteById(Long id);
}

