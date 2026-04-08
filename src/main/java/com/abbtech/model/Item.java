package com.abbtech.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Item {
    private String name;
    private BigDecimal price;
    private String image;
    private String description;
    private BigDecimal wholesalePrice;

}
