package com.anthony.library.system.loan.request;

import jakarta.validation.constraints.*;

public record LoanRequest(
        @NotBlank
        @Pattern(regexp = "^[0-9]{12}$")
        String enrollmentNumber,
        @NotBlank
        String isbn) {}
