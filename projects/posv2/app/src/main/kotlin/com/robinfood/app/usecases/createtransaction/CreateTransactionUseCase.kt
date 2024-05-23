package com.robinfood.app.usecases.createtransaction

import com.robinfood.app.usecases.validateorigin.IValidateOriginUseCase
import com.robinfood.app.usecases.validateuuid.IValidateUuidUseCase
import com.robinfood.core.dtos.transactionrequest.TransactionRequestDTO
import com.robinfood.core.dtos.transactionresponse.TransactionCreatedResponseDTO
import com.robinfood.core.enums.Result
import com.robinfood.core.mappers.toTransactionRequestEntity
import com.robinfood.repository.transactions.ITransactionRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Implementation of ICreateTransactionUseCase
 */
class CreateTransactionUseCase(
        private val transactionRepository: ITransactionRepository,
        private val validateOriginUseCase: IValidateOriginUseCase,
        private val validateUuidUseCase: IValidateUuidUseCase
) : ICreateTransactionUseCase {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun invoke(
            token: String,
            transactionRequestDTO: TransactionRequestDTO
    ): Result<TransactionCreatedResponseDTO> {

        log.info("Execute create transaction use case {}", transactionRequestDTO)

        val transactionRequest = validateOriginUseCase.invoke(transactionRequestDTO)

        val transactionRequestVerify = validateUuidUseCase.invoke(transactionRequest)

        return transactionRepository.createTransaction(
                token,
                transactionRequestVerify.toTransactionRequestEntity()
        )
    }
}
