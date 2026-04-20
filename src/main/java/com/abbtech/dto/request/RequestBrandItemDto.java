package com.abbtech.dto.request;

import java.util.List;

public class RequestBrandItemDto {
    private String name;
    private String description;
    private String image;
    private Boolean active;
    private Boolean deleted;
    private List<RequestItemDto> items;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<RequestItemDto> getItems() {
        return items;
    }

    public void setItems(List<RequestItemDto> items) {
        this.items = items;
    }
}

