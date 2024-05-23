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
        name = "CountryPaymentGatewayGraph",
        attributeNodes = {
                @NamedAttributeNode("platform"),
                @NamedAttributeNode("country"),
                @NamedAttributeNode("paymentGateway")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "country_payment_gateways")
public class CountryPaymentGateway {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_gateway_id", nullable = false)
    private PaymentGateway paymentGateway;

    @Column(name = "credit_card_tokenization_enabled")
    private Boolean creditCardTokenizationEnabled;

    @Column(name = "priority")
    private Long priority;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    public CountryPaymentGateway(Long id) {
        this.id = id;
    }
}
