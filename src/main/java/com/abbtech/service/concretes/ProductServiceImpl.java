package com.abbtech.service.concretes;

import com.abbtech.dto.ResponseProductDto;
import com.abbtech.repository.ProductRepository;
import com.abbtech.service.abstracts.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ResponseProductDto> getAllProduct() {
        return productRepository.getAllProducts()
                .stream()
                .map(product -> new ResponseProductDto(
                        product.getProductName(),
                        product.getProductDescription(),
                        product.getProductImage(),
                        product.getProductPrice()))
                .toList();
    }


}
