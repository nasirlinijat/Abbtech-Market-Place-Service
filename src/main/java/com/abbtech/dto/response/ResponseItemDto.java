package com.abbtech.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseItemDto {

    private String name;
    private BigDecimal price;
    private String image;
    private String description;

}
