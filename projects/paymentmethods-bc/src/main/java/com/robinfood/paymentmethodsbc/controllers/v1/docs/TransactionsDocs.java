package com.robinfood.paymentmethodsbc.controllers.v1.docs;

import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentInitResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.RefundResponseDTO;
import com.robinfood.paymentmethodsbc.dto.api.transactions.TransactionEntityDTO;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundRuntimeException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Servicios de transacciones")
public interface TransactionsDocs {
    /**
     * Servicio encargado para generar transacciones de compras
     *
     * @param transactionDTO información de transacción a generar
     * @throws PaymentStepException 
     */
    @Operation(summary = "Permite la creación de una transacción")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Transacción generada",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(
                            implementation = PaymentInitResponseDTO.class
                        )
                    )
                }
            )
        }
    )
    PaymentInitResponseDTO payment(
        @Valid @RequestBody PaymentRequestDTO transactionDTO
    ) throws BaseException, EntityNotFoundException, EntityNotFoundRuntimeException, PaymentStepException;

    /**
     * Servicio encargado de realizar el reembolso de una transacción
     *
     * @param refundRequestDTO información de transacción a reembolsar
     * @throws PaymentStepException 
     */
    @Operation(summary = "Permite realizar el reembolso de una transacción")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Reembolso de transacción actualizada",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = RefundResponseDTO.class)
                    )
                }
            )
        }
    )
    RefundResponseDTO refund(
        @Valid @RequestBody RefundRequestDTO refundRequestDTO
    )
        throws BaseException, EntityNotFoundException, PaymentStepException;

    /**
     * Servicio encargado de actualizar el estado de una transacción
     *
     * @param notification  {@linkplain String} notificación de cambio de estado
     * @param identificator {@linkplain String} referencia de transacción
     * @param key           {@linkplain String} llave de identificación de la transacción
     * @param type          {@linkplain String} type notificación (Purchase, Refund, Unknown)
     * @return {@linkplain ResponseDTO}
     *
     * @throws EntityNotFoundException error si no existe se encontró algun registro en BD
     * @throws BaseException           error genérico
     * @throws PaymentStepException 
     */
    @Operation(
        summary = "Permite la actualización de estado de una transacción"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Transacción actualizada",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = String.class)
                    )
                }
            )
        }
    )
    ResponseDTO<String> updateTransactionStatus(
        @RequestBody String notification,
        @PathVariable String identificator,
        @RequestParam(
            name = "key",
            required = false,
            defaultValue = AppConstants.STATUS_KEY_REFERENCE
        ) String key,
        @RequestParam(
            name = "type",
            required = false,
            defaultValue = AppConstants.TRANSACTION_TYPE_PURCHASE
        ) String type
    )
        throws BaseException, EntityNotFoundException, PaymentStepException;

    /**
     * @param entitySourceId id de la entidad
     * @param entityReference referencia de la entidad
     *
     * @return Listado de transacciones
     */
    @Operation(
        summary = "Servicio encargado de listar las transacciones"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "listar transacciones",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = String.class)
                    )
                }
            )
        }
    )
    List<TransactionEntityDTO> getTransactionsByEntityInfo(
        @RequestParam(name = "entity_source_id", required = false) Long entitySourceId,
        @RequestParam(name = "entity_reference", required = false) String entityReference,
        @RequestParam(name = "uuid", required = false) String uuid
    ) throws BaseException;
}
