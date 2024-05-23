package com.robinfood.paymentmethodsbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transaction_details")
public class TransactionDetail {
    @Id
    private Long transactionId;

    @Column(name = "transaction_reference")
    private String transactionReference;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "origin_platform_id")
    private Long originPlatformId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "entity_reference")
    private String entityReference;

    @Column(name = "dataphone_code")
    private String dataphoneCode;

    @Column(name = "credit_card_masked_number")
    private String creditCardMaskedNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "franchise")
    private String franchise;

    @Column(name = "dataphone_receipt_number")
    private String dataphoneReceiptNumber;

    @Column(name = "installments")
    private Integer installments;

    @Column(name = "establishment_code")
    private String establishmentCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
    private Terminal terminal;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_id")
    @JsonBackReference
    private Transaction transaction;
}
