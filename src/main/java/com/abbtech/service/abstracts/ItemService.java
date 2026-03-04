package com.abbtech.service.abstracts;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;

import java.util.List;

public interface ItemService {
    List<ResponseItemDto> getAll();
    List<ResponseItemDto> getByName(String name);
    void deleteByName(String name);
    void updateByName(String name, RequestItemDto requestItemDto);
}
