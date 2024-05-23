package com.robinfood.paymentmethodsbc.utils.nullsafety;

import com.robinfood.paymentmethodsbc.model.Country;
import com.robinfood.paymentmethodsbc.model.GeneralPaymentMethod;
import com.robinfood.paymentmethodsbc.model.PaymentGateway;
import com.robinfood.paymentmethodsbc.model.Platform;
import com.robinfood.paymentmethodsbc.model.Terminal;
import com.robinfood.paymentmethodsbc.model.Transaction;
import com.robinfood.paymentmethodsbc.model.TransactionDetail;
import com.robinfood.paymentmethodsbc.model.TransactionUser;

import static java.util.Objects.requireNonNullElse;

public final class ModelsNullSafety {

    private ModelsNullSafety() {}

    public static PaymentGateway validatePaymentGateway(PaymentGateway paymentGateway) {
        return requireNonNullElse(paymentGateway, PaymentGateway.builder().build());
    }

    public static GeneralPaymentMethod validateGeneralPaymentMethod(GeneralPaymentMethod generalPaymentMethod) {
        return requireNonNullElse(generalPaymentMethod, GeneralPaymentMethod.builder().build());
    }

    public static Country validateCountry(Country country) {
        return requireNonNullElse(country, Country.builder().build());
    }

    public static Transaction validateTransaction(Transaction transaction) {
        return requireNonNullElse(transaction, Transaction.builder().build());
    }

    public static TransactionDetail validateTransactionDetail(TransactionDetail transactionDetail) {
        return requireNonNullElse(transactionDetail, TransactionDetail.builder().build());
    }

    public static Terminal validateTerminal(Terminal terminal) {
        return requireNonNullElse(terminal, Terminal.builder().build());
    }

    public static TransactionUser validateTransactionUser(TransactionUser transactionUser) {
        return requireNonNullElse(transactionUser, TransactionUser.builder().build());
    }

    public static Platform validatePlatform(Platform platform) {
        return requireNonNullElse(platform, Platform.builder().build());
    }
}
