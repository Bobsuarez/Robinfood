package com.robinfood.app.usecases.validateuuid

import com.robinfood.core.dtos.transactionrequest.TransactionRequestDTO

interface IValidateUuidUseCase {

    /**
     * Validate origin. If the id is 6,7,8,11 it should be changed to the value of 8 in the payment-method
     * @param transactionRequestDTO the json body request with the transaction info
     * @return the object with the correct id
     */
    fun invoke(transactionRequestDTO: TransactionRequestDTO): TransactionRequestDTO
}