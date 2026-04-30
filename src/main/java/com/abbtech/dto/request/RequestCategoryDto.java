package com.abbtech.dto.request;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record RequestCategoryDto(
        @NotBlank(message = "category.name.not.empty")
        @Length(min = 2, max = 100, message = "Category name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "category.description.not.empty")
        @Length(max = 250, message = "Description cannot exceed 250 characters")
        String description,

        @NotBlank(message = "Category image cannot be empty")
        String image,

        Integer parentId,
        Integer categoryOrder,

        @NotNull(message = "isActive field is required")
        @AssertTrue(message = "Category must be active on creation")
        Boolean isActive,

        @NotNull(message = "isDeleted field is required")
        @AssertFalse(message = "Category cannot be deleted on creation")
        Boolean isDeleted
) {
}
