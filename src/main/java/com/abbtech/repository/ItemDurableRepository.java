package com.abbtech.repository;

import com.abbtech.config.DatabaseConfig;
import com.abbtech.model.Item;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//statement vs preparedStatement
public class ItemDurableRepository implements ItemRepository{


    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String  getItemQuery = "select * from item";
        try (
            Connection connection = DatabaseConfig.dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(getItemQuery)){

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                String image = resultSet.getString("image");

                items.add(new Item(name, price,image, description, price));

            }


        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return items;
    }
}
