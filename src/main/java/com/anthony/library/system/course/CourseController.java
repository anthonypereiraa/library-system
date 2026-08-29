package com.anthony.library.system.course;

import com.anthony.library.system.course.request.CourseRequest;
import com.anthony.library.system.course.request.CourseUpdateRequest;
import com.anthony.library.system.course.response.CourseResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('course:create')")
    public ResponseEntity<Void> registerNewCourse(
            @Valid @RequestBody CourseRequest request) {
        courseService.addNewCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('course:read')")
    public ResponseEntity<List<CourseResponse>> getCourses() {
        return ResponseEntity.ok(courseService.getCourses());
    }

    @PatchMapping("/{courseId}")
    @PreAuthorize("hasAuthority('course:update')")
    public ResponseEntity<Void> updateCourse(
            @PathVariable String courseId,
            @RequestBody CourseUpdateRequest request) {
        courseService.updateCourse(courseId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAuthority('course:delete')")
    public ResponseEntity<Void> deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
