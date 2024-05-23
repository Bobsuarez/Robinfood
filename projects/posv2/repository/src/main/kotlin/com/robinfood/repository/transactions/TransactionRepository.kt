package com.robinfood.repository.transactions

import com.robinfood.core.dtos.transactionresponse.TransactionCreatedResponseDTO
import com.robinfood.core.entities.transactionrequest.TransactionRequestEntity
import com.robinfood.core.enums.Result
import com.robinfood.core.exceptions.OrchestratorException
import com.robinfood.core.exceptions.TransactionCreationException
import com.robinfood.core.extensions.callServices
import com.robinfood.core.extensions.safeApiCall
import com.robinfood.core.mappers.toTransactionCreatedResponseDTO
import com.robinfood.network.api.OrderCreationAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import org.springframework.web.server.ResponseStatusException

@Repository
class TransactionRepository(
    private val orderCreationAPI: OrderCreationAPI
): ITransactionRepository {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override suspend fun createTransaction(token: String, transactionRequestEntity: TransactionRequestEntity
    ): Result<TransactionCreatedResponseDTO> {

        log.info("Execute transaction repository create transaction{} ", transactionRequestEntity)

        return withContext(Dispatchers.IO) {
            val result = safeApiCall(
                call = {
                    orderCreationAPI.createTransaction(token, transactionRequestEntity).callServices()
                }
            )
            when (result) {
                is Result.Success -> {
                    val transactionCreatedResponseEntity = result.data.data
                    if (transactionCreatedResponseEntity == null) {
                        Result.Error(
                            OrchestratorException("Orders array is null"),
                            HttpStatus.INTERNAL_SERVER_ERROR
                        )
                    } else {
                        val transactionCreatedResponseDTO = transactionCreatedResponseEntity.toTransactionCreatedResponseDTO()
                        Result.Success(transactionCreatedResponseDTO)
                    }
                }
                is Result.Error -> {
                    try {
                        throw TransactionCreationException(
                                (result.exception as OrchestratorException).data,
                                result.exception.localizedMessage,
                                result.httpStatus
                        )
                    } catch (transactionCreationException: TransactionCreationException) {
                        throw transactionCreationException;
                    } catch (exception: Exception) {
                        throw ResponseStatusException(result.httpStatus, result.exception.localizedMessage)
                    }
                }
            }
        }
    }
}