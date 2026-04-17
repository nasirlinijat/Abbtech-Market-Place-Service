package com.abbtech.service.abstracts;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;

import java.util.List;

public interface ItemService {
    ResponseItemDto create(RequestItemDto request);

    ResponseItemDto update(Long id, RequestItemDto request);

    void delete(Long id);

    List<ResponseItemDto> getAll();

    ResponseItemDto getById(Long id);

    List<ResponseItemDto> bulkCreate(List<RequestItemDto> requests);

}
