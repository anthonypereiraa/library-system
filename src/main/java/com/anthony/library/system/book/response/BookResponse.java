package com.anthony.library.system.book.response;

public record BookResponse(
        String title,
        String author,
        String publisher,
        String isbn,
        Integer publicationYear,
        Integer quantity) {
}
