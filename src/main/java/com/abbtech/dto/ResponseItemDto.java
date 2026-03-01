package com.abbtech.dto;

import java.math.BigDecimal;

public class ResponseItemDto {
    private String name;
    private BigDecimal price;
    private String image;
    private String description;

    public ResponseItemDto(String name, BigDecimal price, String image, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
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

    @Override
    public String toString() {
        return "ResponseItemDto{" +
               "name='" + name + '\'' +
               ", price=" + price +
               ", image='" + image + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
