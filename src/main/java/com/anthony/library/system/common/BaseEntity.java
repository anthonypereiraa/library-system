package com.anthony.library.system.common;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = UUID)
    private String id;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createBy;
    @LastModifiedBy
    @Column(insertable = false)
    private String lastModifiedBy;

    public BaseEntity() {
    }

    public BaseEntity(LocalDateTime createdDate,
                      LocalDateTime lastModifiedDate,
                      String createBy,
                      String lastModifiedBy) {
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.createBy = createBy;
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
}
