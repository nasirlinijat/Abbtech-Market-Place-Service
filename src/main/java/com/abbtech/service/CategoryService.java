package com.abbtech.service;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.model.enums.SortDirectionEnum;
import org.springframework.data.domain.Page;

public interface CategoryService {

    Page<ResponseCategoryDto> getAll(int pageNumber, int pageSize, SortDirectionEnum sortDirection, String sortField);

    ResponseCategoryDto getById(Long id);

    ResponseCategoryDto add(RequestCategoryDto request);

    ResponseCategoryDto updateById(Long id, RequestCategoryDto request);

    void deleteById(Long id);
}
