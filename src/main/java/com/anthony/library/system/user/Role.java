package com.anthony.library.system.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.anthony.library.system.user.Permission.*;

public enum Role {
    USER(
            Set.of(
                    BOOK_READ,
                    COURSE_READ,
                    LOAN_READ,
                    STUDENT_READ
            )
    ),
    ADMIN(
            Set.of(
                    BOOK_CREATE,
                    BOOK_READ,
                    BOOK_UPDATE,
                    BOOK_DELETE,
                    COURSE_CREATE,
                    COURSE_READ,
                    COURSE_UPDATE,
                    COURSE_DELETE,
                    LOAN_CREATE,
                    LOAN_READ,
                    LOAN_UPDATE,
                    LOAN_DELETE,
                    STUDENT_CREATE,
                    STUDENT_READ,
                    STUDENT_UPDATE,
                    STUDENT_DELETE
            )
    ),
    MANAGER(
            Set.of(
                    BOOK_CREATE,
                    BOOK_READ,
                    BOOK_UPDATE,
                    COURSE_CREATE,
                    COURSE_READ,
                    COURSE_UPDATE,
                    LOAN_CREATE,
                    LOAN_READ,
                    LOAN_UPDATE,
                    STUDENT_CREATE,
                    STUDENT_READ,
                    STUDENT_UPDATE
            )
    )

    ;

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission ->
                        new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
