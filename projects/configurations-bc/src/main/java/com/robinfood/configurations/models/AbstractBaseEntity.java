package com.robinfood.configurations.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuperBuilder(toBuilder = true)
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 8505139494494418865L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", updatable = false, nullable = false)
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP", nullable = false)
    protected LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    protected LocalDateTime deletedAt;

    protected AbstractBaseEntity() {

    }

    protected AbstractBaseEntity(Long id) {
        this.id = id;
    }
}