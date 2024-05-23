package com.robinfood.paymentmethodsbc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "succeeded")
    private boolean succeeded;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_status_id", nullable = false)
    private TransactionStatus transactionStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_gateway_id", nullable = false)
    private PaymentGateway paymentGateway;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private GeneralPaymentMethod paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private com.robinfood.paymentmethodsbc.model.Entity entity;

    @Column(name = "entity_source_id")
    private Long entitySource;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "authorization_code")
    private String authorizationCode;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "tax")
    private BigDecimal tax;

    @Column(name = "total")
    private BigDecimal total;

    @Column(
        name = "created_at",
        nullable = false,
        updatable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @JsonManagedReference
    private TransactionDetail transactionDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private TransactionUser transactionUser;

    public Transaction(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return new StringBuilder()
            .append("Transaction(")
            .append("id=")
            .append(id)
            .append(", uuid=")
            .append(uuid)
            .append(", succeeded=")
            .append(succeeded)
            .append(", transactionStatusId=")
            .append(transactionStatus)
            .append(", platformId=")
            .append(platform)
            .append(", paymentGatewayId=")
            .append(paymentGateway)
            .append(", creditCardId=")
            .append(creditCard)
            .append(", countryId=")
            .append(country)
            .append(", entityId=")
            .append(entity)
            .append(", entitySourceId=")
            .append(entitySource)
            .append(", userId=")
            .append(userId)
            .append(", authorizationCode=")
            .append(authorizationCode)
            .append(", subtotal=")
            .append(subtotal)
            .append(", tax=")
            .append(tax)
            .append(", total=")
            .append(total)
            .append(")")
            .toString();
    }
}
