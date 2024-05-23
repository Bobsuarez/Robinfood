package com.robinfood.app.usecases.validateco2;

import com.robinfood.app.components.Utils;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.CO2CalculateResponseEntity;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.repository.co2.ICO2Repository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class ValidateCO2UseCase implements IValidateCO2UseCase {

    private final ICO2Repository co2Repository;

    public ValidateCO2UseCase(ICO2Repository co2Repository) {
        this.co2Repository = co2Repository;
    }

    public CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequestDTO
    ) {

        final CompletableFuture<TransactionCreationResult> successResult = CompletableFuture.completedFuture(
                TransactionCreationResult.StepValidationSuccess.INSTANCE
        );

        final String isCO2ValidationEnabledString = Utils.getApplicationProperty("co2.validation.is-enabled");

        final boolean isCO2ValidationEnabled = Boolean.logicalOr(
                Objects.isNull(isCO2ValidationEnabledString),
                Boolean.parseBoolean(isCO2ValidationEnabledString)
        );

        if (Boolean.FALSE.equals(isCO2ValidationEnabled)) {
            log.info("CO2 validation not enabled via properties");
            return successResult;
        }

        int[] index = new int[1];

        transactionRequestDTO.getOrders().forEach(
                (OrderDTO orderDTO) -> {
                    log.info(String.format("CO2 validation for order[%d]: %s", index[0], objectToJson(orderDTO)));
                    if (orderDTO.getCo2Total() == null || orderDTO.getCo2Total().doubleValue() == 0) {
                        log.info(String.format("order[%d].co2Total is null or zero, skipping validation", index[0]));
                        return;
                    }
                    processOrder(token, orderDTO, index[0]);
                    log.info(String.format("CO2 validation for order[%d] was success", index[0]));
                    index[0]++;
                }
        );

        log.info("CO2 validation for all orders was success");
        return successResult;
    }

    private void processOrder(String token, OrderDTO orderDTO, int index) throws TransactionCreationException {
        CO2CalculateResponseEntity response = co2Repository.calculateCO2Compensation(token, orderDTO);

        if (orderDTO.getCo2Total().compareTo(response.getCo2TotalValue()) != 0) {
            String message = String.format(
                    "order[%d].co2Total is not valid. Actual: %s, expected: %s",
                    index,
                    orderDTO.getCo2Total().toString(),
                    response.getCo2TotalValue().toString()
            );
            log.error(message);
            throw new TransactionCreationException(
                    HttpStatus.FAILED_DEPENDENCY,
                    message,
                    TransactionCreationErrors.CO2_VALIDATION_ERROR
            );
        }
    }
}
