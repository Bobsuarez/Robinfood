package com.robinfood.paymentmethodsbc.enums;

import com.robinfood.paymentmethodsbc.services.steps.StepActions;
import com.robinfood.paymentmethodsbc.services.steps.common.GetCountryStep;
import com.robinfood.paymentmethodsbc.services.steps.common.GetCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.common.GetEntityStep;
import com.robinfood.paymentmethodsbc.services.steps.common.GetPaymentGatewayCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.common.GetSettingsPaymentGatewayStep;
import com.robinfood.paymentmethodsbc.services.steps.creditcards.BuildUpdateCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.creditcards.CreateCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.creditcards.DeleteCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.creditcards.GetDecryptedCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.creditcards.GetEncryptedCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.creditcards.RemoveTokenCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.creditcards.TokenizeCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.creditcards.UpdateCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.creditcards.UpdateTokenizeCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.creditcards.UserListCreditCardStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.CancelPreviousTransactionStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.GetDataphoneInfoStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.GetDataphoneSettingsStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.GetTransactionStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.InitialTransactionStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.JmsSendGenerateTransactionStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.JmsSendStatusTransactionStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.PaymentStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.RefundStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.TransactionNoValidationStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.UpdateTransactionStatusStep;
import com.robinfood.paymentmethodsbc.services.steps.transactions.ValidatePaymentTotalsStep;
import lombok.Getter;

@Getter
public enum StepType {
    // Common

    GET_COUNTRY(GetCountryStep.class),
    GET_ENTITY(GetEntityStep.class),
    GET_PAYMENT_GATEWAY_CREDIT_CARD(GetPaymentGatewayCreditCardStep.class),
    GET_CREDIT_CARD(GetCreditCardStep.class),
    GET_TRANSACTION(GetTransactionStep.class),
    GET_SETTINGS_PAYMENT_GATEWAY(GetSettingsPaymentGatewayStep.class),
    // transactions
    VALIDATE_TOTALS(ValidatePaymentTotalsStep.class),
    INITIAL_TRANSACTION(InitialTransactionStep.class),
    GENERATE_PAYMENT(PaymentStep.class),
    GENERATE_PAYMENT_NO_VALIDATION(TransactionNoValidationStep.class),
    UPDATE_STATUS_TRANSACTION(UpdateTransactionStatusStep.class),
    GENERATE_REFUND(RefundStep.class),
    JMS_SEND_GENERATE_TRANSACTION(JmsSendGenerateTransactionStep.class),
    JMS_SEND_STATUS_TRANSACTION(JmsSendStatusTransactionStep.class),
    JMS_UPDATE_STATUS_TRANSACTION(UpdateTransactionStatusStep.class),
    // credit cards
    CREATE_CREDIT_CARD(CreateCreditCardStep.class),
    TOKENIZE_CREDIT_CARD(TokenizeCreditCardStep.class),
    REMOVE_TOKEN_CREDIT_CARD(RemoveTokenCreditCardStep.class),
    DELETE_CREDIT_CARD(DeleteCreditCardStep.class),
    USER_LIST_CREDIT_CARD(UserListCreditCardStep.class),
    GET_ENCRYPTED_CREDIT_CARD(GetEncryptedCreditCardStep.class),
    BUILD_UPDATE_CREDIT_CARD(BuildUpdateCreditCardStep.class),
    UPDATE_CREDIT_CARD(UpdateCreditCardStep.class),
    UPDATE_TOKENIZE_CREDIT_CARD(UpdateTokenizeCreditCardStep.class),
    GET_DECRYPTED_CREDIT_CARD(GetDecryptedCreditCardStep.class),
    //dataphones
    GET_DATAPHONE_INFO(GetDataphoneInfoStep.class),
    GET_DATAPHONE_SETTINGS(GetDataphoneSettingsStep.class),
    CANCEL_PREVIOUS_TRANSACTION(CancelPreviousTransactionStep.class);

    private final Class<? extends StepActions> classImplement;

    StepType(Class<? extends StepActions> classImplement) {
        this.classImplement = classImplement;
    }
}
