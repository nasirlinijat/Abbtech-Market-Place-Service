package com.abbtech.repository;

import com.abbtech.model.Category;
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
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Category create(Category category) {
        return jdbcTemplate.queryForObject("""
                        INSERT INTO category (name, description, image, parent_id, category_order, is_active, is_deleted)
                        VALUES (?, ?, ?, ?, COALESCE(?, 1), COALESCE(?, true), COALESCE(?, false))
                        RETURNING *
                        """,
                categoryRowMapper(),
                category.getName(),
                category.getDescription(),
                category.getImage(),
                category.getParentId(),
                category.getCategoryOrder(),
                category.getIsActive(),
                category.getIsDeleted()
        );
    }

    @Override
    public Category update(Long id, Category category) {
        jdbcTemplate.update("""
                        UPDATE category
                        SET name = ?,
                            description = ?,
                            image = ?,
                            parent_id = ?,
                            category_order = ?,
                            is_active = ?,
                            is_deleted = ?,
                            updated_at = CURRENT_TIMESTAMP
                        WHERE id = ?
                        """,
                category.getName(),
                category.getDescription(),
                category.getImage(),
                category.getParentId(),
                category.getCategoryOrder(),
                category.getIsActive(),
                category.getIsDeleted(),
                id
        );
        return getById(id).orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM category WHERE id = ?", id);
    }

    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query("SELECT * FROM category", categoryRowMapper());
    }

    @Override
    public Optional<Category> getById(Long id) {
        var categories = jdbcTemplate.query("SELECT * FROM category WHERE id = ?", categoryRowMapper(), id);
        return categories.stream().findFirst();
    }

    @Override
    public List<Category> bulkCreate(List<Category> categories) {

        jdbcTemplate.batchUpdate("""
                        INSERT INTO category (name, description, image, parent_id, category_order, is_active, is_deleted)
                        VALUES (?, ?, ?, ?, COALESCE(?, 1), COALESCE(?, true), COALESCE(?, false))
                        """,
                categories,
                categories.size(),
                (PreparedStatement ps, Category category) -> {
                    ps.setString(1, category.getName());
                    ps.setString(2, category.getDescription());
                    ps.setString(3, category.getImage());
                    ps.setObject(4, category.getParentId());
                    ps.setObject(5, category.getCategoryOrder());
                    ps.setObject(6, category.getIsActive());
                    ps.setObject(7, category.getIsDeleted());
                }
        );

        List<String> names = categories.stream().map(Category::getName).toList();
        String placeholders = names.stream().map(n -> "?").collect(Collectors.joining(","));
        return jdbcTemplate.query(
                "SELECT * FROM category WHERE name IN (" + placeholders + ")",
                categoryRowMapper(),
                names.toArray()
        );
    }

    private static RowMapper<Category> categoryRowMapper() {
        return (resultSet, rowNum) -> new Category(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("image"),
                (Long) resultSet.getObject("parent_id"),
                (Integer) resultSet.getObject("category_order"),
                resultSet.getBoolean("is_active"),
                resultSet.getBoolean("is_deleted"),
                resultSet.getTimestamp("created_at") == null ? null : resultSet.getTimestamp("created_at").toLocalDateTime(),
                resultSet.getTimestamp("updated_at") == null ? null : resultSet.getTimestamp("updated_at").toLocalDateTime()
        );
    }

}
