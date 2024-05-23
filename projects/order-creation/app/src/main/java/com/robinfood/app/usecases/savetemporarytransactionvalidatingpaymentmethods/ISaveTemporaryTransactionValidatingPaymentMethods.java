package com.robinfood.app.usecases.savetemporarytransactionvalidatingpaymentmethods;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import org.jetbrains.annotations.NotNull;

public interface ISaveTemporaryTransactionValidatingPaymentMethods {

    void invoke(@NotNull TransactionRequestDTO transactionRequest);
}
