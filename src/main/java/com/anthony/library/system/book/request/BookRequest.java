package com.anthony.library.system.book.request;

import jakarta.validation.constraints.*;

public record BookRequest(
        @NotBlank
        String title,
        @NotBlank
        String author,
        @NotBlank
        String publisher,
        @NotBlank
        String isbn,
        @NotNull
        @Positive
        Integer publicationYear,
        @NotNull
        @Positive
        Integer quantity) {}
