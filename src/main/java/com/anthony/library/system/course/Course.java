package com.anthony.library.system.course;

import com.anthony.library.system.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "COURSES")
public class Course extends BaseEntity {
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    protected Course() {
    }

    private Course(Builder builder) {
        super();;
        this.name = builder.name;
        this.code = builder.code;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String code;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
