package com.robinfood.paymentmethodsbc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        name = "CountryPaymentGatewaySettingGraph",
        attributeNodes = {
                @NamedAttributeNode("country"),
                @NamedAttributeNode("paymentGateway")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "country_payment_gateway_settings")
public class CountryPaymentGatewaySetting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_gateway_id", nullable = false)
    private PaymentGateway paymentGateway;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @JsonIgnore
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    public CountryPaymentGatewaySetting(Long id) {
        this.id = id;
    }
}
