package com.robinfood.core.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
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
@Table(name = "order_removed_portions")
public class FinalProductRemovedPortionEntity {

    private LocalDateTime createdAt;

    @NonNull
    private Long finalProductId;

    @NonNull
    private Long groupId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private Long orderId;

    @NonNull
    private Long orderFinalProductId;

    @NonNull
    private Long portionId;

    @NonNull
    private String portionName;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}
