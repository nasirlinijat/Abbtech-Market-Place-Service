package com.abbtech.service;

import com.abbtech.dto.ReqProductDto;
import com.abbtech.dto.RespProductDto;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Product;
import com.abbtech.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public RespProductDto getProductByName(String name) {
        return productRepository.getAllProducts()
                .stream()
                .filter(product -> product.getProductName().equals(name))
                .map(product -> new RespProductDto(product.getProductDescription(),
                        product.getProductImage(),
                        product.getProductPrice(),
                        product.getProductName())).findFirst().orElseThrow(() -> new ProductException());
    }

    @Override
    public List<RespProductDto> getAllProducts() {
        return productRepository.getAllProducts()
                .stream()
                .filter(product -> product.getWholesalePrice().compareTo(BigDecimal.valueOf(60)) > 0)
                .map(product -> new RespProductDto(product.getProductDescription(),
                        product.getProductImage(),
                        product.getProductPrice(),
                        product.getProductName()))
                .toList();
    }

    @Override
    public void saveProduct(ReqProductDto product) {
        productRepository.saveProduct(new Product(product.getProductDescription(),
                product.getProductImage(),
                product.getProductPrice(),
                product.getProductName(),
                product.getProductPrice())
        );
    }
}
