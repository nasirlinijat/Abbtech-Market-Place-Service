package com.abbtech.repository;

import com.abbtech.config.DatabaseConfig;
import com.abbtech.model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public void saveProduct(Product product) {
        String insertItemQuery = """
                INSERT INTO item (name, description, price, image,brand_id,category_id)
                 VALUES (?, ?, ?, ?,1,1)
                """;

        Connection connection = DatabaseConfig.getConnection();
        try (PreparedStatement itemStatement = connection.prepareStatement(insertItemQuery)) {
            PreparedStatement brandStatement = connection.prepareStatement("INSERT INTO Brand (name,description,image) VALUES ('Test','TestDescription','Image Url')");
            itemStatement.setString(1, product.getProductName());
            itemStatement.setString(2, product.getProductDescription());
            itemStatement.setBigDecimal(3, product.getProductPrice());
            itemStatement.setString(4, product.getProductImage());
            itemStatement.executeUpdate();
            brandStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

    }
}
