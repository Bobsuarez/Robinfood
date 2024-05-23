package com.robinfood.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;

    private  Long transactionId;

    private  Double value;

    private  Long paymentMethodId;

    private  Long originId;

}
