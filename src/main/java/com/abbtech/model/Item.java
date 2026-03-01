package com.abbtech.model;

import java.math.BigDecimal;

public class Item {
    private String name;
    private BigDecimal price;
    private String image;
    private String description;
    private BigDecimal wholesalePrice;

    public Item(String name, BigDecimal price, String image, String description, BigDecimal wholesalePrice) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.wholesalePrice = wholesalePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }
}
