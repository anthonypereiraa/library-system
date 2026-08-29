package com.anthony.library.system.student;

import com.anthony.library.system.course.Course;
import com.anthony.library.system.course.CourseRepository;
import com.anthony.library.system.exception.BusinessException;
import com.anthony.library.system.student.request.StudentRequest;
import com.anthony.library.system.student.request.StudentUpdateRequest;
import com.anthony.library.system.student.response.StudentResponse;
import com.anthony.library.system.student.response.StudentResponseMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.anthony.library.system.exception.ErrorCode.*;
import static com.anthony.library.system.student.StudentStatus.ACTIVE;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentResponseMapper studentResponseMapper;

    public StudentService(final StudentRepository studentRepository,
                          final CourseRepository courseRepository,
                          final StudentResponseMapper studentResponseMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentResponseMapper = studentResponseMapper;
    }

    public void addNewStudent(final StudentRequest request) {
        checkIfStudentAlreadyExists(request.enrollmentNumber());
        checkIfCpfAlreadyExists(request.cpf());
        checkIfEmailAlreadyExists(request.email());
        checkIfPhoneAlreadyExists(request.phoneNumber());
        checkIfSemesterYearIsValid(request.semester());
        final var course = checkAndReturnCourse(request.courseCode());

        final var student =  Student.builder()
                .enrollmentNumber(request.enrollmentNumber())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .course(course)
                .semester(request.semester())
                .status(ACTIVE)
                .cpf(request.cpf())
                .phoneNumber(request.phoneNumber())
                .dateOfBirth(request.dateOfBirth())
                .build();

        studentRepository.save(student);
    }

    public List<StudentResponse > getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentResponseMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateStudent(final String studentId,
                              final StudentUpdateRequest request) {
        final var student = checkAndReturnStudent(studentId);
        if (request.enrollmentNumber() != null && !request.enrollmentNumber().isEmpty()) {
            updateEnrollmentNumber(student, request.enrollmentNumber());
        }
        if (request.email() != null && !request.email().isEmpty()) {
            updateEmail(student, request.email());
        }
        if (request.phoneNumber() != null && !request.phoneNumber().isEmpty()) {
            updatePhone(student, request.phoneNumber());
        }
        if (request.cpf() != null && !request.cpf().isEmpty()) {
            updateCpf(student, request.cpf());
        }
        if (request.firstName() != null && !request.firstName().isEmpty()) {
            updateFirstName(student, request.firstName());
        }
        if (request.lastName() != null && !request.lastName().isEmpty()) {
            updateLastName(student, request.lastName());
        }
        if (request.status() != null) {
            updateStatus(student, request.status());
        }
        if (request.courseCode() != null && !request.courseCode().isEmpty()) {
            updateCourse(student, request.courseCode());
        }
        if (request.semester() != null && !request.semester().isEmpty()) {
            updateSemester(student, request.semester());
        }
        if (request.dateOfBirth() != null) {
            updateDateOfBirth(student, request.dateOfBirth());
        }
    }

    @Transactional
    public void deleteStudent(final String studentId) {
        var student = checkAndReturnStudent(studentId);
        studentRepository.delete(student);
    }

    private void checkIfStudentAlreadyExists(final String enrollmentNumber) {
        Optional<Student> student = studentRepository.findByEnrollmentNumber(enrollmentNumber);
        if (student.isPresent()) {
            throw new BusinessException(STUDENT_ALREADY_EXISTS);
        }
    }

    private void checkIfCpfAlreadyExists(final String cpf) {
        Optional<Student> studentCpf = studentRepository.findByCpf(cpf);
        if (studentCpf.isPresent()) {
            throw new BusinessException(CPF_ALREADY_EXISTS);
        }
    }

    private void checkIfEmailAlreadyExists(final String email) {
        Optional<Student> studentEmail = studentRepository.findByEmail(email);
        if (studentEmail.isPresent()) {
            throw new BusinessException(EMAIL_ALREADY_EXISTS);
        }
    }

    private void checkIfPhoneAlreadyExists(final String phoneNumber) {
        Optional<Student> studentPhoneNumber = studentRepository.findByPhoneNumber(phoneNumber);
        if (studentPhoneNumber.isPresent()) {
            throw new BusinessException(PHONE_ALREADY_EXISTS);
        }
    }

    private void checkIfSemesterYearIsValid(final String semester) {
        final String semesterYear = semester.substring(0, 4);
        if (!semesterYear.equals(String.valueOf(LocalDate.now().getYear()))) {
            throw new BusinessException(SEMESTER_YEAR_NOT_ACCEPTED);
        }
    }

    private Course checkAndReturnCourse(final String code) {
        return courseRepository.findByCode(code).
                orElseThrow(() -> new BusinessException(COURSE_NOT_FOUND));
    }

    private Student checkAndReturnStudent(final String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(STUDENT_NOT_FOUND));
    }

    private void updateEnrollmentNumber(final Student student, final String enrollmentNumber) {
        checkIfStudentAlreadyExists(enrollmentNumber);
        if (student.getEnrollmentNumber().equals(enrollmentNumber)) {
            return;
        }
        student.setEnrollmentNumber(enrollmentNumber);
    }

    private void updateFirstName(final Student student, final String firstName) {
        if (student.getFirstName().equals(firstName)) {
            return;
        }
        student.setFirstName(firstName);
    }

    private void updateLastName(final Student student, final String lastName) {
        if (student.getLastName().equals(lastName)) {
            return;
        }
        student.setLastName(lastName);
    }

    private void updateEmail(final Student student, final String email) {
        checkIfEmailAlreadyExists(email);
        if (student.getEmail().equals(email)) {
            return;
        }
        student.setEmail(email);
    }

    private void updateCourse(final Student student, final String courseCode) {
        final var course = checkAndReturnCourse(courseCode);
        if (student.getCourse().equals(course)) {
            return;
        }
        student.setCourse(course);
    }

    private void updateSemester(final Student student, final String semester) {
        checkIfSemesterYearIsValid(semester);
        if (student.getSemester().equals(semester)) {
            return;
        }
        student.setSemester(semester);
    }

    private void updateStatus(final Student student, final StudentStatus status) {
        if (student.getStatus().equals(status)) {
            return;
        }
        student.setStatus(status);
    }

    private void updateCpf(final Student student, final String cpf) {
        checkIfCpfAlreadyExists(cpf);
        if (student.getCpf().equals(cpf)) {
            return;
        }
        student.setCpf(cpf);
    }

    private void updatePhone(final Student student, final String phoneNumber) {
        checkIfPhoneAlreadyExists(phoneNumber);
        if (student.getPhoneNumber().equals(phoneNumber)) {
            return;
        }
        student.setPhoneNumber(phoneNumber);
    }

    private void updateDateOfBirth(final Student student, final LocalDate dateOfBirth) {
        if (student.getDateOfBirth().equals(dateOfBirth)) {
            return;
        }
        student.setDateOfBirth(dateOfBirth);
    }
}

