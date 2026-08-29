package com.anthony.library.system.loan.response;

import com.anthony.library.system.loan.Loan;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LoanResponseMapper implements Function<Loan, LoanResponse> {

    @Override
    public LoanResponse apply(Loan loan) {
        return new LoanResponse(
                loan.getBook().getIsbn(),
                loan.getBook().getTitle(),
                loan.getStudent().getEnrollmentNumber(),
                loan.getStudent().getFirstName(),
                loan.getStudent().getEmail(),
                loan.getStatus(),
                loan.getStartDate(),
                loan.getEndDate());
    }
}
