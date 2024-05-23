package com.robinfood.repository.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.ValidateTaxItemRequestEntity;
import com.robinfood.core.entities.ValidateTaxItemResponseEntity;
import com.robinfood.core.entities.ValidateTaxRequestEntity;
import com.robinfood.core.entities.ValidateTaxResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.TaxesBCAPI;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
class TaxRemoteDataSourceTest {

    @Mock
    private TaxesBCAPI mockTaxBcAPI;

    @Mock
    private Call<ApiResponseEntity<List<ValidateTaxResponseEntity>>> mockGetFinalProductTaxes;

    @InjectMocks
    private TaxRemoteDataSource taxRemoteDataSource;

    private final ValidateTaxRequestEntity validateTaxRequestEntity = new ValidateTaxRequestEntity(
            new HashMap<>(),
            Arrays.asList(
                    new ValidateTaxItemRequestEntity(
                            1L,
                            1L,
                            BigDecimal.valueOf(30),
                            BigDecimal.valueOf(74.3),
                            4
                    ),
                    new ValidateTaxItemRequestEntity(
                            2L,
                            2L,
                            BigDecimal.valueOf(0),
                            BigDecimal.valueOf(30.3),
                            1
                    )
            )
    );
    private final List<ValidateTaxResponseEntity> validateTaxResponseEntities = Collections.singletonList(
            new ValidateTaxResponseEntity(
                    3L,
                    1L,
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(14.9),
                    2,
                    Arrays.asList(
                            new ValidateTaxItemResponseEntity(
                                    3L,
                                    1L,
                                    "PIS 1,65%",
                                    BigDecimal.valueOf(0.0165),
                                    "gjmv",
                                    1L,
                                    1L,
                                    BigDecimal.valueOf(0.225)
                            ),
                            new ValidateTaxItemResponseEntity(
                                    7L,
                                    1L,
                                    "COFINS 7,6%",
                                    BigDecimal.valueOf(0.076),
                                    "gjmv",
                                    1L,
                                    1L,
                                    BigDecimal.valueOf(1.0365)
                            )
                    ),
                    BigDecimal.valueOf(2.523)
            )
    );

    private final ApiResponseEntity<List<ValidateTaxResponseEntity>> getFinalProductTaxesApiResponseEntity = ApiResponseEntity.<List<ValidateTaxResponseEntity>>builder()
            .code(200)
            .data(validateTaxResponseEntities)
            .locale("CO")
            .message("Taxes returned")
            .build();

    private final ApiResponseEntity<List<ValidateTaxResponseEntity>> getFinalProductTaxesApiErrorResponseEntity = ApiResponseEntity.<List<ValidateTaxResponseEntity>>builder()
            .code(500)
            .error("Error")
            .locale("CO")
            .message("Taxes could not be returned")
            .build();

    private final String token = "token";

    @Test
    void test_Get_Final_Product_Taxes_Returns_Successfully() throws Exception {
        when(mockTaxBcAPI.getFinalProductsTaxes(token, validateTaxRequestEntity))
                .thenReturn(mockGetFinalProductTaxes);
        when(mockGetFinalProductTaxes.execute()).thenReturn(Response.success(getFinalProductTaxesApiResponseEntity));

        final List<ValidateTaxResponseEntity> result = taxRemoteDataSource.getFinalProductsTaxes(
                token,
                validateTaxRequestEntity
        ).join();
        assertEquals(validateTaxResponseEntities, result);
    }

    @Test
    void test_Get_Final_Product_Taxes_Returns_With_Failure() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(getFinalProductTaxesApiErrorResponseEntity);
        when(mockTaxBcAPI.getFinalProductsTaxes(token, validateTaxRequestEntity))
                .thenReturn(mockGetFinalProductTaxes);
        when(mockGetFinalProductTaxes.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
            taxRemoteDataSource
                    .getFinalProductsTaxes(token, validateTaxRequestEntity).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(getFinalProductTaxesApiErrorResponseEntity.getMessage()));
        }
    }
}
