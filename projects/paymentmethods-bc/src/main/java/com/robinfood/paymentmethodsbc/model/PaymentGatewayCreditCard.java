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
@NamedEntityGraph(
    name = "PaymentGatewayCreditCardGraph",
    attributeNodes = {
        @NamedAttributeNode("paymentGateway"), @NamedAttributeNode("creditCard")
    }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payment_gateway_credit_cards")
public class PaymentGatewayCreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_gateway_id", nullable = false)
    private PaymentGateway paymentGateway;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;

    @Column(name = "token")
    private String token;

    @Column(
        name = "created_at",
        nullable = false,
        updatable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(
        name = "deleted_at",
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime deletedAt;

    public PaymentGatewayCreditCard(Long id) {
        this.id = id;
    }
}
