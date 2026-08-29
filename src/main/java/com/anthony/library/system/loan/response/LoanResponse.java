package com.anthony.library.system.loan.response;

import com.anthony.library.system.loan.LoanStatus;

import java.time.LocalDate;

public record LoanResponse(
        String bookIsbn,
        String bookTitle,
        String studentEnrollmentNo,
        String studentName,
        String studentEmail,
        LoanStatus status,
        LocalDate startDate,
        LocalDate endDate) {}
