package com.robinfood.repository.productfinancecategory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.productfinancecategorydto.ProductFinanceCategoryDTO;
import com.robinfood.core.dtos.productfinancecategorydto.ProductFinanceCategoryResponseDTO;
import com.robinfood.core.entities.productfinancecategoryentity.ProductFinanceCategoryEntity;
import com.robinfood.core.entities.productfinancecategoryentity.ProductFinanceCategoryResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductFinanceCategoryRepositoryTest {

    @Mock
    private IProductFinanceCategoryRemoteDataSource mockProductFinanceCategoryRemoteDataSource;

    @InjectMocks
    private ProductFinanceCategoryRepository productFinanceCategoryRepository;

    private final List<Long> productIds = Arrays.asList(1L, 2L, 3L);

    private final List<ProductFinanceCategoryResponseEntity> financeCategoryResponseEntities = Arrays.asList(
            new ProductFinanceCategoryResponseEntity(
                    1L,
                    new ProductFinanceCategoryEntity(
                            1L,
                            "Category 1"
                    )
            ),
            new ProductFinanceCategoryResponseEntity(
                    2L,
                    new ProductFinanceCategoryEntity(
                            1L,
                            "Category 1"
                    )
            ),
            new ProductFinanceCategoryResponseEntity(
                    3L,
                    new ProductFinanceCategoryEntity(
                            2L,
                            "Category 2"
                    )
            )
    );

    private final List<ProductFinanceCategoryResponseDTO> productFinanceCategoryResponseDTOs = Arrays.asList(
            new ProductFinanceCategoryResponseDTO(
                    1L,
                    new ProductFinanceCategoryDTO(
                            1L,
                            "Category 1"
                    )
            ),
            new ProductFinanceCategoryResponseDTO(
                    2L,
                    new ProductFinanceCategoryDTO(
                            1L,
                            "Category 1"
                    )
            ),
            new ProductFinanceCategoryResponseDTO(
                    3L,
                    new ProductFinanceCategoryDTO(
                            2L,
                            "Category 2"
                    )
            )
    );

    @Test
    void test_GetProductsFinanceCategoriesReturns_Successfully() {
        String token = "token";
        when(mockProductFinanceCategoryRemoteDataSource.getProductsFinanceCategories(token, productIds)).thenReturn(
                CompletableFuture.completedFuture(financeCategoryResponseEntities)
        );

        final List<ProductFinanceCategoryResponseDTO> result = productFinanceCategoryRepository
                .getProductsFinanceCategories(token, productIds)
                .join();

        assertEquals(productFinanceCategoryResponseDTOs, result);
    }
}
