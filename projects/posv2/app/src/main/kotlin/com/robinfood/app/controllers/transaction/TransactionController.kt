package com.robinfood.app.controllers.transaction

import com.robinfood.app.usecases.createtransaction.ICreateTransactionUseCase
import com.robinfood.core.constants.APIConstants.API_V1
import com.robinfood.core.constants.APIConstants.TRANSACTION
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.transactionrequest.TransactionRequestDTO
import com.robinfood.core.dtos.transactionresponse.TransactionCreatedResponseDTO
import com.robinfood.core.enums.Result
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping(API_V1 + TRANSACTION)
class TransactionController(
    private val createTransactionUseCase: ICreateTransactionUseCase
): ITransactionController {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    override suspend fun createTransaction(
        httpServletRequest: HttpServletRequest,
        @Valid @RequestBody transactionRequestDTO: TransactionRequestDTO
    ): ResponseEntity<ApiResponseDTO<TransactionCreatedResponseDTO>> {
        val token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)

        log.info("Receiving request to create transaction {}", transactionRequestDTO)

        return when(val createdTransaction = createTransactionUseCase.invoke(token, transactionRequestDTO)) {
            is Result.Error -> ResponseEntity(
                ApiResponseDTO(createdTransaction.exception.localizedMessage),
                createdTransaction.httpStatus
            )
            is Result.Success -> ResponseEntity(
                ApiResponseDTO(createdTransaction.data, "Transaction created successfully"),
                HttpStatus.CREATED
            )
        }
    }
}