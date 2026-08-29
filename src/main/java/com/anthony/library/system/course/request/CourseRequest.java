package com.anthony.library.system.course.request;

import jakarta.validation.constraints.NotBlank;

public record CourseRequest(
        @NotBlank
        String name,
        @NotBlank
        String code) {}
