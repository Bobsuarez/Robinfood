package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Builder
@Table(name = "order_deductions")
public class OrderDeductionEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Id
    @GenericGenerator(
            name = "UseExistingIdOtherwiseGenerateUsingIdentity",
            strategy = "com.robinfood.core.helpers.UseExistingIdOtherwiseGenerateUsingIdentity"
    )
    @GeneratedValue(generator = "UseExistingIdOtherwiseGenerateUsingIdentity")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "value")
    private BigDecimal value;

    private LocalDateTime updatedAt;

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
