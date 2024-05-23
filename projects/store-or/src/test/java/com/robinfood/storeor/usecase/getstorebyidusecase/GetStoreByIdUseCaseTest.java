package com.robinfood.storeor.usecase.getstorebyidusecase;

import com.robinfood.storeor.dtos.UserStoreConfigurationsResponseDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.RestResponsePage;
import com.robinfood.storeor.dtos.response.CityResponseDTO;
import com.robinfood.storeor.dtos.response.CountryResponseDTO;
import com.robinfood.storeor.dtos.response.StateResponseDTO;
import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import com.robinfood.storeor.entities.CompanyEntity;
import com.robinfood.storeor.entities.StoreIdentifierTypeEntity;
import com.robinfood.storeor.entities.StoreTypeEntity;
import com.robinfood.storeor.entities.ZoneEntity;
import com.robinfood.storeor.mappers.IStoreMapper;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.CityEntity;
import com.robinfood.storeor.entities.CountryEntity;
import com.robinfood.storeor.entities.StateEntity;
import com.robinfood.storeor.entities.StoreEntity;
import com.robinfood.storeor.entities.UserStoreEntity;
import com.robinfood.storeor.mocks.dto.MultiBrandEntityMock;
import com.robinfood.storeor.repositories.configurationrepository.IConfigurationRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Implementation Test of GetStoreByIdUseCase
 */

@ExtendWith(MockitoExtension.class)
class GetStoreByIdUseCaseTest {

    @Mock
    private IConfigurationRepository mockConfigurationRepository;

    @Mock
    private IStoreMapper mockStoreMapper;


    @InjectMocks
    private GetStoreByIdUseCase getStoreByIdUseCaseUseCase;

    final CityResponseDTO cityResponseDTO = CityResponseDTO.builder()
            .id(1L)
            .name("Barranquilla")
            .build();

    final CountryResponseDTO countryResponseDTO = CountryResponseDTO.builder()
            .id(1L)
            .name("Colombia")
            .build();

    final StateResponseDTO stateResponseDTO = StateResponseDTO.builder()
            .id(1L)
            .name("Atlántico")
            .build();

    final StoreResponseDTO storeResponseDTO = StoreResponseDTO.builder()
            .address("Carr 42 B No. 30 - 25")
            .city(cityResponseDTO)
            .country(countryResponseDTO)
            .email("store1@robinfood.com")
            .identification("123")
            .internalName("test")
            .location("Barranquilla")
            .name("MUY 79")
            .phone("3508907012")
            .timezone("UTC")
            .state(stateResponseDTO)
            .build();

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
            "1234567890",
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

    @Test
    void test_Store_Returns_Correctly() {

        when(mockConfigurationRepository.getStore(1L, "token"))
                .thenReturn(ApiResponse);

        final StoreResponseDTO storeResponseDTO = getStoreByIdUseCaseUseCase.invoke(1L, "token");

        StoreResponseDTO store = mockStoreMapper.storeEntityToStoreResponseDto(ApiResponse.getData());
        assertEquals(storeResponseDTO, store);
    }

    @Test
    void test_StoreList_Returns_Correctly() {

        when(mockConfigurationRepository
            .getStore("", 1L, 1, 1, Sort.unsorted(), "token"))
            .thenReturn(ApiResponsePage);

        final Page<StoreResponseDTO> storeResponseDTO = getStoreByIdUseCaseUseCase
                .invoke("", 1L, 1, 1, Sort.unsorted(), "token");

        Page<StoreResponseDTO> store = ApiResponsePage.getData()
            .map(mockStoreMapper::storeEntityToStoreResponseDto);
        assertEquals(storeResponseDTO, store);
    }

    @Test
    void test_FindStoreByUserId_Returns_Correctly() {

        when(mockConfigurationRepository.findStoreByUserId(1L, "token"))
                .thenReturn(ApiUserStoreResponse);

        final UserStoreConfigurationsResponseDTO userStoreConfigurationsResponseDTO = getStoreByIdUseCaseUseCase.findStoreByUserId(1L, "token");

        StoreResponseDTO store = mockStoreMapper.storeEntityToStoreResponseDto(ApiResponse.getData());
        assertEquals(userStoreConfigurationsResponseDTO.getStore(), store);
    }
}

