package com.robinfood.app.controllers.transaction

import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.transactionrequest.TransactionRequestDTO
import com.robinfood.core.dtos.transactionresponse.TransactionCreatedResponseDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletRequest

/**
 * Exposes the API that handles all data related to transactions
 */
@Tag(name = "Transactions", description = "Requests for transactions related data")
interface ITransactionController {

    /**
     * Sends request to create a transaction
     * @return The order object after being created
     */
    @Operation(summary = "Sends a request to create a transaction")
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "This sends a request to create a transaction",
            content = [(Content(
                array = ArraySchema(schema = Schema(implementation = TransactionCreatedResponseDTO::class)),
                mediaType = MediaType.APPLICATION_JSON_VALUE
            ))]
        )
    )
    suspend fun createTransaction(
        httpServletRequest: HttpServletRequest,
        transactionRequestDTO: TransactionRequestDTO
    ): ResponseEntity<ApiResponseDTO<TransactionCreatedResponseDTO>>
}