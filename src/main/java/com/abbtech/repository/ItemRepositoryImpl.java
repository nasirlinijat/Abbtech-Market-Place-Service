package com.abbtech.repository;

import com.abbtech.model.Item;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM item WHERE id = ?", id);

    }

    @Override
    public void add(Item item) {
        jdbcTemplate.update("INSERT INTO item (name, price, image, description) VALUES (?, ?, ?, ?)",
                item.getName(), item.getPrice(), item.getImage(), item.getDescription());
    }

    @Override
    public void saveAll(List<Item> items) {

        String sql = "INSERT INTO item (name, price, image, description) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) {
                Item p = items.get(i);
                try {
                    ps.setString(1, p.getName());
                    ps.setBigDecimal(2, p.getPrice());
                    ps.setString(3, p.getImage());
                    ps.setString(4, p.getDescription());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public int getBatchSize() {
                return items.size(); // whole list
            }
        });

    }
}
