package com.abbtech.service;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.ResponseCategoryDto;

import java.util.List;

public interface CategoryService {
    List<ResponseCategoryDto> getAll();

    ResponseCategoryDto getById(Long id);

    ResponseCategoryDto add(RequestCategoryDto request);

    ResponseCategoryDto updateById(Long id, RequestCategoryDto request);

    void deleteById(Long id);
}

