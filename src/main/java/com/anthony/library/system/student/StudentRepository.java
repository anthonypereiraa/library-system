package com.anthony.library.system.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByEnrollmentNumber(String enrollmentNumber);

    Optional<Student> findByCpf(String cpf);

    Optional<Student> findByEmail(String email);

    Optional<Student> findByPhoneNumber(String phoneNumber);
}
