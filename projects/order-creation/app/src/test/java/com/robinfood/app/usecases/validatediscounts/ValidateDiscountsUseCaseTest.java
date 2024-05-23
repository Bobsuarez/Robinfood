package com.robinfood.app.usecases.validatediscounts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.distributediscounts.IDistributeDiscountsUseCase;
import com.robinfood.app.usecases.distributeplatformdeliveryfee.IDistributedPlatformDeliveryFeeUseCase;
import com.robinfood.core.dtos.DiscountContainerResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidateDiscountsUseCaseTest {

    @Mock
    IDistributeDiscountsUseCase distributeDiscountsUseCase;

    @Mock
    IDistributedPlatformDeliveryFeeUseCase distributedPlatformDeliveryFeeUseCase;

    @InjectMocks
    private ValidateDiscountsUseCase validateDiscountsUseCase;

    private final TransactionRequestDTO transactionRequestValidateDiscountDTO =
            new TransactionRequestDTOMock().transactionRequestValidateDiscountDTO;

    final String token = "token";

    final ArrayList<DiscountContainerResponseDTO> invalidDiscounts = new ArrayList<>(
            Collections.singletonList(
                    new DiscountContainerResponseDTO(
                            "ARTICLE",
                            1,
                            "Price does not match",
                            1,
                            false,
                            BigDecimal.valueOf(3000.0)
                    )
            )
    );

    private final TransactionRequestDTO transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;

    private final TransactionRequestDTO transactionRequestDeductions = new TransactionRequestDTOMock().transactionRequestDTOWithDeductions;

    @Test
    void test_Some_Discount_Is_Invalid() {

        try {
            validateDiscountsUseCase.invoke(token, transactionRequest).get();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(invalidDiscounts.get(0).getReason()));
        }
    }

    @Test
    void test_Some_Discount_Is_Valid() throws Exception {

        final TransactionCreationResult result = validateDiscountsUseCase.invoke(token, transactionRequest).get();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_Some_Discount_Is_Valid_With_Deductions() throws Exception {

        final TransactionCreationResult result = validateDiscountsUseCase
                .invoke(token, transactionRequestDeductions).get();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }
}
