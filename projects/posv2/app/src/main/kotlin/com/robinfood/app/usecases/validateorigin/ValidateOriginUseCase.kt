package com.robinfood.app.usecases.validateorigin

import com.robinfood.core.constants.GlobalConstants.UBEREATS
import com.robinfood.core.constants.GlobalConstants.RAPPI
import com.robinfood.core.constants.GlobalConstants.IFOOD
import com.robinfood.core.constants.GlobalConstants.DIDI
import com.robinfood.core.constants.GlobalConstants.TUORDEN
import com.robinfood.core.constants.GlobalConstants.INTEGRATIONS
import com.robinfood.core.dtos.transactionrequest.TransactionRequestDTO

class ValidateOriginUseCase() : IValidateOriginUseCase {

    override fun invoke(
            transactionRequestDTO: TransactionRequestDTO
    ): TransactionRequestDTO {

        val origin = transactionRequestDTO.origin

        if (validateOrigin(origin.id)) {
            for (paymentMethod in transactionRequestDTO.paymentMethods) {
                paymentMethod.id = INTEGRATIONS
            }
        }
        return transactionRequestDTO
    }

    private fun validateOrigin(id: Long): Boolean {

        val originList = listOf(UBEREATS, RAPPI, IFOOD, DIDI, TUORDEN)
        return originList.contains(id);
    }
}