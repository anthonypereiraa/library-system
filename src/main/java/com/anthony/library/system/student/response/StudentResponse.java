package com.anthony.library.system.student.response;

import com.anthony.library.system.student.StudentStatus;

import java.time.LocalDate;

public record StudentResponse(
        String enrollmentNumber,
        String firstName,
        String lastName,
        String email,
        String courseCode,
        String semester,
        StudentStatus status,
        String cpf,
        String phoneNumber,
        LocalDate dateOfBirth) {}
