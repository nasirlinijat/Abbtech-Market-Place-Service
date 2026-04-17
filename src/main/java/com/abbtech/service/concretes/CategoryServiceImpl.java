package com.abbtech.service.concretes;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.model.Category;
import com.abbtech.repository.CategoryRepository;
import com.abbtech.service.abstracts.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseCategoryDto create(RequestCategoryDto request) {
        Category created = categoryRepository.create(toCategory(request));
        return toResponse(created);
    }

    @Override
    public ResponseCategoryDto update(Long id, RequestCategoryDto request) {
        Category updated = categoryRepository.update(id, toCategory(request));
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public List<ResponseCategoryDto> getAll() {
        return categoryRepository.getAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ResponseCategoryDto getById(Long id) {
        return categoryRepository.getById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @Override
    public List<ResponseCategoryDto> bulkCreate(List<RequestCategoryDto> requests) {
        List<Category> categories = requests.stream().map(this::toCategory).toList();
        return categoryRepository.bulkCreate(categories).stream()
                .map(this::toResponse)
                .toList();
    }

    private Category toCategory(RequestCategoryDto request) {
        return new Category(
                null,
                request.getName(),
                request.getDescription(),
                request.getImage(),
                request.getParentId(),
                request.getCategoryOrder(),
                request.getIsActive(),
                request.getIsDeleted(),
                null,
                null
        );
    }

    private ResponseCategoryDto toResponse(Category category) {
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
