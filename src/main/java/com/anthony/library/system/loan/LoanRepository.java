package com.anthony.library.system.loan;

import com.anthony.library.system.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, String> {

    long countByStudentAndStatus(Student student, LoanStatus status);
}
