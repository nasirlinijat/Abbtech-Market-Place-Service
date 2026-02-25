package com.abbtech.dto;

import java.math.BigDecimal;

public class ResponseProductDto {
    private String productName;

    private BigDecimal productPrice;

    private String productImage;
    private String productDescription;
    public ResponseProductDto(String productName, BigDecimal productPrice, String productImage, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productDescription = productDescription;
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
        return "ResponseProductDto{" +
               "productName='" + productName + '\'' +
               ", productPrice=" + productPrice +
               ", productImage='" + productImage + '\'' +
               ", productDescription='" + productDescription + '\'' +
               '}';
    }
}
