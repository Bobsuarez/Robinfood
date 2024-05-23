package com.robinfood.app.usecases.validatepaymentmethodpaid;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.NonNull;

public interface IValidatePaymentMethodPaidUseCase {

    void invoke(@NonNull String token, @NonNull TransactionRequestDTO transactionRequestDTO);
}
