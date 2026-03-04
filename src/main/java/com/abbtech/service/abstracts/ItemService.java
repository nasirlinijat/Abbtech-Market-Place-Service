package com.abbtech.service.abstracts;

import com.abbtech.dto.ResponseItemDto;

import java.util.List;

public interface ItemService {
    List<ResponseItemDto> getAll();
    List<ResponseItemDto> getByName(String name);
    void deleteByName(String name);
}
