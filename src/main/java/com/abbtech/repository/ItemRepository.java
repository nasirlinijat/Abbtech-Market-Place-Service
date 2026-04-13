package com.abbtech.repository;

import com.abbtech.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    List<Item> getAll();

    Optional<Item> getById(Long id);

    void deleteById(Long id);

    void add(Item item);

}
