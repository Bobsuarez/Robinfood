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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_method_store_flow_channels")
public class PaymentMethodStoreFlowChannel {
    public static final Boolean STATUS_ENABLED = true;
    public static final Boolean STATUS_DISABLED = false;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "general_payment_method_id", nullable = false)
    private GeneralPaymentMethod generalPaymentMethod;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "channel_id")
    private Long channelId;

    @Column(name = "flow_id")
    private Long flowId;

    @Column(name = "position")
    private Long position;

    @Column(name = "status")
    private boolean status;

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

    public PaymentMethodStoreFlowChannel(Long id) {
        this.id = id;
    }
}
