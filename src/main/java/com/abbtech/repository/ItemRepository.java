package com.abbtech.repository;

import com.abbtech.model.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> getAll();
    List<Item> getByName(String name);
    void  deleteByName(String name);
}
