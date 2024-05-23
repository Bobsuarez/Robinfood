package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
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
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_devices")
public class OrderDeviceEntity {

    private LocalDateTime createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ip;

    private Long orderId;

    private Long platformId;

    private String version;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}
