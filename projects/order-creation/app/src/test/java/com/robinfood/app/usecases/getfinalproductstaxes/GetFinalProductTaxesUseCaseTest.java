package com.robinfood.app.usecases.getfinalproductstaxes;

import com.robinfood.app.mocks.FinalProductTaxDTOMock;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.addpaidpaymentstoorder.IAddPaidPaymentsToOrderUseCase;
import com.robinfood.app.usecases.configuretaxinfo.IConfigureTaxInfoUseCase;
import com.robinfood.app.usecases.getfinalproducttaxes.GetFinalProductTaxesUseCase;
import com.robinfood.core.dtos.ValidateTaxItemRequestDTO;
import com.robinfood.core.dtos.ValidateTaxItemResponseDTO;
import com.robinfood.core.dtos.ValidateTaxRequestDTO;
import com.robinfood.core.dtos.ValidateTaxResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.repository.tax.ITaxRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetFinalProductTaxesUseCaseTest {

    @Mock
    private ITaxRepository mockTaxRepository;

    @Mock
    private IConfigureTaxInfoUseCase configureTaxInfoUseCase;

    @Mock
    private IAddPaidPaymentsToOrderUseCase mockAddPaidPayments;

    @InjectMocks
    private GetFinalProductTaxesUseCase getFinalProductTaxesUseCase;

    private final ValidateTaxRequestDTO validateTaxRequestDTO = new ValidateTaxRequestDTO(
            new HashMap<>(),
            Collections.singletonList(
                    new ValidateTaxItemRequestDTO(
                            1L,
                            1L,
                            BigDecimal.valueOf(3000.0),
                            BigDecimal.valueOf(7900.0),
                            1
                    )
            )
    );
    private final List<ValidateTaxResponseDTO> validateTaxResponseDTOS = Collections.singletonList(
            new ValidateTaxResponseDTO(
                    3L,
                    1L,
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(14.9),
                    2,
                    Arrays.asList(
                            new ValidateTaxItemResponseDTO(
                                    1L,
                                    3L,
                                    BigDecimal.valueOf(0.0165),
                                    "gjmv",
                                    1L,
                                    1L,
                                    "PIS 1,65%",
                                    BigDecimal.valueOf(0.225)
                            ),
                            new ValidateTaxItemResponseDTO(
                                    1L,
                                    7L,
                                    BigDecimal.valueOf(0.076),
                                    "gjmv",
                                    1L,
                                    1L,
                                    "COFINS 7,6%",
                                    BigDecimal.valueOf(1.0365)
                            )
                    ),
                    BigDecimal.valueOf(2.523)
            )
    );
    private final TransactionRequestDTO transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;
    private final TransactionRequestDTO transactionRequestDiscountProduct = new TransactionRequestDTOMock()
            .transactionRequestDTOWithDiscountTaxes;

    private final TransactionRequestDTO transactionRequestDTONoDiscount = new TransactionRequestDTOMock()
            .transactionRequestDTOWithPaymentsPaid;

    private final String token = "token";

    @Test
    void test_GetFinalProductTaxesUseCase_Returns_Correctly() {

        when(mockTaxRepository.getFinalProductsTaxes(anyString(), any(ValidateTaxRequestDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(validateTaxResponseDTOS));

        when(mockTaxRepository.getFinalProductsTaxes(anyString(), any(ValidateTaxRequestDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(validateTaxResponseDTOS));

        final TransactionCreationResult result = getFinalProductTaxesUseCase.invoke(token, transactionRequest)
                .join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_GetFinalProducts_DISCOUNT_CONSUMTION() {

        when(mockTaxRepository.getFinalProductsTaxes(anyString(), any(ValidateTaxRequestDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(validateTaxResponseDTOS));

        when(mockTaxRepository.getFinalProductsTaxes(anyString(), any(ValidateTaxRequestDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(validateTaxResponseDTOS));

        final TransactionCreationResult result = getFinalProductTaxesUseCase.invoke(token, transactionRequestDiscountProduct)
                .join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_GetFinalProducts_NOT_DISCOUNT_CONSUMTION() {

        when(mockTaxRepository.getFinalProductsTaxes(anyString(), any(ValidateTaxRequestDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(validateTaxResponseDTOS));
        when(mockTaxRepository.getFinalProductsTaxes(anyString(), any(ValidateTaxRequestDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(validateTaxResponseDTOS));

        final TransactionCreationResult result = getFinalProductTaxesUseCase.invoke(token, transactionRequestDTONoDiscount)
                .join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void returnOneTaxes_When_ThereIsArticleId_And_DiscountCoupon() {

        transactionRequestDTONoDiscount
                .getOrders().get(0)
                .getFinalProducts().get(0)
                .getTaxes().add(FinalProductTaxDTOMock.build());

        transactionRequestDTONoDiscount
                .getOrders().get(0)
                .getFinalProducts().get(0)
                .setDiscounts(Collections.singletonList(
                        FinalProductDiscountDTO.builder()
                                .isProductDiscount(Boolean.FALSE)
                                .isCoupons(Boolean.TRUE)
                                .value(BigDecimal.valueOf(3000.0))
                                .build()
                ));

        when(mockTaxRepository.getFinalProductsTaxes(anyString(), any(ValidateTaxRequestDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(validateTaxResponseDTOS));

        when(mockTaxRepository.getFinalProductsTaxes(anyString(), any(ValidateTaxRequestDTO.class)))
                .thenReturn(CompletableFuture.completedFuture(validateTaxResponseDTOS));

        final TransactionCreationResult result = getFinalProductTaxesUseCase.invoke(token, transactionRequestDTONoDiscount)
                .join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }


    @Test
    void test_Params_Is_Null() {
        assertThrows(
                NullPointerException.class,
                () -> getFinalProductTaxesUseCase.invoke(null, null)
        );
    }
}
