package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "payment_methods")
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type_payment_method_id", nullable = false)
    private Integer typePaymentMethodId;

    @Column(name = "order_flow_print_id", nullable = false)
    private Integer orderFlowPrintId;

    @Column(name = "status_id", nullable = false)
    private Integer statusId;

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
