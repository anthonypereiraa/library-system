package com.anthony.library.system.student;

import com.anthony.library.system.common.BaseEntity;
import com.anthony.library.system.course.Course;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "STUDENTS")
public class Student extends BaseEntity {
    @Column(name = "ENROLLMENT_NUMBER", unique = true, nullable = false)
    private String enrollmentNumber;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    private Course course;
    @Column(name = "SEMESTER", nullable = false)
    private String semester;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private StudentStatus status;
    @Column(name = "CPF", unique = true)
    private String cpf;
    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber;
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    protected Student() {}

    private Student(Builder builder) {
        super();
        this.enrollmentNumber = builder.enrollmentNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.course = builder.course;
        this.semester = builder.semester;
        this.status = builder.status;
        this.cpf = builder.cpf;
        this.phoneNumber = builder.phoneNumber;
        this.dateOfBirth = builder.dateOfBirth;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String enrollmentNumber;
        private String firstName;
        private String lastName;
        private String email;
        private Course course;
        private String semester;
        private StudentStatus status;
        private String cpf;
        private String phoneNumber;
        private LocalDate dateOfBirth;

        public Builder enrollmentNumber(String enrollmentNumber) {
            this.enrollmentNumber = enrollmentNumber;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder course(Course course) {
            this.course = course;
            return this;
        }

        public Builder semester(String semester) {
            this.semester = semester;
            return this;
        }

        public Builder status(StudentStatus status) {
            this.status = status;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "enrollmentNumber='" + enrollmentNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", course=" + course +
                ", semester='" + semester + '\'' +
                ", status=" + status +
                ", cpf='" + cpf + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
