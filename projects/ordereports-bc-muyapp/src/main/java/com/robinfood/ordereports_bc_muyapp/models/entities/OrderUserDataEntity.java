package com.robinfood.ordereports_bc_muyapp.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_user_data")
public class OrderUserDataEntity {

    private LocalDateTime createdAt;

    private String email;

    private String firstName;

    @Id
    private Long id;

    private String lastName;

    private String mobile;

    private Long orderId;

    private Long storeId;

    private Integer userId;
}
