package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.configs.FeignConfig;
import com.robinfood.paymentmethodsbc.dto.api.transactions.PaymentRequestDTO;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    name = "transaction-messages-create-http-client",
    url = "${transaction-messages.create-trx.url}",
    configuration = FeignConfig.class,
    primary = false
)
@Repository
public interface TransactionMessagesCreateRepository {

    @PostMapping(
        path = "${transaction-messages.create-trx.uri}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseDTO<Object> createTransaction(
        @RequestHeader(name = "Authorization") String token,
        @RequestBody PaymentRequestDTO paymentRequestDTO
    );
}
