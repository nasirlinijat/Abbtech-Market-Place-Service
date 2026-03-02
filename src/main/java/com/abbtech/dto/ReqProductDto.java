package com.abbtech.dto;

import java.math.BigDecimal;

public class ReqProductDto {
    private String productName;
    private BigDecimal productPrice;
    private String productImage;
    private String productDescription;


    public ReqProductDto(String productDescription, String productImage, BigDecimal productPrice, String productName) {
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        return "RespProductDto{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productImage='" + productImage + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
