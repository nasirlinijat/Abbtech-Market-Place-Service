package com.abbtech.controller;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.model.enums.SortDirectionEnum;
import com.abbtech.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Page<ResponseCategoryDto> getAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "name") String sortField) {
        return categoryService.getAll(pageNumber, pageSize, SortDirectionEnum.valueOf(sortDirection), sortField);
    }

    @GetMapping("/{id}")
    public ResponseCategoryDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCategoryDto add(@RequestBody RequestCategoryDto request) {
        return categoryService.add(request);
    }

    @PutMapping("/{id}")
    public ResponseCategoryDto updateById(@PathVariable Long id, @RequestBody RequestCategoryDto request) {
        return categoryService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
