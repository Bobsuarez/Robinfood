package com.robinfood.core.entities;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String observation;

    private Long orderId;

    private Long orderStatusId;

    private Long userId;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}
