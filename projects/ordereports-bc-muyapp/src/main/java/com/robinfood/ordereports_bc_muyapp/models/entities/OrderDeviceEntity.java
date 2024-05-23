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
@Data
@Builder
@Entity
@NoArgsConstructor
@Table(name = "order_devices")
public class OrderDeviceEntity {

    private LocalDateTime createdAt;

    @Id
    private Long id;

    private String ip;

    private Integer orderId;

    private Short platformId;

    private String version;
}
