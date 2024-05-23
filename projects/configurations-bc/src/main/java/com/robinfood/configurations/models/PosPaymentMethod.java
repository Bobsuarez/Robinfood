package com.robinfood.configurations.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.configurations.serializers.PosPaymentMethodSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pos_payment_methods")
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = PosPaymentMethodSerializer.class)
public class PosPaymentMethod extends AbstractBaseEntity {

    private static final long serialVersionUID = -4098609110121444302L;

    @Column(name = "payment_method_id", nullable = false)
    private Long paymentMethodId;

    @ManyToOne
    @JoinColumn(name = "pos_id", nullable = false)
    private Pos pos;

}
