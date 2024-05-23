package com.robinfood.app.usecases.validatediscounts;

import com.robinfood.app.usecases.distributeplatformdeliveryfee.IDistributedPlatformDeliveryFeeUseCase;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

@Service
@Slf4j
public class ValidateDiscountsUseCase implements IValidateDiscountsUseCase {
    private final IDistributedPlatformDeliveryFeeUseCase distributedPlatformDeliveryFeeUseCase;

    public ValidateDiscountsUseCase(IDistributedPlatformDeliveryFeeUseCase distributedPlatformDeliveryFeeUseCase) {
        this.distributedPlatformDeliveryFeeUseCase = distributedPlatformDeliveryFeeUseCase;
    }

    @Override
    public CompletableFuture<TransactionCreationResult> invoke(
            @NotNull String token,
            @NotNull TransactionRequestDTO transactionRequestDTO
    ) {
        log.info("Discounts validation has started with request: {}", objectToJson(transactionRequestDTO));

        if (Objects.nonNull(transactionRequestDTO.getDeductions())) {
            distributedPlatformDeliveryFeeUseCase.invoke(transactionRequestDTO, token);
        }

        for (OrderDTO order : transactionRequestDTO.getOrders()) {

            setDiscountsFinalProducts(order.getFinalProducts());
        }

        return CompletableFuture.completedFuture(TransactionCreationResult.StepValidationSuccess.INSTANCE);
    }

    private void setDiscountsFinalProducts(
            @NotNull List<FinalProductDTO> finalProducts
    ) {

        for (FinalProductDTO finalProduct : finalProducts) {
            for (FinalProductDiscountDTO discount : finalProduct.getDiscounts()) {
                discount.setIsProductDiscount(Boolean.TRUE);
                discount.setSKU(finalProduct.getSku());
            }
        }
    }
}
