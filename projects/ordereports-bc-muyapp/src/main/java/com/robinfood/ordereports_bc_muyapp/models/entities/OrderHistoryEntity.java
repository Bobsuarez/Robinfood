package com.robinfood.ordereports_bc_muyapp.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Builder
@Entity
@NoArgsConstructor
@Table(name = "order_history")
public class OrderHistoryEntity {

    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime date;

    @Id
    private Long id;

    private String observation;

    private Integer orderId;

    private Short orderStatusId;

    private Long userId;

}
