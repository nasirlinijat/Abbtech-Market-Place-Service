package com.abbtech.repository;

import com.abbtech.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category create(Category category);

    Category update(Long id, Category category);

    void delete(Long id);

    List<Category> getAll();

    Optional<Category> getById(Long id);

    List<Category> bulkCreate(List<Category> categories);
}
