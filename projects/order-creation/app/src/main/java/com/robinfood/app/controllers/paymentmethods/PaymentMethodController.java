package com.robinfood.app.controllers.paymentmethods;

import com.robinfood.app.usecases.sendmsgacceptedorrejectedtoqueue.ISendMsgAcceptedOrRejectedToQueueUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.queue.paymentmethod.TransactionDTO;
import com.robinfood.core.util.ObjectMapperSingleton;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.constants.APIConstants.PAYMENT_METHODS_V1;
import static com.robinfood.core.constants.GlobalConstants.NAME_SQS_PAYMENT_METHOD;
import static com.robinfood.core.constants.GlobalConstants.RESPONSE_PAYMENT_METHOD;
import static com.robinfood.core.enums.logs.OrderLogEnum.ORDER_RECEIVING_SQS_QUEUE_PAYMENT_METHOD;
import static com.robinfood.core.enums.logs.OrderLogEnum.ORDER_SUCCESSFULLY_SQS_QUEUE_PAYMENT_METHOD;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@RestController
@RequestMapping(PAYMENT_METHODS_V1)
@Slf4j
public class PaymentMethodController implements IPaymentMethodController {

    private final ISendMsgAcceptedOrRejectedToQueueUseCase sendMsgAcceptedOrRejectedToQueueUseCase;

    public PaymentMethodController(ISendMsgAcceptedOrRejectedToQueueUseCase sendMsgAcceptedOrRejectedToQueueUseCase) {
        this.sendMsgAcceptedOrRejectedToQueueUseCase = sendMsgAcceptedOrRejectedToQueueUseCase;
    }

    @Override
    @PostMapping("/transaction/response")
    public ResponseEntity<ApiResponseDTO<Object>> processPaymentMethodResult(@RequestBody TransactionDTO transaction) {

        log.info(
                ORDER_RECEIVING_SQS_QUEUE_PAYMENT_METHOD.getMessage(),
                NAME_SQS_PAYMENT_METHOD,
                ObjectMapperSingleton.objectToJson(transaction)
        );

        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
                sendMsgAcceptedOrRejectedToQueueUseCase.invoke(transaction)
        );

        future.thenRun(() ->
                log.info(ORDER_SUCCESSFULLY_SQS_QUEUE_PAYMENT_METHOD.getMessage(), objectToJson(transaction))
        );

        return new ResponseEntity<>(buildResponseSuccess(), HttpStatus.OK);

    }

    @NotNull
    private static ApiResponseDTO buildResponseSuccess() {
        ApiResponseDTO apiResponse = new ApiResponseDTO(new HashMap<>());
        apiResponse.setMessage(RESPONSE_PAYMENT_METHOD);
        return apiResponse;
    }
}
