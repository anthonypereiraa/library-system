package com.anthony.library.system.loan.request;

import com.anthony.library.system.loan.LoanStatus;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record LoanUpdateRequest(
        LoanStatus status,
        @PastOrPresent
        LocalDate startDate,
        @PastOrPresent
        LocalDate endDate) {}
