package com.abbtech.repository;

import com.abbtech.model.Item;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemDurableRepository implements ItemRepository {

    private static List<Item> items = new ArrayList<>(Arrays.asList(
            new Item("Wireless Headphones", new BigDecimal("79.99"), "headphones.jpg", "Premium noise-cancelling wireless headphones with 30hr battery life", new BigDecimal("45.00")),
            new Item("Mechanical Keyboard", new BigDecimal("129.99"), "keyboard.jpg", "RGB backlit mechanical keyboard with tactile brown switches", new BigDecimal("72.00")),
            new Item("USB-C Hub", new BigDecimal("49.99"), "usbhub.jpg", "7-in-1 USB-C hub with HDMI, SD card reader and 100W PD charging", new BigDecimal("25.00")),
            new Item("Webcam HD", new BigDecimal("89.99"), "webcam.jpg", "1080p HD webcam with built-in microphone and auto light correction", new BigDecimal("50.00")),
            new Item("Mouse Pad XL", new BigDecimal("24.99"), "mousepad.jpg", "Extended gaming mouse pad with non-slip rubber base, 80x40cm", new BigDecimal("10.00")),
            new Item("LED Desk Lamp", new BigDecimal("39.99"), "lamp.jpg", "Dimmable LED desk lamp with USB charging port and touch control", new BigDecimal("18.00"))
    ));

    @Override
    public List<Item> getAll() {
        return items;
    }

    @Override
    public Optional<Item> getByName(String itemName) {
        return items.stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst();
    }

    @Override
    public void deleteByName(String itemName) {
        Item removedItem = items.stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No element with name found!"));

        items.remove(removedItem);
    }

    @Override
    public void add(Item item) {
        items.add(item);
    }
}
