package com.anthony.library.system.book;

import com.anthony.library.system.book.request.BookRequest;
import com.anthony.library.system.book.request.BookUpdateRequest;
import com.anthony.library.system.book.response.BookResponse;
import com.anthony.library.system.book.response.BookResponseMapper;
import com.anthony.library.system.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.anthony.library.system.exception.ErrorCode.*;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookResponseMapper bookResponseMapper;

    public BookService(final BookRepository bookRepository,
                       final BookResponseMapper bookResponseMapper) {
        this.bookRepository = bookRepository;
        this.bookResponseMapper = bookResponseMapper;
    }

    public void addNewBook(final BookRequest request) {
        checkIfBookAlreadyExists(request.isbn());
        validateIsbnStructure(request.isbn());
        validatePublicationYear(request.publicationYear());

        var book = Book.builder()
                .title(request.title())
                .author(request.author())
                .publisher(request.publisher())
                .isbn(request.isbn())
                .publicationYear(request.publicationYear())
                .quantity(request.quantity())
                .build();
        bookRepository.save(book);
    }

    private void validatePublicationYear(Integer publicationYear) {
        if (publicationYear > LocalDate.now().getYear()) {
            throw new BusinessException(YEAR_OF_PUBLICATION_INVALID);
        }
    }

    public List<BookResponse> getBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookResponseMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateBook(final String bookId, final BookUpdateRequest request) {
        final var book = checkAndReturnBook(bookId);
        if (request.title() != null && !request.title().isEmpty()) {
            updateTitle(book, request.title());
        }
        if (request.author() != null && !request.author().isEmpty()) {
            updateAuthor(book, request.author());
        }
        if (request.publisher() != null && !request.publisher().isEmpty()) {
            updatePublisher(book, request.publisher());
        }
        if (request.isbn() != null && !request.isbn().isEmpty()) {
            updateIsbn(book, request.isbn());
        }
        if (request.publicationYear() != null) {
            updatePublicationYear(book, request.publicationYear());
        }
        if (request.quantity() != null) {
            updateQuantity(book, request.quantity());
        }
    }

    @Transactional
    public void deleteBook(final String bookId) {
        final var book = checkAndReturnBook(bookId);
        bookRepository.delete(book);
    }

    private void checkIfBookAlreadyExists(final String isbn) {
        final Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);;
        if (bookOptional.isPresent()) {
            throw new BusinessException(BOOK_ALREADY_EXISTS);
        }
    }

    public void validateIsbnStructure(final String isbn) {
        final String isbnFiltered = isbn.replace("-", "");
        final int checkDigit = Character.getNumericValue(isbnFiltered.charAt(12));

        int sum = 0;
        int remainder;
        for (int i = 0; i < isbnFiltered.length()-1; i += 2) {
            sum += Character.getNumericValue(isbnFiltered.charAt(i));
        }
        for (int i = 1; i <= isbnFiltered.length()-1; i += 2) {
            sum += Character.getNumericValue(isbnFiltered.charAt(i)) * 3;
        }
        remainder = 10 - sum % 10;
        if (remainder != checkDigit) {
            throw new BusinessException(INVALID_ISBN);
        }
    }

    private Book checkAndReturnBook(final String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BOOK_NOT_FOUND));
    }

    private void updateTitle(final Book book, final String title) {
        if (book.getTitle().equals(title)) {
            return;
        }
        book.setTitle(title);
    }

    private void updateAuthor(final Book book, final String author) {
        if (book.getAuthor().equals(author)) {
            return;
        }
        book.setAuthor(author);
    }

    private void updatePublisher(final Book book, final String publisher) {
        if (book.getPublisher().equals(publisher)) {
            return;
        }
        book.setPublisher(publisher);
    }

    private void updateIsbn(final Book book, final String isbn) {
        validateIsbnStructure(isbn);
        checkIfBookAlreadyExists(isbn);
        if (book.getIsbn().equals(isbn)) {
            return;
        }
        book.setIsbn(isbn);
    }

    private void updatePublicationYear(final Book book, final Integer publicationYear) {
        if (book.getPublicationYear().equals(publicationYear)) {
            return;
        }
        book.setPublicationYear(publicationYear);
    }

    private void updateQuantity(final Book book, final Integer quantity) {
        if (book.getQuantity().equals(quantity)) {
            return;
        }
        book.setQuantity(quantity);
    }
}