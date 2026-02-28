package com.abbtech.service;

import com.abbtech.dto.ResponseProductDto;
import com.abbtech.model.Product;
import com.abbtech.repository.ProductRepository;
import com.abbtech.service.abstracts.ProductService;
import com.abbtech.service.concretes.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getAllProduct_whenRepositoryReturnsProducts_shouldMapToDto() {
        Product product1 = new Product(
                "Latest iPhone with A16 chip",
                "iphone.png",
                BigDecimal.valueOf(1200),
                "iPhone 14",
                BigDecimal.valueOf(900)
        );

        Product product2 = new Product(
                "Powerful laptop for professionals",
                "macbook.png",
                BigDecimal.valueOf(2500),
                "MacBook Pro",
                BigDecimal.valueOf(2000)
        );

        when(productRepository.getAllProducts())
                .thenReturn(List.of(product1, product2));

        List<ResponseProductDto> result = productService.getAllProduct();

        assertEquals(2, result.size());

        assertAll("First product mapping",
                () -> assertEquals("Latest iPhone with A16 chip", result.get(0).getProductDescription()),
                () -> assertEquals("iphone.png", result.get(0).getProductImage()),
                () -> assertEquals("iPhone 14", result.get(0).getProductName()),
                () -> assertEquals(BigDecimal.valueOf(1200), result.get(0).getProductPrice()));

        verify(productRepository, times(1)).getAllProducts();

    }

}
