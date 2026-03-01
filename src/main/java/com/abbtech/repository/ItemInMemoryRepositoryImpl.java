package com.abbtech.repository;

import com.abbtech.model.Item;

import java.math.BigDecimal;
import java.util.List;

public class ItemInMemoryRepositoryImpl implements ItemRepository {


    private static final List<Item> items = List.of(

            new Item(
                    "Wireless Mouse",
                    new BigDecimal("29.99"),
                    "mouse.jpg",
                    "High quality wireless mouse",
                    new BigDecimal("18.00")
            ),

            new Item(
                    "Gaming Keyboard",
                    new BigDecimal("89.99"),
                    "keyboard.jpg",
                    "Mechanical gaming keyboard",
                    new BigDecimal("60.00")
            ),

            new Item(
                    "4K Monitor",
                    new BigDecimal("399.99"),
                    "monitor.jpg",
                    "27-inch 4K Monitor",
                    new BigDecimal("320.00")
            ),
            new Item(
                    "4K Monitor",
                    new BigDecimal("500.00"),
                    "monitor.jpg",
                    "28-inch 4K Monitor",
                    new BigDecimal("399.99")
                    )

    );

    @Override
    public List<Item> getAllItems() {
        return items;
    }
}
