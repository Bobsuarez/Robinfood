package com.robinfood.app.usecases.gettransactionuuid;

import com.robinfood.core.dtos.ExistsTransactionUuidOrderUuidDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.logs.OrderErrorLogEnum;
import com.robinfood.core.enums.logs.OrderLogEnum;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.logging.mappeddiagnosticcontext.CreateTransactionCustomLog;
import com.robinfood.repository.exitstransactionuuidorderuid.IExitsTransactionUuidOrderUuidRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.robinfood.core.enums.TransactionCreationErrors.TRANSACTION_ORDERS_UUID_ALL_REQUIRED;
import static com.robinfood.core.enums.TransactionCreationErrors.TRANSACTION_ORDERS_UUID_EXITS;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;
import static java.util.Objects.isNull;

/**
 * Implementation of ITransactionCreationStepUseCase
 */
@Service
@Slf4j
public class GetTransactionUuidUseCase implements IGetTransactionUuidUseCase {

    private final IExitsTransactionUuidOrderUuidRepository exitsTransactionUuidOrderUuidRepository;

    private final Predicate<OrderDTO> validationOrderUuidIsNull = orderDTO -> isNull(orderDTO.getUuid());

    public GetTransactionUuidUseCase(IExitsTransactionUuidOrderUuidRepository exitsTransactionUuidOrderUuidRepository) {
        this.exitsTransactionUuidOrderUuidRepository = exitsTransactionUuidOrderUuidRepository;
    }

    @Override
    public void invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequest
    ) {

        boolean areAllOrdersUuidsNotNull = transactionRequest.getOrders().stream()
                .noneMatch(validationOrderUuidIsNull);

        boolean areAllOrdersUuidsNull = transactionRequest.getOrders().stream()
                .allMatch(validationOrderUuidIsNull);

        validateAllOrderUuidMustBeNullOrNotNull(areAllOrdersUuidsNotNull, areAllOrdersUuidsNull);

        getTransactionUuid(transactionRequest);

        getOrdersUuids(token, transactionRequest, areAllOrdersUuidsNull);

        CreateTransactionCustomLog.addUuid(transactionRequest);

        log.info(
                OrderLogEnum.ORDER_RECEIVED_SUCCESSFULLY_HTTP.getMessage() + " {}",
                objectToJson(transactionRequest)
        );

        log.info("Get transaction and orders Uuids end: {}", objectToJson(transactionRequest));
    }

    private void getTransactionUuid(@NotNull TransactionRequestDTO transactionRequest) {

        if (isNull(transactionRequest.getUuid())) {
            transactionRequest.setUuid(UUID.randomUUID());
        }
    }

    private void getOrdersUuids(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequest,
            boolean areAllOrdersUuidsNull
    ) {

        ExistsTransactionUuidOrderUuidDTO exitsUuidOrders;

        if (areAllOrdersUuidsNull) {
            do {
                for (OrderDTO order : transactionRequest.getOrders()
                ) {
                    order.setUuid(UUID.randomUUID());
                }
                exitsUuidOrders = getExitsUuidOrders(token, transactionRequest);
            } while (exitsUuidOrders.isExits());

        } else {
            exitsUuidOrders = getExitsUuidOrders(token, transactionRequest);

            validateIfUuidExists(exitsUuidOrders);
        }
    }

    private ExistsTransactionUuidOrderUuidDTO getExitsUuidOrders(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequest
    ) {
        return exitsTransactionUuidOrderUuidRepository.invoke(
                token, null,
                transactionRequest.getOrders().stream()
                        .map(orderDTO -> orderDTO.getUuid().toString())
                        .collect(Collectors.toList()));
    }

    private void validateIfUuidExists(ExistsTransactionUuidOrderUuidDTO result) {
        if (result.isExits()) {
            log.error(OrderErrorLogEnum.ERROR_ORDER_TRANSACTION_EXIST_UUID.getMessage(), result.getMessage());
            throw new TransactionCreationException(HttpStatus.PRECONDITION_FAILED,
                    result.getMessage(),
                    TRANSACTION_ORDERS_UUID_EXITS);
        }
    }

    private void validateAllOrderUuidMustBeNullOrNotNull
            (boolean areAllOrdersUuidsNotNull, boolean areAllOrdersUuidsNull) {

        if (!(areAllOrdersUuidsNotNull || areAllOrdersUuidsNull)) {
            throw new TransactionCreationException(HttpStatus.BAD_REQUEST,
                    "All order uuids must be sent or send none",
                    TRANSACTION_ORDERS_UUID_ALL_REQUIRED);
        }
    }
}
