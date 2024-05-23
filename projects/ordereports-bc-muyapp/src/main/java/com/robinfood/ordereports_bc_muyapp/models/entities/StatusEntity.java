package com.robinfood.ordereports_bc_muyapp.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "order_status")
public class StatusEntity {

    private LocalDateTime createdAt;

    private String code;

    @Id
    private Short id;

    private String name;

    private BigDecimal order;

    private LocalDateTime updatedAt;

    @Column(name = "parent_id")
    private Short parentId;
}
