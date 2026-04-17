package com.abbtech.service.abstracts;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.ResponseCategoryDto;

import java.util.List;

public interface CategoryService {
    ResponseCategoryDto create(RequestCategoryDto request);

    ResponseCategoryDto update(Long id, RequestCategoryDto request);

    void delete(Long id);

    List<ResponseCategoryDto> getAll();

    ResponseCategoryDto getById(Long id);

    List<ResponseCategoryDto> bulkCreate(List<RequestCategoryDto> requests);
}
