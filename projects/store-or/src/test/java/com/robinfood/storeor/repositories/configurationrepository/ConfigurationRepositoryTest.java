package com.robinfood.storeor.repositories.configurationrepository;

import com.robinfood.storeor.configs.apis.ConfigurationsBcApi;
import com.robinfood.storeor.dtos.apiresponsebuilder.RestResponsePage;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.CityEntity;
import com.robinfood.storeor.entities.CompanyEntity;
import com.robinfood.storeor.entities.CountryEntity;
import com.robinfood.storeor.entities.StateEntity;
import com.robinfood.storeor.entities.StoreEntity;
import com.robinfood.storeor.entities.StoreIdentifierTypeEntity;
import com.robinfood.storeor.entities.StoreTypeEntity;
import com.robinfood.storeor.entities.ZoneEntity;
import com.robinfood.storeor.entities.UserStoreEntity;

import java.util.List;
import java.util.UUID;

import com.robinfood.storeor.entities.listposresponse.PosListResponseEntity;
import com.robinfood.storeor.mocks.dto.MultiBrandEntityMock;
import com.robinfood.storeor.mocks.entity.listposresponse.PosListResponseEntityMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfigurationRepositoryTest {

    @Mock
    private ConfigurationsBcApi configurationsBcApi;

    @InjectMocks
    private ConfigurationRepository configurationRepository;

    private final String token = "token";

    private final Long storeId = 1L;
    private final Long userId = 1L;

    final StoreEntity storeEntity = new StoreEntity(
            "Carr 42 B No. 30 - 25",
            new CityEntity(
                    1L,
                    "Barranquilla",
                    "America/Barranquilla"
            ),
            new CountryEntity(
                    1L,
                    "Colombia"
            ),
            "COP",
            "$",
            "store1@robinfood.com",
            "123",
            "test",
            "Barranquilla",
            MultiBrandEntityMock.getDataDefault(),
            "MUY 79",
            "3508907012",
            "UTC",
            new StateEntity(
                    1L,
                    "Atlántico"
            ),
            1L,
            new ZoneEntity(new CityEntity(1L, "city", "timezone"), 1L),
            new StoreTypeEntity(1L, "store type"),
            UUID.randomUUID().toString(),
            new StoreIdentifierTypeEntity(),
            new CompanyEntity()
    );

    final UserStoreEntity userStoreEntity = new UserStoreEntity(1L, storeEntity);

    final APIResponseEntity<StoreEntity> ApiResponse = new APIResponseEntity<>(
            200,
            storeEntity,
            "locale",
            "Store retrieved successfully",
            "Success"
    );

    final APIResponseEntity<RestResponsePage<StoreEntity>> ApiResponsePage = new APIResponseEntity<>(
            200,
            new RestResponsePage<>(List.of(storeEntity)),
            "locale",
            "Store retrieved successfully",
            "Success"
    );

    final APIResponseEntity<UserStoreEntity> ApiUserStoreResponse = new APIResponseEntity<>(
            200,
            userStoreEntity,
            "locale",
            "Store retrieved successfully",
            "Success"
    );

    final PosListResponseEntity posListResponseEntity = PosListResponseEntityMock.build();

    final APIResponseEntity<RestResponsePage<PosListResponseEntity>> ApiResponseListPosPage = new APIResponseEntity<>(
            200,
            new RestResponsePage<>(List.of(posListResponseEntity)),
            "locale",
            "Pos List retrieved successfully",
            "Success"
    );

    @Test
    void test_GetStoreId_Returns_Successfully() {

        final String token = "token";

        when(configurationsBcApi.getStoreById(storeId, token)).thenReturn(ApiResponse);

        final APIResponseEntity<StoreEntity> storeResponseDTO = configurationRepository.getStore(storeId, token);

        assertEquals(storeResponseDTO, ApiResponse);
    }

    @Test
    void test_GetStoreList_Returns_Successfully() {

        final String token = "token";

        when(configurationsBcApi
                .getStoreList("", 1L, 1, 1, Sort.unsorted(), token))
                .thenReturn(ApiResponsePage);

        final APIResponseEntity<RestResponsePage<StoreEntity>> storeResponseDTO = configurationRepository
                .getStore("", 1L, 1, 1, Sort.unsorted(), token);

        assertEquals(storeResponseDTO, ApiResponsePage);
    }


    @Test
    void test_GetStoreId_Returns_Successfully1() {

        final String token = "token";

        when(configurationsBcApi.findStoreByUserId(userId, token)).thenReturn(ApiUserStoreResponse);

        final APIResponseEntity<UserStoreEntity> userStoreResponseDTO = configurationRepository
                .findStoreByUserId(userId, token);

        assertEquals(userStoreResponseDTO, ApiUserStoreResponse);
    }

    @Test
    void test_Get_Pos_List_Returns_Successfully() {

        when(configurationsBcApi.
                getListPos(0, "caja", 10, 1L, 1L, Sort.unsorted(), "token"))
                .thenReturn(ApiResponseListPosPage);

        final APIResponseEntity<RestResponsePage<PosListResponseEntity>> apiResponseEntity = configurationRepository
                .getListPos(0, "caja", 10, 1L, 1L, Sort.unsorted(), "token");

        assertEquals(apiResponseEntity, ApiResponseListPosPage);
    }
}

