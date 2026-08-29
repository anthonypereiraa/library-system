package com.anthony.library.system.course;

import com.anthony.library.system.course.request.CourseRequest;
import com.anthony.library.system.course.request.CourseUpdateRequest;
import com.anthony.library.system.course.response.CourseResponse;
import com.anthony.library.system.course.response.CourseResponseMapper;
import com.anthony.library.system.exception.BusinessException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.anthony.library.system.exception.ErrorCode.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseResponseMapper courseResponseMapper;

    public CourseService(final CourseRepository courseRepository,
                         CourseResponseMapper courseResponseMapper) {
        this.courseRepository = courseRepository;
        this.courseResponseMapper = courseResponseMapper;
    }

    public void addNewCourse(final CourseRequest request) {
        checkIfCourseAlreadyExists(request.code());
        var course = Course.builder()
                .name(request.name())
                .code(request.code())
                .build();
        courseRepository.save(course);
    }

    public List<CourseResponse> getCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseResponseMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateCourse(final String courseId, final CourseUpdateRequest request) {
        final var course = checkAndReturnCourse(courseId);
        if (request.name() != null && !request.name().isEmpty()) {
            updateName(course, request.name());
        }
        if (request.code() != null && !request.code().isEmpty()) {
            updateCode(course, request.code());
        }
    }

    @Transactional
    public void deleteCourse(final String courseId) {
        final var course = checkAndReturnCourse(courseId);
        courseRepository.delete(course);
    }

    private void checkIfCourseAlreadyExists(final String code) {
        final Optional<Course> codeOptional = courseRepository.findByCode(code);
        if (codeOptional.isPresent()) {
            throw new BusinessException(COURSE_ALREADY_EXISTS);
        }
    }

    private Course checkAndReturnCourse(final String courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new BusinessException(COURSE_NOT_FOUND));
    }

    private void updateName(final Course course, final String name) {
        if (course.getName().equals(name)) {
            return;
        }
        course.setName(name);
    }

    private void updateCode(final Course course, final String code) {
        checkIfCourseAlreadyExists(code);
        if (course.getCode().equals(code)) {
            return;
        }
        course.setCode(code);
    }
}
