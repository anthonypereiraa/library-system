package com.anthony.library.system.loan;

import com.anthony.library.system.loan.request.LoanRequest;
import com.anthony.library.system.loan.request.LoanUpdateRequest;
import com.anthony.library.system.loan.response.LoanResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('loan:create')")
    public ResponseEntity<Void> registerNewLoan(
            @Valid @RequestBody LoanRequest request) {
        loanService.addNewLoan(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('loan:read')")
    public ResponseEntity<List<LoanResponse>> getLoans() {
        return ResponseEntity.ok(loanService.getLoans());
    }

    @PatchMapping("/{loanId}")
    @PreAuthorize("hasAuthority('loan:update')")
    public ResponseEntity<Void> updateLoan(
            @PathVariable String loanId,
            @RequestBody LoanUpdateRequest request) {
        loanService.updateLoan(loanId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{loanId}")
    @PreAuthorize("hasAuthority('loan:delete')")
    public ResponseEntity<Void> deleteLoan(@PathVariable String loanId) {
        loanService.deleteLoan(loanId);
        return ResponseEntity.noContent().build();
    }
}
