package com.robinfood.repository.loyalty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.RedeemFoodCoinsRequestEntity;
import com.robinfood.core.entities.RedeemFoodCoinsResponseEntity;
import com.robinfood.core.entities.ValidateFoodCoinsRequestEntity;
import com.robinfood.core.entities.ValidateFoodCoinsResponseEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.LoyaltyAPI;

import java.io.IOException;
import java.math.BigDecimal;

import com.robinfood.repository.mocks.entities.RedeemFoodCoinsRequestEntityMocks;
import com.robinfood.repository.mocks.entities.RedeemFoodCoinsResponseEntityMocks;
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
class RedeemFoodCoinDataSourceTest {

    @Mock
    private LoyaltyAPI mockLoyaltyAPI;

    @Mock
    private Call<ApiResponseEntity<RedeemFoodCoinsResponseEntity>> mockGetRedeemFoodCoin;

    @Mock
    private Call<ApiResponseEntity<ValidateFoodCoinsResponseEntity>> mockGetValidateFoodCoins;

    @InjectMocks
    private RedeemFoodCoinsRemoteDataSource redeemFoodCoinDataSource;

    private final String token = "token";
    private final RedeemFoodCoinsRequestEntity redeemFoodCoinsRequestEntity = RedeemFoodCoinsRequestEntityMocks.getDataDefault();
    private final RedeemFoodCoinsResponseEntity redeemFoodCoinsResponseEntity = RedeemFoodCoinsResponseEntityMocks.getDataDefault();

    final ApiResponseEntity<RedeemFoodCoinsResponseEntity> apiResponseEntity = ApiResponseEntity.<RedeemFoodCoinsResponseEntity>builder()
            .code(200)
            .data(redeemFoodCoinsResponseEntity)
            .locale("CO")
            .message("Success")
            .build();

    private final ValidateFoodCoinsResponseEntity validateFoodCoinsResponseEntity = ValidateFoodCoinsResponseEntity.builder()
            .userCurrentCredits(90)
            .transactionStatus(true)
            .transactionCredits(400)
            .build();

    final ApiResponseEntity<ValidateFoodCoinsResponseEntity> apiResponseEntityValidateFoodCoins = ApiResponseEntity.<ValidateFoodCoinsResponseEntity>builder()
            .code(200)
            .data(validateFoodCoinsResponseEntity)
            .locale("CO")
            .message("Success")
            .build();

    private final ValidateFoodCoinsRequestEntity validateFoodCoinsRequestEntity = new ValidateFoodCoinsRequestEntity(
            BigDecimal.valueOf(90),
            1L,
            1,
            1L
    );

    final ApiResponseEntity<Object> apiErrorResponseEntityLoyalty = ApiResponseEntity.builder()
            .code(400)
            .error("Error")
            .locale("CO")
            .message("There is not FoodCoins")
            .build();

    @Test
    void test_Get_Redeem_Food_Coins_Returns_Successfully() throws Exception {

        when(mockLoyaltyAPI.redeemOrRollbackFoodCoins(
                token,
                redeemFoodCoinsRequestEntity
        ))
                .thenReturn(mockGetRedeemFoodCoin);

        when(mockGetRedeemFoodCoin.execute()).thenReturn(Response.success(apiResponseEntity));

        final RedeemFoodCoinsResponseEntity result = redeemFoodCoinDataSource
                .redeemOrRollbackFoodCoins(token, redeemFoodCoinsRequestEntity);

        assertEquals(redeemFoodCoinsResponseEntity, result);
    }

    @Test
    void test_Error_Redeem_Food_Coins_Returns_Exception() throws Exception {

        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntityLoyalty);

        when(mockLoyaltyAPI.redeemOrRollbackFoodCoins(
                token,
                redeemFoodCoinsRequestEntity
        ))
                .thenReturn(mockGetRedeemFoodCoin);

        when(mockGetRedeemFoodCoin.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
           redeemFoodCoinDataSource.redeemOrRollbackFoodCoins(token, redeemFoodCoinsRequestEntity);
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityLoyalty.getMessage()));
        }
    }

    @Test
    void test_Get_Validate_Food_Coins_Returns_With_Successfully() throws IOException {

        lenient().when(mockGetValidateFoodCoins.execute()).thenReturn(
                Response.success(apiResponseEntityValidateFoodCoins)
        );

        lenient().when(mockLoyaltyAPI.validateFoodcoins(
                token,
                validateFoodCoinsRequestEntity.getAmount(),
                validateFoodCoinsRequestEntity.getCountryId(),
                validateFoodCoinsRequestEntity.getOperationType(),
                validateFoodCoinsRequestEntity.getUserId()

        )).thenReturn(
                mockGetValidateFoodCoins
        );
        ApiResponseEntity<ValidateFoodCoinsResponseEntity> result = redeemFoodCoinDataSource.validateFoodCoins(
                token,
                validateFoodCoinsRequestEntity
        ).join();
        assertNotNull(result);
    }
}
