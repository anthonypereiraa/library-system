package com.anthony.library.system.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthenticateRequest (
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9_.$+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$")
        String email,
        @NotBlank
        String password){}
