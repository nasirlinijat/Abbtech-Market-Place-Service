package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Category;
import com.abbtech.model.enums.SortDirectionEnum;
import com.abbtech.repository.CategoryRepository;
import com.abbtech.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseCategoryDto> getAll(int pageNumber, int pageSize, SortDirectionEnum sortDirection, String sortField) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection.toString()), sortField);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        log.debug("Fetching categories page={}, size={}", pageNumber, pageSize);
        return categoryRepository.findAll(pageable).map(this::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseCategoryDto getById(Long id) {
        return toResponseDto(findCategoryOrThrow(id));
    }

    @Override
    @Transactional
    public ResponseCategoryDto add(RequestCategoryDto request) {
        Category category = toCategory(request);
        return toResponseDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public ResponseCategoryDto updateById(Long id, RequestCategoryDto request) {
        Category existing = findCategoryOrThrow(id);
        existing.setName(request.name());
        existing.setDescription(request.description());
        existing.setImage(request.image());
        existing.setParentId(request.parentId());
        existing.setCategoryOrder(request.categoryOrder());
        existing.setIsActive(request.isActive());
        existing.setIsDeleted(request.isDeleted());
        return toResponseDto(categoryRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    private Category findCategoryOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.CATEGORY_NOT_FOUND));
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
        return new ResponseCategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getImage(),
                category.getParentId(),
                category.getCategoryOrder(),
                category.getIsActive(),
                category.getIsDeleted(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }
}
