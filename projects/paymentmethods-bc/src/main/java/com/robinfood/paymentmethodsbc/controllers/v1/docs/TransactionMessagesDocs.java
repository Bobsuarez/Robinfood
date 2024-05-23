package com.robinfood.paymentmethodsbc.controllers.v1.docs;

import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsEntityRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsUpdateTransactionStatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "Transaction messages services")
public interface TransactionMessagesDocs {

    @Operation(summary = "Creates a transaction")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Response ok for create transaction",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(
                            implementation = ResponseDTO.class
                        )
                    )
                }
            )
        }
    )
    ResponseDTO<Object> createTransaction(
        @Valid @RequestBody PaymentRequestDTO paymentRequestDTO
    );

    @Operation(summary = "Update a transaction status")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Response ok for update transaction",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(
                            implementation = ResponseDTO.class
                        )
                    )
                }
            )
        }
    )
    ResponseDTO<Object> updateTransaction(
        @RequestBody JmsUpdateTransactionStatusDTO transactionDTO
    );

    @Operation(summary = "Refunds a transaction")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Response ok for refund transaction",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(
                            implementation = ResponseDTO.class
                        )
                    )
                }
            )
        }
    )
    ResponseDTO<Object> refundTransaction(
        @RequestBody JmsEntityRefundRequestDTO refundRequestDTO
    );
}
