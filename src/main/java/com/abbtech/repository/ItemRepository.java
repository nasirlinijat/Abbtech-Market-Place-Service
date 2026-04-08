package com.abbtech.repository;

import com.abbtech.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    List<Item> getAll();

    Optional<Item> getByName(String name);

    void deleteByName(String name);

    void add(Item item);

}
