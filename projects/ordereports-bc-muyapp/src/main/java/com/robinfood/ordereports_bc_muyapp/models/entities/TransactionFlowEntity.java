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
@Builder
@Entity
@NoArgsConstructor
@Table(name = "transactions_flow")
public class TransactionFlowEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Id
    @Column(unique = true, nullable = false)
    private Long id;

    private Short flowId;

    private Long transactionId;

    private LocalDateTime updatedAt;

}
