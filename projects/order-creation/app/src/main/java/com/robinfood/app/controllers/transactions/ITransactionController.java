package com.robinfood.app.controllers.transactions;

import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionresponsedto.TransactionCreationResponseDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Exposes the API that handles all data related to transactions
 */
@Tag(name = "Transactions", description = "Requests for transactions related data")
public interface ITransactionController {

    /**
     * Validates all the necessary steps to create a transaction and then creates it
     *
     * @return the details of the created transaction and its orders
     */
    @Operation(summary = "Validates all the necessary steps to create a transaction and then creates it")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Returns the details of the created transaction and its orders",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TransactionCreationResponseDTO.class))
                    })
    })
    ResponseEntity<ApiResponseDTO<TransactionCreationResponseDTO>> createTransaction(
            TransactionRequestDTO transactionRequest
    ) throws TransactionCreationException;
}
