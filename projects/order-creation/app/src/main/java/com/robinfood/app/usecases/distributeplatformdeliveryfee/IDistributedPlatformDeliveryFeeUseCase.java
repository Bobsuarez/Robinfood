package com.robinfood.app.usecases.distributeplatformdeliveryfee;


import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;


public interface IDistributedPlatformDeliveryFeeUseCase {

    /**
     * Distributes plataform or delivery fee by percentage
     *
     * @param transactionRequestDTO transaction request
     * @param token                 discount total
     */
    void invoke(
            TransactionRequestDTO transactionRequestDTO,
            String token
    );
}
