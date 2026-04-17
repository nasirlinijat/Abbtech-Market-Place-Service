package com.abbtech.service.abstracts;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.ResponseBrandDto;

import java.util.List;

public interface BrandService {
    ResponseBrandDto create(RequestBrandDto request);

    ResponseBrandDto update(Long id, RequestBrandDto request);

    void delete(Long id);

    List<ResponseBrandDto> getAll();

    ResponseBrandDto getById(Long id);

    List<ResponseBrandDto> bulkCreate(List<RequestBrandDto> requests);
}
