package com.anthony.library.system.course.response;

import com.anthony.library.system.course.Course;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CourseResponseMapper implements Function<Course, CourseResponse> {

    @Override
    public CourseResponse apply(Course course) {
        return new CourseResponse(
                course.getName(),
                course.getCode());
    }
}
