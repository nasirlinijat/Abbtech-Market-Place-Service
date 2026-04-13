package com.abbtech.repository;

import com.abbtech.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        return jdbcTemplate.query("SELECT * FROM item", itemRowMapper());

    }

    private static RowMapper<Item> itemRowMapper() {
        return (resultSet, i) -> new Item(
                resultSet.getString("name")
                , resultSet.getBigDecimal("price")
                , resultSet.getString("image")
                , resultSet.getString("description"));
    }

    @Override
    public Optional<Item> getById(Long id) {
        var item = jdbcTemplate.queryForObject("SELECT * FROM item WHERE id = ?", itemRowMapper(), id);
        return Optional.ofNullable(item);
    }

    @Override
    public void deleteByName(String itemName) {

    }

    @Override
    public void add(Item item) {
    }
}
