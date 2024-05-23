package com.robinfood.app.usecases.issueelectronicbilling;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.NonNull;

public interface IIssueElectronicBillingUseCase {

    /**
     * use case in charge of sending the order to the electronic billing provider.
     *
     * @param token the authorization token
     * @param transactionRequest transaction request created
     */
    void invoke(@NonNull String token,
                @NonNull TransactionRequestDTO transactionRequest);
}
