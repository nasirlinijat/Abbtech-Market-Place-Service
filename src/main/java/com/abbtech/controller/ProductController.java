package com.abbtech.controller;

import com.abbtech.repository.ProductInMemoryRepositoryImpl;
import com.abbtech.service.abstracts.ProductService;
import com.abbtech.service.concretes.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    private ProductService productService;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void init() {
        productService = new ProductServiceImpl(new ProductInMemoryRepositoryImpl());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println(OBJECT_MAPPER.writeValueAsString(productService.getAllProduct()));
    }
}
