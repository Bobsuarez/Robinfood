package com.robinfood.repository.productfinancecategory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.CompanyConsumptionTaxEntity;
import com.robinfood.core.entities.productfinancecategoryentity.ProductFinanceCategoryEntity;
import com.robinfood.core.entities.productfinancecategoryentity.ProductFinanceCategoryResponseEntity;
import com.robinfood.core.extensions.ListExtensionsKt;
import com.robinfood.core.extensions.ObjectExtensions;
import java.util.Arrays;
import java.util.List;

import com.robinfood.network.api.OrderSyncDataBCAPI;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class ProductFinanceCategoryRemoteDataSourceTest {

    @Mock
    private OrderSyncDataBCAPI mockOrderSyncDataBCAPI;

    @Mock
    private Call<ApiResponseEntity<List<ProductFinanceCategoryResponseEntity>>> mockProductsFinanceCategories;

    @InjectMocks
    private ProductFinanceCategoryRemoteDataSource productFinanceCategoryRemoteDataSource;

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

    private final ApiResponseEntity<List<ProductFinanceCategoryResponseEntity>> categoriesApiResponseEntity = ApiResponseEntity.<List<ProductFinanceCategoryResponseEntity>>builder()
            .code(200)
            .data(financeCategoryResponseEntities)
            .locale("CO")
            .message("Categories returned")
            .build();

    private final ApiResponseEntity<CompanyConsumptionTaxEntity> categoriesApiErrorResponseEntity = ApiResponseEntity.<CompanyConsumptionTaxEntity>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("Categories not found")
            .build();

    private final String token = "token";

    @Test
    void test_GetProductsFinanceCategories_Returns_Successfully() throws Exception {
        final String productsIdsQuery = ListExtensionsKt.toQueryParam(productIds);
        when(mockOrderSyncDataBCAPI.getProductsFinanceCategories(productsIdsQuery, token))
                .thenReturn(mockProductsFinanceCategories);
        when(mockProductsFinanceCategories.execute()).thenReturn(Response.success(categoriesApiResponseEntity));

        final List<ProductFinanceCategoryResponseEntity> result = productFinanceCategoryRemoteDataSource
                .getProductsFinanceCategories(token, productIds).join();

        assertEquals(financeCategoryResponseEntities, result);
    }

    @Test
    void test_GetProductsFinanceCategories_Returns_Failure() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(categoriesApiErrorResponseEntity);
        final String productsIdsQuery = ListExtensionsKt.toQueryParam(productIds);
        when(mockOrderSyncDataBCAPI.getProductsFinanceCategories(productsIdsQuery, token))
                .thenReturn(mockProductsFinanceCategories);
        when(mockProductsFinanceCategories.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
            productFinanceCategoryRemoteDataSource.getProductsFinanceCategories(token, productIds).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(categoriesApiErrorResponseEntity.getMessage()));
        }
    }
}
