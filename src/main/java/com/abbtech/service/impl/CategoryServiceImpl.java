package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Category;
import com.abbtech.repository.CategoryRepository;
import com.abbtech.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseCategoryDto getById(Long id) {
        return toResponseDto(findCategoryByIdOrThrow(id));
    }

    @Override
    @Transactional
    public ResponseCategoryDto add(RequestCategoryDto request) {
        Category category = toCategory(request);
        if (category.getCategoryOrder() == null) {
            category.setCategoryOrder(1);
        }
        if (category.getIsActive() == null) {
            category.setIsActive(true);
        }
        if (category.getIsDeleted() == null) {
            category.setIsDeleted(false);
        }
        return toResponseDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public ResponseCategoryDto updateById(Long id, RequestCategoryDto request) {
        Category existingCategory = findCategoryByIdOrThrow(id);
        existingCategory.setName(request.name());
        existingCategory.setDescription(request.description());
        existingCategory.setImage(request.image());
        existingCategory.setParentId(request.parentId());
        existingCategory.setCategoryOrder(request.categoryOrder());
        existingCategory.setIsActive(request.isActive());
        existingCategory.setIsDeleted(request.isDeleted());
        return toResponseDto(categoryRepository.save(existingCategory));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    private Category findCategoryByIdOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.ITEM_NOT_FOUND));
    }

    private Category toCategory(RequestCategoryDto request) {
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        category.setImage(request.image());
        category.setParentId(request.parentId());
        category.setCategoryOrder(request.categoryOrder());
        category.setIsActive(request.isActive());
        category.setIsDeleted(request.isDeleted());
        return category;
    }

    private ResponseCategoryDto toResponseDto(Category category) {
        return ResponseCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .image(category.getImage())
                .parentId(category.getParentId())
                .categoryOrder(category.getCategoryOrder())
                .isActive(category.getIsActive())
                .isDeleted(category.getIsDeleted())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}

