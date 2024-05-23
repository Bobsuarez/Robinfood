package com.robinfood.paymentmethodsbc.components.clients;

import com.robinfood.paymentmethodsbc.configs.FeignConfig;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCICreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCICreditCardResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCIDeleteCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.creditcard.BCIUpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCICancelTransactionResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIRefundRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIRefundResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIPaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCIPaymentResponseDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusRequestDTO;
import com.robinfood.paymentmethodsbc.dto.bci.transactions.BCITransactionStatusResponseDTO;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import java.net.URI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    name = "bci-http-client",
    configuration = FeignConfig.class,
    url = "https://releno.internal",
    primary = false
)
@Component
public interface BCIClient {
    @PostMapping(
        path = "${bci.credit-card}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseDTO<BCICreditCardResponseDTO> createCreditCard(
        URI bciUri,
        @RequestHeader(name = "Authorization") String token,
        @RequestBody BCICreateCreditCardRequestDTO body
    );

    @PutMapping(
        path = "${bci.credit-card}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseDTO<BCICreditCardResponseDTO> updateCreditCard(
        URI bciUri,
        @RequestHeader(name = "Authorization") String token,
        @RequestBody BCIUpdateCreditCardRequestDTO body
    );

    @DeleteMapping(
        path = "${bci.credit-card}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseDTO<BCICreditCardResponseDTO> deleteCreditCard(
        URI bciUri,
        @RequestHeader(name = "Authorization") String token,
        @RequestBody BCIDeleteCreditCardRequestDTO body
    );

    @PostMapping(
        path = "${bci.transaction.generate}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseDTO<BCIPaymentResponseDTO> generateTransaction(
        URI bciUri,
        @RequestHeader(name = "Authorization") String token,
        @RequestBody BCIPaymentRequestDTO body
    );

    @PutMapping(
        path = "${bci.transaction.refund}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseDTO<BCIRefundResponseDTO> refundTransaction(
        URI bciUri,
        @RequestHeader(name = "Authorization") String token,
        @RequestBody BCIRefundRequestDTO body
    );

    @PostMapping(
        path = "${bci.transaction.notification}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseDTO<BCITransactionStatusResponseDTO> validateStatus(
        URI bciUri,
        @RequestHeader(name = "Authorization") String token,
        @RequestBody BCITransactionStatusRequestDTO body
    );

    @DeleteMapping(
        path = "${bci.transaction.cancel}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseDTO<BCICancelTransactionResponseDTO> cancelTransaction(
        URI bciUri,
        @RequestHeader(name = "Authorization") String token,
        @PathVariable(name = "reference") String reference
    );
}
