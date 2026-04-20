package com.abbtech.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestBrandDto {
    private String name;
    private String description;
    private String image;
    private Boolean isActive;
    private Boolean isDeleted;
}

