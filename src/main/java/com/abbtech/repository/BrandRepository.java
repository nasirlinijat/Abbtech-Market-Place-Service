package com.abbtech.repository;

import com.abbtech.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {
    Brand create(Brand brand);

    Brand update(Long id, Brand brand);

    void delete(Long id);

    List<Brand> getAll();

    Optional<Brand> getById(Long id);

    List<Brand> bulkCreate(List<Brand> brands);
}
