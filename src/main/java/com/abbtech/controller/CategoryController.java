package com.abbtech.controller;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.service.abstracts.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<ResponseCategoryDto> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseCategoryDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public ResponseCategoryDto create(@RequestBody RequestCategoryDto request) {
        return categoryService.create(request);
    }

    @PostMapping("/bulk")
    public List<ResponseCategoryDto> bulkCreate(@RequestBody List<RequestCategoryDto> requests) {
        return categoryService.bulkCreate(requests);
    }

    @PutMapping("/{id}")
    public ResponseCategoryDto update(@PathVariable Long id, @RequestBody RequestCategoryDto request) {
        return categoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
