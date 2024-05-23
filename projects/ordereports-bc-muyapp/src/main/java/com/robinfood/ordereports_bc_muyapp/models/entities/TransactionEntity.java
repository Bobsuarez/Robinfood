package com.robinfood.ordereports_bc_muyapp.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "transactions")
public class TransactionEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Id
    @Column(unique = true, nullable = false)
    private Integer id;

    private Boolean paid;

    @Column(name = "uuid")
    private String uniqueIdentifier;

    private LocalDateTime updatedAt;

    private Long userId;

    private Double value;
}
