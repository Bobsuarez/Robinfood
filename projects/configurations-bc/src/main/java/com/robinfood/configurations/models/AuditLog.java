package com.robinfood.configurations.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Builder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "audit_logs", schema = "menu")
public class AuditLog {

    private static final long serialVersionUID = 8505139494494418865L;

    public static final String ACTION_CREATE = "CREATE";
    public static final String ACTION_UPDATE = "UPDATE";
    public static final String ACTION_DELETE = "DELETE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "action")
    private String action;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "model_id")
    private Long modelId;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "modification")
    private String modification;

    public AuditLog(Long id,
        LocalDateTime createdAt,
        String action,
        Long userId,
        Long modelId,
        String modelName,
        String modification) {
        this.id = id;
        this.createdAt = createdAt;
        this.action = action;
        this.userId = userId;
        this.modelId = modelId;
        this.modelName = modelName;
        this.modification = modification;
    }
}
