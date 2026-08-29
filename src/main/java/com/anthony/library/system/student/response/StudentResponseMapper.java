package com.anthony.library.system.student.response;

import com.anthony.library.system.student.Student;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StudentResponseMapper implements Function<Student, StudentResponse> {

    @Override
    public StudentResponse apply(Student student) {
        return new StudentResponse(
                student.getEnrollmentNumber(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getCourse().getCode(),
                student.getSemester(),
                student.getStatus(),
                student.getCpf(),
                student.getPhoneNumber(),
                student.getDateOfBirth());
    }
}
