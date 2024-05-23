package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "transactions_flow")
public class TransactionFlowEntity {

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

    private Long flowId;

    private Long transactionId;

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

    public TransactionFlowEntity(Long transactionId, Long flowId){
        this.transactionId = transactionId;
        this.flowId = flowId;
    }

}
