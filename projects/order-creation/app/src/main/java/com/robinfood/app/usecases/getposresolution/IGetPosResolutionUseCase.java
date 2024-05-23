package com.robinfood.app.usecases.getposresolution;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.NonNull;

public interface IGetPosResolutionUseCase {

    /**
     * use case in charge of the resolution by id pos
     *
     * @param token              the authorization token
     * @param transactionRequest transaction request created
     */
    void invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequest
    );
}
