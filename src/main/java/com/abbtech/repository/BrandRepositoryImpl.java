package com.abbtech.repository;

import com.abbtech.model.Brand;
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
public class BrandRepositoryImpl implements BrandRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Brand create(Brand brand) {
        return jdbcTemplate.queryForObject("""
                        INSERT INTO brand (name, description, image, is_active, is_deleted)
                        VALUES (?, ?, ?, COALESCE(?, true), COALESCE(?, false))
                        RETURNING *
                        """,
                brandRowMapper(),
                brand.getName(),
                brand.getDescription(),
                brand.getImage(),
                brand.getIsActive(),
                brand.getIsDeleted()
        );
    }

    @Override
    public Brand update(Long id, Brand brand) {
        jdbcTemplate.update("""
                        UPDATE brand
                        SET name = ?,
                            description = ?,
                            image = ?,
                            is_active = ?,
                            is_deleted = ?,
                            updated_at = CURRENT_TIMESTAMP
                        WHERE id = ?
                        """,
                brand.getName(),
                brand.getDescription(),
                brand.getImage(),
                brand.getIsActive(),
                brand.getIsDeleted(),
                id
        );
        return getById(id).orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM brand WHERE id = ?", id);
    }

    @Override
    public List<Brand> getAll() {
        return jdbcTemplate.query("SELECT * FROM brand", brandRowMapper());
    }

    @Override
    public Optional<Brand> getById(Long id) {
        var brands = jdbcTemplate.query("SELECT * FROM brand WHERE id = ?", brandRowMapper(), id);
        return brands.stream().findFirst();
    }

    @Override
    public List<Brand> bulkCreate(List<Brand> brands) {
        jdbcTemplate.batchUpdate("""
                        INSERT INTO brand (name, description, image, is_active, is_deleted)
                        VALUES (?, ?, ?, COALESCE(?, true), COALESCE(?, false))
                        """,
                brands,
                brands.size(),
                (PreparedStatement ps, Brand brand) -> {
                    ps.setString(1, brand.getName());
                    ps.setString(2, brand.getDescription());
                    ps.setString(3, brand.getImage());
                    ps.setObject(4, brand.getIsActive());
                    ps.setObject(5, brand.getIsDeleted());
                }
        );

        List<String> names = brands.stream().map(Brand::getName).toList();
        String placeholders = names.stream().map(n -> "?").collect(Collectors.joining(","));
        return jdbcTemplate.query(
                "SELECT * FROM brand WHERE name IN (" + placeholders + ")",
                brandRowMapper(),
                names.toArray()
        );
    }

    private static RowMapper<Brand> brandRowMapper() {
        return (resultSet, rowNum) -> new Brand(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("image"),
                resultSet.getBoolean("is_active"),
                resultSet.getBoolean("is_deleted"),
                resultSet.getTimestamp("created_at") == null ? null : resultSet.getTimestamp("created_at").toLocalDateTime(),
                resultSet.getTimestamp("updated_at") == null ? null : resultSet.getTimestamp("updated_at").toLocalDateTime()
        );
    }

}
