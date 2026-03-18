package com.abbtech.repository;

import com.abbtech.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductInMemoryRepositoryImpl implements ProductRepository {


    private static final List<Product> products = List.of(

            new Product(
                    "High quality wireless mouse",
                    "mouse.jpg",
                    new BigDecimal("29.99"),
                    "Wireless Mouse",
                    new BigDecimal("18.00")
            ),

            new Product(
                    "Mechanical gaming keyboard",
                    "keyboard.jpg",
                    new BigDecimal("89.99"),
                    "Gaming Keyboard",
                    new BigDecimal("60.00")
            ),

            new Product(
                    "27-inch 4K Monitor",
                    "monitor.jpg",
                    new BigDecimal("399.99"),
                    "4K Monitor",
                    new BigDecimal("320.00")
            ),
            new Product(
                    "28-inch 4K Monitor",
                    "monitor.jpg",
                    new BigDecimal("399.99"),
                    "4K Monitor",
                    new BigDecimal("500.00")
            )

    );

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public void saveProduct(Product product) {

    }
}
