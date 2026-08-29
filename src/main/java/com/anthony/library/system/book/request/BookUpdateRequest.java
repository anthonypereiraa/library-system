package com.anthony.library.system.book.request;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public record BookUpdateRequest(
        String title,
        String author,
        String publisher,
        String isbn,
        @PastOrPresent
        Integer publicationYear,
        @Positive
        Integer quantity) {}
