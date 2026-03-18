package com.abbtech.controller;

import com.abbtech.bean.annotation.Employee;
import com.abbtech.dto.RespProductDto;
import com.abbtech.service.ProductService;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductController extends HttpServlet {
    private final ProductService productService;

    @Autowired
    @Qualifier("ali")
    //field injection
    private Employee smth;

    public Employee smthels;

    // setter injection
    public void setEmployee(Employee smth) {
        this.smth = smth;
    }

    //constructor
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public List<RespProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

}
