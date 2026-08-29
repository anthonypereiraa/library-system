package com.anthony.library.system.user;

public enum Permission {
    BOOK_CREATE("book:create"),
    BOOK_READ("book:read"),
    BOOK_UPDATE("book:update"),
    BOOK_DELETE("book:delete"),
    COURSE_CREATE("course:create"),
    COURSE_READ("course:read"),
    COURSE_UPDATE("course:update"),
    COURSE_DELETE("course:delete"),
    LOAN_CREATE("loan:create"),
    LOAN_READ("loan:read"),
    LOAN_UPDATE("loan:update"),
    LOAN_DELETE("loan:delete"),
    STUDENT_CREATE("student:create"),
    STUDENT_READ("student:read"),
    STUDENT_UPDATE("student:update"),
    STUDENT_DELETE("student:delete")

    ;

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
