package com.robinfood.core.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.PrePersist;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_changed_portions")
public class OrderChangedPortionEntity {

    @NonNull
    private Long changedPortionId;

    @NonNull
    private String changedPortionName;

    @NonNull
    private Long changedProductId;

    @NonNull
    private String changedProductName;

    private LocalDateTime createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long orderFinalProductPortionId;

    private Long originalPortionId;

    private String originalPortionName;

    private Long originalProductId;

    private String originalProductName;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}
