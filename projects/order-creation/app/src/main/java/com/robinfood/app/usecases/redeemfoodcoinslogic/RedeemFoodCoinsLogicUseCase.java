package com.robinfood.app.usecases.redeemfoodcoinslogic;

import com.robinfood.app.usecases.getpaymentmethod.IGetPaymentMethodFoodCoinsUseCase;
import com.robinfood.core.dtos.ConfigTransactionResponseDTO;
import com.robinfood.core.dtos.RedeemFoodCoinsRequestDTO;
import com.robinfood.core.dtos.RedeemFoodCoinsResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductBrandDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import com.robinfood.core.dtos.transactionrequestdto.StoreDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.WriteInTransactionException;
import com.robinfood.core.mappers.FoodCoinsMappers;
import com.robinfood.repository.loyalty.IRedeemFoodCoinsRepository;
import com.robinfood.repository.transaction.ITransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;
import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class RedeemFoodCoinsLogicUseCase implements IRedeemFoodCoinsLogicUseCase {

    private final ITransactionRepository transactionRepository;
    private final IRedeemFoodCoinsRepository redeemFoodCoinsRepository;
    private final IGetPaymentMethodFoodCoinsUseCase getPaymentMethodFoodCoinsUseCase;

    public RedeemFoodCoinsLogicUseCase(
            ITransactionRepository transactionRepository,
            IRedeemFoodCoinsRepository redeemFoodCoinsRepository,
            IGetPaymentMethodFoodCoinsUseCase getPaymentMethodFoodCoinsUseCase
    ) {
        this.transactionRepository = transactionRepository;
        this.redeemFoodCoinsRepository = redeemFoodCoinsRepository;
        this.getPaymentMethodFoodCoinsUseCase = getPaymentMethodFoodCoinsUseCase;
    }

    public TransactionCreationResult invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequestDTO,
            @NotNull Integer operationType
    ) {

        try {

            final ConfigTransactionResponseDTO configTransactionResponseDTO = transactionRepository
                    .getTransactionResponseDTO();

            if (configTransactionResponseDTO.getOrderIds().size() != transactionRequestDTO.getOrders()
                    .size()) {
                throw new WriteInTransactionException(
                        HttpStatus.CONFLICT,
                        "The total of the requested orders is different from the total of the created orders",
                        TransactionCreationErrors.INCONSISTENT_TRANSACTION_ERROR
                );
            }

            final List<RedeemFoodCoinsResponseDTO> redeemFoodCoinResponseDTOS = new ArrayList<>();

            final List<PaymentMethodDTO> paymentMethodDTOS = Stream.of(
                    transactionRequestDTO.getPaymentMethods(),
                    transactionRequestDTO.getPaymentsPaid()
            ).flatMap(List::stream).collect(Collectors.toList());

            PaymentMethodDTO findPaymentMethodFoodCoin = getPaymentMethodFoodCoinsUseCase.invoke(paymentMethodDTOS);
            RedeemFoodCoinsRequestDTO redeemFoodCoinsRequestDTO = null;

            if (Objects.nonNull(findPaymentMethodFoodCoin)) {

                for (
                        int countOrderIds = DEFAULT_INTEGER_VALUE;
                        countOrderIds < transactionRequestDTO.getOrders().size();
                        countOrderIds++
                ) {

                    final OrderDTO orderDTO = transactionRequestDTO.getOrders().get(countOrderIds);
                    final StoreDTO getStore = orderDTO.getStore();
                    final FinalProductBrandDTO getFinalProductBrandDTO = orderDTO.getBrand();

                    BigDecimal totalSizeByOrders = BigDecimal.valueOf(transactionRequestDTO.getOrders().size());

                    BigDecimal amount = findPaymentMethodFoodCoin.getValue().divide(
                            totalSizeByOrders,
                            DEFAULT_INTEGER_VALUE,
                            RoundingMode.HALF_UP
                    );

                    redeemFoodCoinsRequestDTO = FoodCoinsMappers.toRedeemRequestDTO(
                            amount,
                            getFinalProductBrandDTO,
                            operationType,
                            getStore,
                            transactionRequestDTO
                    );

                    final RedeemFoodCoinsResponseDTO redeemFoodCoinResponseDTO = redeemFoodCoinsRepository
                            .redeemOrRollbackFoodCoins(
                                    token,
                                    redeemFoodCoinsRequestDTO

                            );

                    redeemFoodCoinResponseDTOS.add(redeemFoodCoinResponseDTO);
                }
            }

            log.info("Answers in the redemption FoodCoins, transaction: {}, request: {}, response: {}",
                    objectToJson(transactionRequestDTO),
                    objectToJson(redeemFoodCoinsRequestDTO),
                    objectToJson(redeemFoodCoinResponseDTOS));

            return TransactionCreationResult.StepValidationSuccess.INSTANCE;

        } catch (CancellationException | CompletionException exception) {
            log.error(exception.getMessage(), exception);
            throw new WriteInTransactionException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    exception.getLocalizedMessage()
            );
        }
    }
}
