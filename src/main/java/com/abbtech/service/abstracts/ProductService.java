package com.abbtech.service.abstracts;

import com.abbtech.dto.ResponseProductDto;

import java.util.List;

public interface ProductService {
    List<ResponseProductDto> getAllProduct();
}
