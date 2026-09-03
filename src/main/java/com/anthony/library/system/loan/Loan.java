package com.anthony.library.system.loan;

import com.anthony.library.system.book.Book;
import com.anthony.library.system.common.BaseEntity;
import com.anthony.library.system.student.Student;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "book_uid", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "student_uid", nullable = false)
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    protected Loan() {
    }

    private Loan(Builder builder) {
        super();
        this.book = builder.book;
        this.student = builder.student;
        this.status = builder.status;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Book book;
        private Student student;
        private LoanStatus status;
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder book(Book book) {
            this.book = book;
            return this;
        }

        public Builder student(Student student) {
            this.student = student;
            return this;
        }

        public Builder status(LoanStatus status) {
            this.status = status;
            return this;
        }

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Loan build() {
            return new Loan(this);
        }
    }

    public Book getBook() {
        return book;
    }

    public Student getStudent() {
        return student;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "book=" + book +
                ", student=" + student +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
