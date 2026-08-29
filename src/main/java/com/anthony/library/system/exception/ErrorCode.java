package com.anthony.library.system.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


public enum ErrorCode {

    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "Email already exists", CONFLICT),
    PHONE_ALREADY_EXISTS("PHONE_ALREADY_EXISTS", "Phone number already exists", CONFLICT),
    CPF_ALREADY_EXISTS("CPF_ALREADY_EXISTS", "CPF already exists", CONFLICT),
    INTERNAL_EXCEPTION("INTERNAL_EXCEPTION", "Internal server error", INTERNAL_SERVER_ERROR),
    BOOK_ALREADY_EXISTS("BOOK_ALREADY_EXISTS", "Book already exists", CONFLICT),
    SEMESTER_YEAR_NOT_ACCEPTED("SEMESTER_YEAR_NOT_ACCEPTED", "Semester year not accepted", BAD_REQUEST),
    INVALID_ISBN("INVALID_ISBN", "Invalid ISBN", BAD_REQUEST),
    BOOK_NOT_FOUND("BOOK_NOT_FOUND", "Book not found", NOT_FOUND),
    COURSE_ALREADY_EXISTS("COURSE_ALREADY_EXISTS", "Course already exists", CONFLICT),
    COURSE_NOT_FOUND("COURSE_NOT_FOUND", "Course not found", NOT_FOUND),
    BOOK_UNAVAILABLE("BOOK_UNAVAILABLE", "Book unavailable", CONFLICT),
    STUDENT_NOT_FOUND("STUDENT_NOT_FOUND", "Student not found", NOT_FOUND),
    STUDENT_ALREADY_EXISTS("STUDENT_ALREADY_EXISTS", "Student already exists", CONFLICT),
    LOAN_LIMIT_EXCEEDED("LOAN_LIMIT_EXCEEDED", "Loan limit exceeded", CONFLICT),
    PATRON_HAS_OVERDUE_LOANS("PATRON_HAS_OVERDUE_LOANS", "Patron has overdue loans", CONFLICT),
    LOAN_NOT_FOUND("LOAN_NOT_FOUND", "Loan not found", NOT_FOUND),
    LOAN_ALREADY_RETURNED("LOAN_ALREADY_RETURNED", "Loan already returned", CONFLICT),
    DATE_NOT_ACCEPTED("DATE_NOT_ACCEPTED", "Date not accepted", BAD_REQUEST),
    YEAR_OF_PUBLICATION_INVALID("YEAR_OF_PUBLICATION_INVALID", "Year of publication invalid", BAD_REQUEST)

    ;

    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;

    ErrorCode(String code,
              String defaultMessage,
              HttpStatus status) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
