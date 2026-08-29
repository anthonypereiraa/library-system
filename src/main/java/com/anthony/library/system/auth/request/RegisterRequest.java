package com.anthony.library.system.auth.request;

import com.anthony.library.system.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank
        @Size(min = 1, max = 50)
        String firstName,
        @NotBlank
        @Size(min = 1, max = 50)
        String lastName,
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9_.$+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$")
        String email,
        @NotBlank
        String password,
        @NotNull
        Role role){}
