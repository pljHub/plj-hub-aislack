package com.plj.hub.ai_slack.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Audit {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Column(nullable = true)
    private Long createdBy;

    private Long updatedBy;

    private Long deletedBy;

    private Boolean isDeleted = false;

    protected void create(Long userId) {
        createdBy = userId;
    }

    protected void delete(Long userId) {
        deletedAt = LocalDateTime.now();
        deletedBy = userId;
    }
}
