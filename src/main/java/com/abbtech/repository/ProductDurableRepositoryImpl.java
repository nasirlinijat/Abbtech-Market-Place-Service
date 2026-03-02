package com.abbtech.repository;

import com.abbtech.config.DatabaseConfig;
import com.abbtech.model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDurableRepositoryImpl implements ProductRepository {
    @Override
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();
        String getProductsQuery = "SELECT * FROM item";
        try (
                Connection connection = DatabaseConfig.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(getProductsQuery)) {

            ResultSet resulSet = statement.executeQuery();
            while (resulSet.next()) {
                String name = resulSet.getString("name");
                String description = resulSet.getString("description");
                BigDecimal price = resulSet.getBigDecimal("price");
                String image = resulSet.getString("image");
                products.add(new Product(description, image, price, name, price));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
