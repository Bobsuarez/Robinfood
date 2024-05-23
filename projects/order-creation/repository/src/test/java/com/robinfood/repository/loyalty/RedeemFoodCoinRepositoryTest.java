package com.robinfood.repository.loyalty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.robinfood.core.dtos.RedeemFoodCoinsRequestDTO;
import com.robinfood.core.dtos.RedeemFoodCoinsResponseDTO;
import com.robinfood.core.dtos.ValidateFoodCoinsRequestDTO;
import com.robinfood.core.dtos.ValidateFoodCoinsResponseDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.RedeemFoodCoinsRequestEntity;
import com.robinfood.core.entities.RedeemFoodCoinsResponseEntity;
import com.robinfood.core.entities.ValidateFoodCoinsRequestEntity;
import com.robinfood.core.entities.ValidateFoodCoinsResponseEntity;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import com.robinfood.repository.mocks.dtos.RedeemFoodCoinsRequestDTOMocks;
import com.robinfood.repository.mocks.dtos.RedeemFoodCoinsResponseDTOMocks;
import com.robinfood.repository.mocks.entities.RedeemFoodCoinsRequestEntityMocks;
import com.robinfood.repository.mocks.entities.RedeemFoodCoinsResponseEntityMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RedeemFoodCoinRepositoryTest {

    @Mock
    private IRedeemFoodCoinsRemoteDataSource mockRedeemFoodCoinDataSource;

    @InjectMocks
    private RedeemFoodCoinsRepository redeemFoodCoinRepository;

    private final String token = "token";

    private final RedeemFoodCoinsRequestEntity redeemFoodCoinsRequestEntity = RedeemFoodCoinsRequestEntityMocks.getDataDefault();
    private final RedeemFoodCoinsResponseEntity redeemFoodCoinsResponseEntity = RedeemFoodCoinsResponseEntityMocks.getDataDefault();
    private final RedeemFoodCoinsResponseDTO redeemFoodCoinsResponseDTO = RedeemFoodCoinsResponseDTOMocks.getDataDefault();
    private final RedeemFoodCoinsRequestDTO redeemFoodCoinsRequestDTO = RedeemFoodCoinsRequestDTOMocks.getDataDefault();

    private final ValidateFoodCoinsRequestEntity validateFoodCoinsRequestEntity = ValidateFoodCoinsRequestEntity.builder()
            .amount(BigDecimal.valueOf(90))
            .countryId(1L)
            .operationType(1)
            .userId(1L)
            .build();

    private final ValidateFoodCoinsResponseEntity validateFoodCoinsResponseEntity = ValidateFoodCoinsResponseEntity.builder()
            .transactionCredits(90)
            .transactionStatus(true)
            .userCurrentCredits(400)
            .build();

    private final ApiResponseEntity<ValidateFoodCoinsResponseEntity> apiResponseEntity = ApiResponseEntity.<ValidateFoodCoinsResponseEntity>builder()
            .code(1)
            .data(validateFoodCoinsResponseEntity)
            .build();

    private final ValidateFoodCoinsRequestDTO validateFoodCoinsRequestDTO = new ValidateFoodCoinsRequestDTO(
            validateFoodCoinsRequestEntity.getAmount(),
            validateFoodCoinsRequestEntity.getCountryId(),
            validateFoodCoinsRequestEntity.getOperationType(),
            validateFoodCoinsRequestEntity.getUserId()
    );

    @Test
    void test_Get_Redeem_Food_Coins_Returns_Successfully() {

        when(mockRedeemFoodCoinDataSource.redeemOrRollbackFoodCoins(
                token, redeemFoodCoinsRequestEntity
        )).thenReturn(redeemFoodCoinsResponseEntity);

        final RedeemFoodCoinsResponseDTO response = redeemFoodCoinRepository.redeemOrRollbackFoodCoins(token, redeemFoodCoinsRequestDTO);

        assertEquals(redeemFoodCoinsResponseDTO, response);
    }

    @Test
    void test_Get_Validate_Food_Coins_Food_Coins_Returns_Successfully() {
        when(mockRedeemFoodCoinDataSource.validateFoodCoins(
                token,
                validateFoodCoinsRequestEntity
        )).thenReturn(
                CompletableFuture.completedFuture(apiResponseEntity)
        );

        CompletableFuture<ValidateFoodCoinsResponseDTO> response =
                redeemFoodCoinRepository.validateFoodCoins(token, validateFoodCoinsRequestDTO);

        assertNotNull(response.join());
    }
}
