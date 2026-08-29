package com.anthony.library.system.loan;

import com.anthony.library.system.book.Book;
import com.anthony.library.system.book.BookRepository;
import com.anthony.library.system.exception.BusinessException;
import com.anthony.library.system.loan.request.LoanRequest;
import com.anthony.library.system.loan.request.LoanUpdateRequest;
import com.anthony.library.system.loan.response.LoanResponse;
import com.anthony.library.system.loan.response.LoanResponseMapper;
import com.anthony.library.system.student.Student;
import com.anthony.library.system.student.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.anthony.library.system.exception.ErrorCode.*;
import static com.anthony.library.system.loan.LoanStatus.*;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final LoanResponseMapper loanResponseMapper;

    private static final int MAX_ACTIVE_LOANS = 3;
    private static final int MAX_DAYS_LOAN = 15;

    public LoanService(final LoanRepository loanRepository,
                       final BookRepository bookRepository,
                       final StudentRepository studentRepository,
                       final LoanResponseMapper loanResponseMapper) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.loanResponseMapper = loanResponseMapper;
    }

    @Transactional
    public void addNewLoan(final LoanRequest request) {
        final var book = checkIfBookExistsAndReturn(request.isbn());
        checkIfBookIsAvailableToBorrow(book);
        final var student = checkIfStudentExistsAndReturn(request.enrollmentNumber());
        validateStudentLoanEligibility(student);

        final var loan = Loan.builder()
                .book(book)
                .student(student)
                .status(LOANED)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(MAX_DAYS_LOAN))
                .build();
        loanRepository.save(loan);
    }

    public List<LoanResponse> getLoans() {
        return loanRepository.findAll()
                .stream()
                .map(loanResponseMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateLoan(final String loanId, final LoanUpdateRequest request) {
        final var loan = checkAndReturnLoan(loanId);
        if (request.status() != null) {
            updateStatus(loan, request.status());
        }
        if (request.startDate() != null) {
            updateStartDate(loan, request.startDate());
        }
        if (request.endDate() != null) {
            updateEndDate(loan, request.endDate());
        }
    }

    @Transactional
    public void deleteLoan(final String loanId) {
        final var loan = checkAndReturnLoan(loanId);
        loanRepository.delete(loan);
    }

    private Book checkIfBookExistsAndReturn(final String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException(BOOK_NOT_FOUND));
    }

    private void checkIfBookIsAvailableToBorrow(final Book book) {
        if (book.getQuantity() <= 0) {
            throw new BusinessException(BOOK_UNAVAILABLE);
        }
        book.setQuantity(book.getQuantity()-1);
    }

    private Student checkIfStudentExistsAndReturn(final String enrollmentNumber) {
        return studentRepository.findByEnrollmentNumber(enrollmentNumber)
                .orElseThrow(() -> new BusinessException(STUDENT_NOT_FOUND));
    }

    private void validateStudentLoanEligibility(final Student student) {
        final long activeLoans = loanRepository.countByStudentAndStatus(student, LOANED);
        final long overdueLoans = loanRepository.countByStudentAndStatus(student, OVERDUE);

        if (activeLoans >= MAX_ACTIVE_LOANS) {
            throw new BusinessException(LOAN_LIMIT_EXCEEDED);
        }
        if (overdueLoans > 0) {
            throw new BusinessException(PATRON_HAS_OVERDUE_LOANS);
        }
    }

    private Loan checkAndReturnLoan(String loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new BusinessException(LOAN_NOT_FOUND));
    }

    private void updateStatus(final Loan loan,
                              final LoanStatus status) {
        if (loan.getStatus() == RETURNED) {
            throw new BusinessException(LOAN_ALREADY_RETURNED);
        }
        if (status == RETURNED) {
            loan.getBook().setQuantity(loan.getBook().getQuantity()+1);
        }
        if (loan.getStatus() == status) {
            return;
        }
        loan.setStatus(status);
    }

    private void updateStartDate(final Loan loan, final LocalDate startDate) {
        if (startDate.isAfter(loan.getEndDate())) {
            throw new BusinessException(DATE_NOT_ACCEPTED);
        }
        if (loan.getStartDate().equals(startDate)) {
            return;
        }
        loan.setStartDate(startDate);
    }

    private void updateEndDate(final Loan loan, final LocalDate endDate) {
        if (endDate.isBefore(loan.getStartDate())) {
            throw new BusinessException(DATE_NOT_ACCEPTED);
        }
        if (loan.getEndDate().equals(endDate)) {
            return;
        }
        loan.setEndDate(endDate);
    }
}
