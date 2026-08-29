package com.anthony.library.system.book.response;

import com.anthony.library.system.book.Book;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookResponseMapper implements Function<Book, BookResponse> {

    @Override
    public BookResponse apply(Book book) {
        return new BookResponse(
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getQuantity());
    }
}
