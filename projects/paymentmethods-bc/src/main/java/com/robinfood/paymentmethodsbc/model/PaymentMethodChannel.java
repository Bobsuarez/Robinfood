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
    name = "PaymentMethodChannelsGraph",
    attributeNodes = { @NamedAttributeNode("paymentMethod") }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payment_method_channels")
public class PaymentMethodChannel {
    public static final Boolean STATUS_ENABLED = true;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "channel_id")
    private Long channelId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "muy_payment_method_id")
    private Long muyPaymentMethodId;

    @Column(name = "origin_id")
    private Long originId;

    @Column(name = "status")
    private Boolean status;

    @Column(
        name = "created_at",
        nullable = false,
        updatable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime deletedAt;

    public PaymentMethodChannel(Long id) {
        this.id = id;
    }
}
