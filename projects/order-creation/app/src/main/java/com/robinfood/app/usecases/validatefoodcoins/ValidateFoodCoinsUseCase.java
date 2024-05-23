package com.robinfood.app.usecases.validatefoodcoins;

import com.robinfood.app.usecases.getpaymentmethod.IGetPaymentMethodFoodCoinsUseCase;
import com.robinfood.core.dtos.ValidateFoodCoinsRequestDTO;
import com.robinfood.core.dtos.ValidateFoodCoinsResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.exceptions.WriteInTransactionException;
import com.robinfood.repository.loyalty.IRedeemFoodCoinsRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.robinfood.core.constants.GlobalConstants.FOOD_COINS_OPERATION_TYPE_REDEEM;
import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACES;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class ValidateFoodCoinsUseCase implements IValidateFoodCoinsUseCase{

    private final IRedeemFoodCoinsRepository redeemFoodCoinsRepository;
    private final IGetPaymentMethodFoodCoinsUseCase getPaymentMethodFoodCoinsUseCase;

    public ValidateFoodCoinsUseCase(
            IRedeemFoodCoinsRepository redeemFoodCoinsRepository,
            IGetPaymentMethodFoodCoinsUseCase getPaymentMethodFoodCoinsUseCase
    ) {
        this.redeemFoodCoinsRepository = redeemFoodCoinsRepository;
        this.getPaymentMethodFoodCoinsUseCase = getPaymentMethodFoodCoinsUseCase;
    }

    public CompletableFuture<TransactionCreationResult> invoke(
            @NonNull String token,
            @NonNull TransactionRequestDTO transactionRequestDTO
    ) {
        log.info("FoodCoins validation has started with request: {}", objectToJson(transactionRequestDTO));

        try {

            final List<PaymentMethodDTO> paymentMethodDTOS = Stream.of(
                    transactionRequestDTO.getPaymentMethods(),
                    transactionRequestDTO.getPaymentsPaid()
            ).flatMap(List::stream).collect(Collectors.toList());

            final PaymentMethodDTO foodCoinsPaymentMethod = getPaymentMethodFoodCoinsUseCase.invoke(
                    paymentMethodDTOS
            );

            if (Objects.nonNull(foodCoinsPaymentMethod)) {

                BigDecimal totalOrders = transactionRequestDTO.getOrders()
                        .stream()
                        .map(OrderDTO::getTotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                for (OrderDTO order : transactionRequestDTO.getOrders()) {
                    final BigDecimal calculateFoodCoin = order.getTotal()
                            .multiply(foodCoinsPaymentMethod.getValue())
                            .divide(totalOrders, NUMBER_DECIMAL_PLACES, RoundingMode.FLOOR);
                    order.setFoodcoins(calculateFoodCoin);
                }

                final BigDecimal amount = foodCoinsPaymentMethod.getValue();
                final Long userId = transactionRequestDTO.getUser().getId();
                final Long originId = transactionRequestDTO.getCompany().getId();

                ValidateFoodCoinsRequestDTO validateFoodCoinsRequestDTO = new ValidateFoodCoinsRequestDTO(
                        amount,
                        originId,
                        FOOD_COINS_OPERATION_TYPE_REDEEM,
                        userId
                );

                final ValidateFoodCoinsResponseDTO response = redeemFoodCoinsRepository.validateFoodCoins(
                        token,
                        validateFoodCoinsRequestDTO
                ).join();

                if (!response.isTransactionStatus()) {
                    return CompletableFuture.failedFuture(
                            new TransactionCreationException(
                                    HttpStatus.PRECONDITION_FAILED,
                                    response.getMessage(),
                                    TransactionCreationErrors.FOODCOINS_VALIDATION_ERROR
                            )
                    );
                }
            }

            return CompletableFuture.completedFuture(TransactionCreationResult.StepValidationSuccess.INSTANCE);

        } catch (CancellationException | CompletionException exception) {

            log.error(exception.getMessage(), exception);
            throw new WriteInTransactionException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    exception.getLocalizedMessage()
            );
        }
    }
}
