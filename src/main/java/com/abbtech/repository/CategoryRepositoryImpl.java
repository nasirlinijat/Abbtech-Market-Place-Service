package com.abbtech.repository;

import com.abbtech.model.Category;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query("SELECT * FROM category", categoryRowMapper());
    }

    @Override
    public Optional<Category> getById(Long id) {
        try {
            Category category = jdbcTemplate.queryForObject("SELECT * FROM category WHERE id = ?", categoryRowMapper(), id);
            return Optional.ofNullable(category);
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void add(Category category) {
        Integer id = jdbcTemplate.queryForObject(
                "INSERT INTO category (name, description, image, parent_id, category_order, is_active, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id",
                Integer.class,
                category.getName(),
                category.getDescription(),
                category.getImage(),
                category.getParentId(),
                category.getCategoryOrder(),
                Boolean.TRUE.equals(category.getIsActive()),
                Boolean.TRUE.equals(category.getIsDeleted())
        );
        category.setId(id);
    }

    @Override
    public void updateById(Long id, Category category) {
        jdbcTemplate.update(
                "UPDATE category SET name = ?, description = ?, image = ?, parent_id = ?, category_order = ?, is_active = ?, is_deleted = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?",
                category.getName(),
                category.getDescription(),
                category.getImage(),
                category.getParentId(),
                category.getCategoryOrder(),
                Boolean.TRUE.equals(category.getIsActive()),
                Boolean.TRUE.equals(category.getIsDeleted()),
                id
        );
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM category WHERE id = ?", id);
    }

    private static RowMapper<Category> categoryRowMapper() {
        return (rs, i) -> {
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setDescription(rs.getString("description"));
            category.setImage(rs.getString("image"));
            category.setParentId((Integer) rs.getObject("parent_id"));
            category.setCategoryOrder(rs.getInt("category_order"));
            category.setIsActive(rs.getBoolean("is_active"));
            category.setIsDeleted(rs.getBoolean("is_deleted"));
            category.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            category.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return category;
        };
    }
}

