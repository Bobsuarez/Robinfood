package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "transactions")
public class TransactionEntity {

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

    private Boolean paid;

    @Column(name = "uuid")
    private String uniqueIdentifier;

    private LocalDateTime updatedAt;

    private Long userId;

    private Double value;

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
