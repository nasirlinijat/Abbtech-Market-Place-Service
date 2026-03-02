package com.abbtech;

import com.abbtech.dto.ReqProductDto;
import com.abbtech.repository.ProductDurableRepositoryImpl;
import com.abbtech.service.ProductService;
import com.abbtech.service.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a method: ");
        String method = sc.nextLine();

        ProductService productService = new ProductServiceImpl(new ProductDurableRepositoryImpl());


        switch (method) {
            case "save":
                productService.saveProduct(new ReqProductDto("Desc", "image", BigDecimal.TEN, "name -1"));
                break;
            case "update":

            case "delete":
            case "divide":
            default:
                System.out.println("Invalid operation");
        }

    }
}
