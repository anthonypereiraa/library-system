package com.anthony.library.system.book;

import com.anthony.library.system.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String publisher;
    @Column(unique = true, nullable = false)
    private String isbn;
    @Column(nullable = false)
    private Integer publicationYear;
    @Column(nullable = false)
    private Integer quantity;

    protected Book() {}

    private Book(Builder builder) {
        super();
        this.title = builder.title;
        this.author = builder.author;
        this.publisher = builder.publisher;
        this.isbn = builder.isbn;
        this.publicationYear = builder.publicationYear;
        this.quantity = builder.quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String title;
        private String author;
        private String publisher;
        private String isbn;
        private Integer publicationYear;
        private Integer quantity;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder publicationYear(Integer publicationYear) {
            this.publicationYear = publicationYear;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", quantity=" + quantity +
                '}';
    }
}
