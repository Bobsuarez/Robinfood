package com.robinfood.app.usecases.getfinalproductsfinancecategories;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.getfinalproductcategories.GetFinalProductFinanceCategoriesUseCase;
import com.robinfood.core.dtos.productfinancecategorydto.ProductFinanceCategoryDTO;
import com.robinfood.core.dtos.productfinancecategorydto.ProductFinanceCategoryResponseDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.repository.productfinancecategory.IProductFinanceCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetFinalProductsFinanceCategoriesUseCaseTest {

    @Mock
    private IProductFinanceCategoryRepository mockProductFinanceCategoryRepository;

    @InjectMocks
    private GetFinalProductFinanceCategoriesUseCase getFinalProductsFinanceCategoriesUseCase;

    private final TransactionRequestDTO transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;

    private final String token = "token";

    private final List<ProductFinanceCategoryResponseDTO> productFinanceCategoryResponseDTOs = Collections.singletonList(
            new ProductFinanceCategoryResponseDTO(
                    1L,
                    new ProductFinanceCategoryDTO(
                            1L,
                            "Category 1"
                    )
            )
    );

    private final List<ProductFinanceCategoryResponseDTO> ResponseWithOtherProductsDTOs = Collections.singletonList(
            new ProductFinanceCategoryResponseDTO(
                    50L,
                    new ProductFinanceCategoryDTO(
                            1L,
                            "Category 1"
                    )
            )
    );

    @Test
    void test_GetFinalProductsFinanceCategoriesUseCase_Returns_Correctly() {

        final List<Long> finalProductsIds = transactionRequest.getOrders().get(0).getFinalProducts().stream().map(
                FinalProductDTO::getId
        ).collect(Collectors.toList());

        when(mockProductFinanceCategoryRepository.getProductsFinanceCategories(token, finalProductsIds))
                .thenReturn(CompletableFuture.completedFuture(productFinanceCategoryResponseDTOs));

        final TransactionCreationResult result = getFinalProductsFinanceCategoriesUseCase.invoke(token, transactionRequest)
                .join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_GetFinalProductsFinanceCategoriesUseCase_Throws_Exception() {

        try {
            final List<Long> finalProductsIds = transactionRequest.getOrders().get(0).getFinalProducts().stream().map(
                    FinalProductDTO::getId
            ).collect(Collectors.toList());

            when(mockProductFinanceCategoryRepository.getProductsFinanceCategories(token, finalProductsIds))
                    .thenReturn(CompletableFuture.completedFuture(ResponseWithOtherProductsDTOs));

            getFinalProductsFinanceCategoriesUseCase.invoke(token, transactionRequest).join();

        } catch (Exception exception) {
            assertTrue(exception instanceof TransactionCreationException);
        }
    }

    @Test
    void test_Params_Is_Null() {
        assertThrows(
                NullPointerException.class,
                () -> getFinalProductsFinanceCategoriesUseCase.invoke(null, null)
        );
    }
}
