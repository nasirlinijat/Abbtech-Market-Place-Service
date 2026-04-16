package com.abbtech.service;

import com.abbtech.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAll();

    Brand getById(Long id);

    Brand add(Brand brand);

    Brand updateById(Long id, Brand brand);

    void deleteById(Long id);
}

