package com.abbtech.repository;

import com.abbtech.model.Item;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return (resultSet, i) -> {
            Item item = new Item(
                    resultSet.getString("name"),
                    resultSet.getBigDecimal("price"),
                    resultSet.getString("image"),
                    resultSet.getString("description"));
            item.setId(resultSet.getInt("id"));
            item.setCategoryId((Integer) resultSet.getObject("category_id"));
            item.setBrandId((Integer) resultSet.getObject("brand_id"));
            item.setIsActive(resultSet.getBoolean("is_active"));
            item.setIsDeleted(resultSet.getBoolean("is_deleted"));
            item.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
            item.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
            return item;
        };
    }

    @Override
    public Optional<Item> getById(Long id) {
        try {
            var item = jdbcTemplate.queryForObject("SELECT * FROM item WHERE id = ?", itemRowMapper(), id);
            return Optional.ofNullable(item);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Item> getByName(String name) {
        try {
            var item = jdbcTemplate.queryForObject("SELECT * FROM item WHERE name = ?", itemRowMapper(), name);
            return Optional.ofNullable(item);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM item WHERE id = ?", id);

    }

    @Override
    public void add(Item item) {
        Integer id = jdbcTemplate.queryForObject(
                "INSERT INTO item (name, description, image, price, category_id, brand_id, is_active, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id",
                Integer.class,
                item.getName(),
                item.getDescription(),
                item.getImage(),
                item.getPrice(),
                item.getCategoryId(),
                item.getBrandId(),
                item.getIsActive(),
                item.getIsDeleted()
        );
        item.setId(id);
    }

    @Override
    public void saveAll(List<Item> items) {

        String sql = "INSERT INTO item (name, description, image, price, category_id, brand_id, is_active, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) {
                Item p = items.get(i);
                try {
                    ps.setString(1, p.getName());
                    ps.setString(2, p.getDescription());
                    ps.setString(3, p.getImage());
                    ps.setBigDecimal(4, p.getPrice());
                    ps.setObject(5, p.getCategoryId());
                    ps.setObject(6, p.getBrandId());
                    ps.setBoolean(7, Boolean.TRUE.equals(p.getIsActive()));
                    ps.setBoolean(8, Boolean.TRUE.equals(p.getIsDeleted()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public int getBatchSize() {
                return items.size();
            }
        });

    }

    @Override
    public void updateByName(String name, Item item) {
        jdbcTemplate.update(
                "UPDATE item SET name = ?, description = ?, image = ?, price = ?, category_id = ?, brand_id = ?, updated_at = CURRENT_TIMESTAMP WHERE name = ?",
                item.getName(),
                item.getDescription(),
                item.getImage(),
                item.getPrice(),
                item.getCategoryId(),
                item.getBrandId(),
                name
        );
    }

    @Override
    public void updateDescriptionByName(String name, String description) {
        jdbcTemplate.update(
                "UPDATE item SET description = ?, updated_at = CURRENT_TIMESTAMP WHERE name = ?",
                description,
                name
        );
    }
}
