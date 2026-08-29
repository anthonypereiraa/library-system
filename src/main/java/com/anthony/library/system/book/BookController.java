package com.anthony.library.system.book;

import com.anthony.library.system.book.request.BookRequest;
import com.anthony.library.system.book.request.BookUpdateRequest;
import com.anthony.library.system.book.response.BookResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('book:create')")
    public ResponseEntity<Void> registerNewBook(
            @Valid @RequestBody BookRequest request) {
        bookService.addNewBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('book:read')")
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @PatchMapping("/{bookId}")
    @PreAuthorize("hasAuthority('book:update')")
    public ResponseEntity<Void> updateBook(
            @PathVariable String bookId,
            @Valid @RequestBody BookUpdateRequest request){
        bookService.updateBook(bookId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasAuthority('book:delete')")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }
}
