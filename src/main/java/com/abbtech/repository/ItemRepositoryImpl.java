package com.abbtech.repository;

import com.abbtech.model.Item;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Item create(Item item) {
        return jdbcTemplate.queryForObject("""
                        INSERT INTO item (name, description, image, price, category_id, brand_id, is_active, is_deleted)
                        VALUES (?, ?, ?, ?, ?, ?, COALESCE(?, true), COALESCE(?, false))
                        RETURNING *
                        """,
                itemRowMapper(),
                item.getName(),
                item.getDescription(),
                item.getImage(),
                item.getPrice(),
                item.getCategoryId(),
                item.getBrandId(),
                item.getIsActive(),
                item.getIsDeleted()
        );
    }

    @Override
    public Item update(Long id, Item item) {
        jdbcTemplate.update("""
                        UPDATE item
                        SET name = ?,
                            description = ?,
                            image = ?,
                            price = ?,
                            category_id = ?,
                            brand_id = ?,
                            is_active = ?,
                            is_deleted = ?,
                            updated_at = CURRENT_TIMESTAMP
                        WHERE id = ?
                        """,
                item.getName(),
                item.getDescription(),
                item.getImage(),
                item.getPrice(),
                item.getCategoryId(),
                item.getBrandId(),
                item.getIsActive(),
                item.getIsDeleted(),
                id
        );
        return getById(id).orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM item WHERE id = ?", id);
    }

    @Override
    public List<Item> getAll() {
        return jdbcTemplate.query("SELECT * FROM item", itemRowMapper());
    }

    @Override
    public Optional<Item> getById(Long id) {
        var items = jdbcTemplate.query("SELECT * FROM item WHERE id = ?", itemRowMapper(), id);
        return items.stream().findFirst();
    }

    @Override
    public List<Item> bulkCreate(List<Item> items) {

        jdbcTemplate.batchUpdate("""
                        INSERT INTO item (name, description, image, price, category_id, brand_id, is_active, is_deleted)
                        VALUES (?, ?, ?, ?, ?, ?, COALESCE(?, true), COALESCE(?, false))
                        """,
                items,
                items.size(),
                (PreparedStatement ps, Item item) -> {
                    ps.setString(1, item.getName());
                    ps.setString(2, item.getDescription());
                    ps.setString(3, item.getImage());
                    ps.setBigDecimal(4, item.getPrice());
                    ps.setObject(5, item.getCategoryId());
                    ps.setObject(6, item.getBrandId());
                    ps.setObject(7, item.getIsActive());
                    ps.setObject(8, item.getIsDeleted());
                }
        );

        List<String> names = items.stream().map(Item::getName).toList();
        String placeholders = names.stream().map(n -> "?").collect(Collectors.joining(","));

        return jdbcTemplate.query(
                "SELECT * FROM item WHERE name IN (" + placeholders + ")",
                itemRowMapper(),
                names.toArray()
        );
    }

    private static RowMapper<Item> itemRowMapper() {
        return (resultSet, rowNum) -> new Item(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("image"),
                resultSet.getBigDecimal("price"),
                toLong(resultSet.getObject("category_id")),
                toLong(resultSet.getObject("brand_id")),
                resultSet.getBoolean("is_active"),
                resultSet.getBoolean("is_deleted"),
                resultSet.getTimestamp("created_at") == null ? null : resultSet.getTimestamp("created_at").toLocalDateTime(),
                resultSet.getTimestamp("updated_at") == null ? null : resultSet.getTimestamp("updated_at").toLocalDateTime()
        );
    }

    private static Long toLong(Object value) {
        return value == null ? null : ((Number) value).longValue();
    }
}
