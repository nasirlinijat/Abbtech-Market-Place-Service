package com.abbtech.controller;

import com.abbtech.dto.ReqProductDto;
import com.abbtech.dto.RespProductDto;
import com.abbtech.service.ProductService;
import jakarta.servlet.http.HttpServlet;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController extends HttpServlet {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void saveProduct(@RequestBody ReqProductDto product) {
        productService.saveProduct(product);
    }

    @GetMapping
    public List<RespProductDto> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/{name}")
    public RespProductDto getProductByName(@PathVariable("name") String name
            , @RequestParam(value = "course") String course
            , @RequestParam(value = "student", required = false) String student
            , @RequestHeader("x-custom-header") String customHeader
            , @RequestHeader Map<String, String> allHeaders) {
        return productService.getProductByName(name);
    }


}
