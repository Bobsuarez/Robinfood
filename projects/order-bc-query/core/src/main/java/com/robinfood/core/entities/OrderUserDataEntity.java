package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.PrePersist;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

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

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    private String lastName;

    private String mobile;

    private Long orderId;

    private Long storeId;

    private Long userId;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}
