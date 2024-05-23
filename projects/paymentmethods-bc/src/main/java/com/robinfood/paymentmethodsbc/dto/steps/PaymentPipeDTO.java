package com.robinfood.paymentmethodsbc.dto.steps;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.model.Country;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.Entity;
import com.robinfood.paymentmethodsbc.model.GeneralPaymentMethod;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.model.Terminal;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPipeDTO {

    private PaymentRequestDTO transactionRequestDTO;
    private PaymentGateway paymentGateway;
    private PaymentGatewayCreditCard paymentGatewayCreditCard;
    private GeneralPaymentMethod paymentMethod;
    private CreditCard creditCard;
    private Country country;
    private Entity entity;
    private Transaction transaction;
    private TransactionDetail transactionDetail;
    private Terminal terminal;
    private SettingsDTO settings;
    private boolean notifyStatus;

    public PaymentPipeDTO(PaymentRequestDTO transactionRequestDTO) {
        this.transactionRequestDTO = transactionRequestDTO;
    }
}
