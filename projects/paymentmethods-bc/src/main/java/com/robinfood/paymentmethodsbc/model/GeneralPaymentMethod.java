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
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NamedEntityGraph(
    name = "GeneralPaymentMethodsGraph",
    attributeNodes = { @NamedAttributeNode("paymentGateway") }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "general_payment_methods")
public class GeneralPaymentMethod {
    public static final Boolean STATUS_ENABLED = true;
    public static final Boolean STATUS_DISABLED = false;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug_name", nullable = false)
    private String slugName;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_gateway_id", nullable = false)
    private PaymentGateway paymentGateway;

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

    public GeneralPaymentMethod(Long id) {
        this.id = id;
    }
}
