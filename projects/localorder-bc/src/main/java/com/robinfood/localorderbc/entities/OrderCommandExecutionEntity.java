package com.robinfood.localorderbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Data
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Table(name = "order_command_execution")
@IdClass(OrderCommandExecutionId.class)
public class OrderCommandExecutionEntity implements Serializable {

    @Id
    @Column(name = "command_id", nullable = false)
    private Long commandId;

    private String commandName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private Integer currentResponseStatusCode;

    private LocalDateTime deletedAt;

    private LocalDateTime executionEndedAt;

    private LocalDateTime executionStartedAt;

    @Id
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    private Boolean reprocess;

    private Integer reprocessAttempt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onInsert() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
        updatedAt = createdAt;

        if (reprocessAttempt == null){
            reprocessAttempt = 0;
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now(ZoneOffset.UTC);
        System.out.println("ingrsé por aca aaaa " + reprocessAttempt);
    }

    @PreRemove
    public void onDelete() {
        deletedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

}
