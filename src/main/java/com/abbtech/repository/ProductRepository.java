package com.abbtech.repository;

import com.abbtech.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
}
