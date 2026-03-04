package com.abbtech.repository;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.model.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> getAll();
    List<Item> getByName(String name);
    void deleteByName(String name);
    void updateByName(String name, RequestItemDto requestItemDto);

}
