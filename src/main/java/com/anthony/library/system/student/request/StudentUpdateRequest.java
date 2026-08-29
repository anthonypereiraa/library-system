package com.anthony.library.system.student.request;

import com.anthony.library.system.student.StudentStatus;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record StudentUpdateRequest(
        @Pattern(regexp = "^[0-9]{12}$")
        String enrollmentNumber,
        @Size(min = 1, max = 50)
        String firstName,
        @Size(min = 1, max = 50)
        String lastName,
        @Pattern(regexp = "^[a-zA-Z0-9_.$+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$")
        String email,
        String courseCode,
        String semester,
        StudentStatus status,
        @Pattern(regexp = "^[0-9]{11}$|^[0-9]{3}-[0-9]{3}-[0-9]{3}\\.[0-9]{2}$")
        String cpf,
        @Pattern(regexp = "^\\+55[0-9]{10}$")
        String phoneNumber,
        @PastOrPresent
        LocalDate dateOfBirth) {}
