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
@Table(name = "order_type_deductions")
public class OrderTypeDeductionsEntity {
    private LocalDateTime createdAt;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private Long status;
}
