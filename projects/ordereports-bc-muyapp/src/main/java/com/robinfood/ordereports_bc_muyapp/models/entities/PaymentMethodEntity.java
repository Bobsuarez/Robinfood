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
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "payment_methods")
public class PaymentMethodEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Short id;

    @Column(name = "type_payment_method_id", nullable = false)
    private Short typePaymentMethodId;

    @Column(name = "order_flow_print_id", nullable = false)
    private Short orderFlowPrintId;

    @Column(name = "status_id", nullable = false)
    private Short statusId;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "name_short", nullable = false, length = 45)
    private String nameShort;

    @Column(name = "icon", length = 45)
    private String icon;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
