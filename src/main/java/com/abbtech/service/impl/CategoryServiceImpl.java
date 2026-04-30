package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Category;
import com.abbtech.model.enums.SortDirectionEnum;
import com.abbtech.repository.CategoryRepository;
import com.abbtech.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ResponseCategoryDto> getAll(int pageNumber, int pageSize, SortDirectionEnum sortDirection, String sortField) {
        return null;
    }

    @Override
    public ResponseCategoryDto getById(Long id) {
        return null;
    }

    @Override
    public ResponseCategoryDto add(RequestCategoryDto request) {
        return null;

    }

    @Override
    public ResponseCategoryDto updateById(Long id, RequestCategoryDto request) {
        return null;
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}

