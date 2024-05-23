package com.robinfood.changestatusbc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_status")
public class StatusEntity {

    private LocalDateTime createdAt;

    private String code;

    @Id
    private Long id;

    private String name;

    private BigDecimal order;

    private LocalDateTime updatedAt;


    @Column(name = "parent_id")
    private Long parentId;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}
