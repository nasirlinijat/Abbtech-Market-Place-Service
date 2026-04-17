package com.abbtech.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBrandDto {
    private String name;
    private String description;
    private String image;
    private Boolean isActive;
    private Boolean isDeleted;
}
