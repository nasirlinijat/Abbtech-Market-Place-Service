package com.abbtech.repository;

import com.abbtech.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Item create(Item item);

    Item update(Long id, Item item);

    void delete(Long id);

    List<Item> getAll();

    Optional<Item> getById(Long id);

    List<Item> bulkCreate(List<Item> items);

}
