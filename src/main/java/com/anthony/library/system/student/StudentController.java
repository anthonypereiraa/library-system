package com.anthony.library.system.student;

import com.anthony.library.system.student.request.StudentRequest;
import com.anthony.library.system.student.request.StudentUpdateRequest;
import com.anthony.library.system.student.response.StudentResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:create')")
    public ResponseEntity<Void> registerNewStudent(
            @Valid @RequestBody StudentRequest request) {
        studentService.addNewStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('student:read')")
    public ResponseEntity<List<StudentResponse>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PatchMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:update')")
    public ResponseEntity<Void> updateStudent(
            @PathVariable String studentId,
            @Valid @RequestBody StudentUpdateRequest request) {
        studentService.updateStudent(studentId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:delete')")
    public ResponseEntity<Void> deleteStudent(@PathVariable String studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
