package com.abbtech.repository;

import com.abbtech.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    List<Item> getAll();

    Optional<Item> getById(Long id);

    Optional<Item> getByName(String name);

    void deleteById(Long id);

    void add(Item item);

    void saveAll(List<Item> items);

    void updateByName(String name, Item item);

    void updateDescriptionByName(String name, String description);

}
