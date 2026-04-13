package com.abbtech.repository;

import com.abbtech.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public Optional<Item> getByName(String itemName) {
        return null;
    }

    @Override
    public void deleteByName(String itemName) {

    }

    @Override
    public void add(Item item) {
    }
}
