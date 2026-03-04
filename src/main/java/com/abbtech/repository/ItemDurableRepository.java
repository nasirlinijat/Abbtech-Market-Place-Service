package com.abbtech.repository;

import com.abbtech.config.DatabaseConfig;
import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.model.Item;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//statement vs preparedStatement
public class ItemDurableRepository implements ItemRepository {


    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String getItemQuery = "SELECT * FROM item";
        try (
                Connection connection = DatabaseConfig.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(getItemQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                String image = resultSet.getString("image");

                items.add(new Item(name, price, image, description, price));

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    @Override
    public List<Item> getByName(String itemName) {
        List<Item> items = new ArrayList<>();
        String getItemQuery = "SELECT * FROM item WHERE name = ?";
        try (
                Connection connection = DatabaseConfig.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(getItemQuery)) {

            statement.setString(1, itemName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                String image = resultSet.getString("image");

                items.add(new Item(name, price, image, description, price));

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    @Override
    public void deleteByName(String itemName) {
        String deleteQuery = "DELETE FROM item WHERE name = ?";
        try (
                Connection connection = DatabaseConfig.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(deleteQuery)
        ) {
            statement.setString(1, itemName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateByName(String itemName, RequestItemDto requestItemDto) {
        String updateQuery = """
                UPDATE item
                SET name = ?, price = ?, image = ?, description = ?
                WHERE name = ? 
                """;
        try (
                Connection connection = DatabaseConfig.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(updateQuery)
                ){
            statement.setString(1, requestItemDto.getName());
            statement.setBigDecimal(2, requestItemDto.getPrice());
            statement.setString(3, requestItemDto.getImage());
            statement.setString(4, requestItemDto.getDescription());
            statement.setString(5, itemName);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
