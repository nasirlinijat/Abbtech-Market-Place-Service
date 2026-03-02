package com.abbtech.service;

import com.abbtech.dto.ReqProductDto;
import com.abbtech.dto.RespProductDto;

import java.util.List;

public interface ProductService {

    List<RespProductDto> getAllProducts();

    void saveProduct(ReqProductDto product);
}
