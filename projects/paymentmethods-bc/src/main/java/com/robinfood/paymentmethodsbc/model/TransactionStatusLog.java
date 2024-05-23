package com.robinfood.paymentmethodsbc.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedEntityGraph(
    name = "TransactionStatusLogGraph",
    attributeNodes = {
        @NamedAttributeNode("transaction"),
        @NamedAttributeNode("transactionStatus")
    }
)
@Table(name = "transaction_status_logs")
public class TransactionStatusLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_status_id", nullable = false)
    private TransactionStatus transactionStatus;

    @Column(name = "transaction_reference")
    private String transactionReference;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "authorization_code")
    private String authorizationCode;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(
        name = "created_at",
        nullable = false,
        updatable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    public TransactionStatusLog(Long id) {
        this.id = id;
    }
}
