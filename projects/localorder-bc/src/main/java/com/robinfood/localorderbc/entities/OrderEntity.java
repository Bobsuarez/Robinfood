package com.robinfood.localorderbc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Data
@Builder
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    private Long brandId;

    private String brandName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private String data;

    private LocalDateTime deletedAt;

    @Id
    @Column(unique = true, nullable = false)
    private Long id;

    private Long originId;

    private String originName;

    private Boolean printed;

    private Long statusId;

    private String statusName;

    private LocalDateTime updatedAt;

    private String uid;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreRemove
    public void onDelete() {
        deletedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

}
