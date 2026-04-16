package com.abbtech.repository;

import com.abbtech.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> getAll();

    Optional<Category> getById(Long id);

    void add(Category category);

    void updateById(Long id, Category category);

    void deleteById(Long id);
}

