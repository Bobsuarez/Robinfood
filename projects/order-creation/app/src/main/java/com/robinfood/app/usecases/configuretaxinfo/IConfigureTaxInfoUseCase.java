package com.robinfood.app.usecases.configuretaxinfo;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;

public interface IConfigureTaxInfoUseCase {

    /**
     * Set taxes values to be send to order-bc
     * @param transactionRequestDTO transaction request
     */
    void invoke (TransactionRequestDTO transactionRequestDTO);
}
