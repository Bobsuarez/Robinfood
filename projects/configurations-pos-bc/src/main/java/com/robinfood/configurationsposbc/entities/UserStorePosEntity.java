package com.robinfood.configurationsposbc.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Table(name = "user_store_pos")
@IdClass(UserStorePosId.class)
public class UserStorePosEntity implements Serializable {

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    @Id
    private Long posId;

    @Id
    private Long storeId;

    private LocalDateTime updatedAt;

    @Id
    private Long userId;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
