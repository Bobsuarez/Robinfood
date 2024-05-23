package com.robinfood.paymentmethodsbc.controllers.v1.docs;

import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreditCardDetailDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.UpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Servicios de tarjetas de crédito")
public interface CreditCardsDocs {
    /**
     * Metodo que consulta todas las campañas paginadas
     *
     * @param userId id de usuario
     * @param countryId id del país
     * @param generalPaymentMethodId id del mètodo de pago
     * @return List<CreditCardDetailDTO>
     * @throws PaymentStepException 
     */
    @Operation(
        summary = "Retorna listado de tarjetas de crédito de un usuario por pais"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Listado de tarjetas de crédito de un usuario por pais",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = List.class)
                    )
                }
            )
        }
    )
    List<CreditCardDetailDTO> findAllByUserAndCountryAndPaymentGateway(
        @PathVariable(name = "userId", required = true) Long userId,
        @RequestParam(name = "country_id", required = true) Long countryId,
        @RequestParam(name = "payment_method_id", required = true) Long generalPaymentMethodId
    )
        throws BaseException, EntityNotFoundException, PaymentStepException;

    /**
     * Metodo que invoca la creacion de tarjeta de crédito
     *
     * @param campaignDTO información de la tarjeta de crédito a crear
     * @return CreditCardDetailDTO
     * @throws PaymentStepException 
     */
    @Operation(summary = "Permite la creación de una tarjeta de crédito")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Tarjeta de crédito creada",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(
                            implementation = CreditCardDetailDTO.class
                        )
                    )
                }
            )
        }
    )
    CreditCardDetailDTO create(
        @Valid @RequestBody CreateCreditCardRequestDTO campaignDTO
    )
        throws BaseException, EntityNotFoundException, PaymentStepException;

    /**
     * Metodo que invoca la eliminación de tarjeta de crédito
     *
     * @param userId id de usuario propietario de tarjeta de crédito
     * @param creditCardId id de la tarjeta de crédito
     * @return ResponseResultDTO<String>
     * @throws PaymentStepException 
     */
    @Operation(summary = "Permite la eliminación de una tarjeta de crédito")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Tarjeta de crédito eliminada",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    ResponseDTO<String> delete(
        @PathVariable(name = "userId") Long userId,
        @PathVariable(name = "creditCardId") Long creditCardId
    )
        throws BaseException, EntityNotFoundException, PaymentStepException;


     /**
     * Method for update a credit card
     *
     * @param userId credit card owner
     * @param creditCardId credit card Id
     * @return ResponseResultDTO<String>
     * @throws PaymentStepException 
     */
    @Operation(summary = "Allow to updating a credit card")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Credit card updated",
                content = {
                    @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ResponseDTO.class)
                    )
                }
            )
        }
    )
    CreditCardDetailDTO update(
        @PathVariable(name = "userId") Long userId,
        @PathVariable(name = "creditCardId") Long creditCardId,
        @Valid @RequestBody UpdateCreditCardRequestDTO updateDTO
    )
        throws BaseException, EntityNotFoundException, PaymentStepException;

}
