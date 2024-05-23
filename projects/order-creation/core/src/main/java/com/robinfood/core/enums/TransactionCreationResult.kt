package com.robinfood.core.enums

import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO

sealed class TransactionCreationResult {

    /** -----------------------------------------ERROR RESPONSES------------------------------------ */

    data class GenericTransactionCreationError(val exception: Throwable): TransactionCreationResult()
    data class TransactionCreationError(val exception: Throwable): TransactionCreationResult()
    data class StepValidationError(val exception: Throwable): TransactionCreationResult()

    /** -----------------------------------------SUCCESS RESPONSES------------------------------------ */

    data class TransactionCreated(val data: TransactionCreationResponseDTO): TransactionCreationResult()
    object StepValidationSuccess: TransactionCreationResult()
}