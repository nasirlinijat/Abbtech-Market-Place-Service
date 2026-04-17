package com.abbtech.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCategoryDto {
    private String name;
    private String description;
    private String image;
    private Long parentId;
    private Integer categoryOrder;
    private Boolean isActive;
    private Boolean isDeleted;
}
