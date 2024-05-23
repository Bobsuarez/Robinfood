package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.configs.FeignConfig;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.dto.jms.JmsTransactionStatusChangeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    name = "transaction-messages-notify-http-client",
    url = "${transaction-messages.notify-status.url}",
    configuration = FeignConfig.class,
    primary = false
)
@Repository
public interface TransactionMessagesNotifyRepository {

    @PostMapping(
        path = "${transaction-messages.notify-status.uri}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseDTO<Object> notifyChangeStatusTransaction(
        @RequestHeader(name = "Authorization") String token,
        @RequestBody JmsTransactionStatusChangeDTO statusChangeDTO
    );
}
