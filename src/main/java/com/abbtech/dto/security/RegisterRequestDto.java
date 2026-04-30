package com.abbtech.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record RegisterRequestDto(
        @NotBlank(message = "Username cannot be empty")
        @Length(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,

        @NotBlank(message = "Full name cannot be empty")
        String fullName,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email must be a valid address")
        String email,

        @NotBlank(message = "Password cannot be empty")
        @Length(min = 6, message = "Password must be at least 6 characters")
        String password
) {}
