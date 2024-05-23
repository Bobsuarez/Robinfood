package com.robinfood.storeor.usecase.getconfigurationbystore;

import com.robinfood.storeor.dtos.configurationposbystore.StorePosDTO;
import com.robinfood.storeor.dtos.response.CityResponseDTO;
import com.robinfood.storeor.dtos.response.CountryResponseDTO;
import com.robinfood.storeor.dtos.response.StateResponseDTO;
import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentCategoryProductDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentTaxesDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentsDTO;
import com.robinfood.storeor.dtos.response.TreasureEntitiesDTO;
import com.robinfood.storeor.mocks.dto.TreasureDepartmentCategoryProductDTOMock;
import com.robinfood.storeor.mocks.dto.TreasureDepartmentTaxesDTOMock;
import com.robinfood.storeor.mocks.dto.TreasureEntitiesDTOMock;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mocks.dto.commandConfiguration.CommandConfigurationResponseDTOMock;
import com.robinfood.storeor.mocks.dto.configurationposbystore.StorePosDTOMock;
import com.robinfood.storeor.usecase.getbillingconfigurationbystore.IGetBillingConfigurationByStoreUseCase;
import com.robinfood.storeor.usecase.getcommandconfigurationbystoreid.IGetCommandConfigurationByStoreIdUseCase;
import com.robinfood.storeor.usecase.getconfigurationposbystore.IGetConfigurationPosByStoreUseCase;
import com.robinfood.storeor.usecase.getstorebyidusecase.IGetStoreByIdUseCase;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetConfigurationByStoreUseCaseTest {

    @Mock
    private IGetStoreByIdUseCase getStoreByIdUseCase;

    @Mock
    private IGetBillingConfigurationByStoreUseCase getBillingConfigurationByStoreUseCase;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @Mock
    private IGetCommandConfigurationByStoreIdUseCase getCommandConfigurationByStoreIdUseCase;

    @Mock
    private IGetConfigurationPosByStoreUseCase getConfigurationPosByStoreUseCase;

    @InjectMocks
    private GetConfigurationByStoreUseCase getConfigurationByStoreUseCase;

    private final CommandConfigurationResponseDTOMock commandConfiguration =
            new CommandConfigurationResponseDTOMock();

    private final TreasureEntitiesDTOMock entitiesMockDTO = new TreasureEntitiesDTOMock();

    private final List<TreasureEntitiesDTO> entitiesDTO = entitiesMockDTO.getDataDefaultList();

    private final TreasureDepartmentCategoryProductDTOMock productsMockDTO =
            new TreasureDepartmentCategoryProductDTOMock();

    private final List<TreasureDepartmentCategoryProductDTO> productsDTO = productsMockDTO.getDataDefaultList();

    private final TreasureDepartmentTaxesDTOMock taxesMockDTO = new TreasureDepartmentTaxesDTOMock();

    private final List<TreasureDepartmentTaxesDTO> taxesDTO = taxesMockDTO.getDataDefaultList();


    private final TreasureDepartmentsDTO treasureDepartmentsDTO = TreasureDepartmentsDTO.builder()
            .entities(entitiesDTO)
            .name("SEFAZ")
            .products(productsDTO)
            .taxes(taxesDTO)
            .build();

    final CityResponseDTO cityResponseDTO = CityResponseDTO.builder()
            .id(1l)
            .name("Barranquilla")
            .build();

    final CountryResponseDTO countryResponseDTO = CountryResponseDTO.builder()
            .id(1l)
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
            .treasuryDepartment(treasureDepartmentsDTO)
            .commandConfiguration(commandConfiguration.getDefautlListCommandConfiguration())
            .build();

    private final List<StorePosDTO> storePosDTOS = new StorePosDTOMock().storePosDTOs;


    final StoreResponseDTO storeResponseIncludePosDTO = StoreResponseDTO.builder()
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
            .treasuryDepartment(treasureDepartmentsDTO)
            .commandConfiguration(commandConfiguration.getDefautlListCommandConfiguration())
            .pos(storePosDTOS)
            .build();

    final String token = "123456677";


    @Test
    void test_When_GetConfigurationByStoreUseCase_Is_Ok() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build());

        when(getStoreByIdUseCase.invoke(anyLong(), anyString())).thenReturn(storeResponseDTO);

        when(getBillingConfigurationByStoreUseCase.invoke(anyLong(), anyString())).thenReturn(treasureDepartmentsDTO);
        when(getCommandConfigurationByStoreIdUseCase.invoke(anyLong(), anyString()))
                .thenReturn(commandConfiguration.getDefautlListCommandConfiguration());

        StoreResponseDTO storeResponse = getConfigurationByStoreUseCase.invoke(1L, Boolean.FALSE);

        assertEquals(storeResponse, storeResponseDTO);
    }

    @Test
    void test_When_GetConfigurationByStoreUseCase_IncludePos_Is_Ok() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build());

        when(getStoreByIdUseCase.invoke(anyLong(), anyString())).thenReturn(storeResponseDTO);

        when(getBillingConfigurationByStoreUseCase.invoke(anyLong(), anyString())).thenReturn(treasureDepartmentsDTO);
        when(getCommandConfigurationByStoreIdUseCase.invoke(anyLong(), anyString()))
                .thenReturn(commandConfiguration.getDefautlListCommandConfiguration());
        when(getConfigurationPosByStoreUseCase.invoke(anyLong())).thenReturn(storePosDTOS);


        StoreResponseDTO storeResponse = getConfigurationByStoreUseCase.invoke(1L, Boolean.TRUE);

        assertEquals(storeResponse.getPos(), storeResponseIncludePosDTO.getPos());
    }

    @Test
    void test_When_GetConfigurationStoreListUseCase_Is_Ok() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build());

        when(getStoreByIdUseCase.invoke(anyString(), anyLong(), anyInt(), anyInt(), any(Sort.class), anyString()))
                .thenReturn(new PageImpl<>(List.of(storeResponseDTO), PageRequest.of(0, 10), 10));

        Page<StoreResponseDTO> storeResponse = getConfigurationByStoreUseCase
                .invoke("", 1L, 1, 1, Sort.unsorted());

        assertEquals(storeResponse, new PageImpl<>(List.of(storeResponseDTO), PageRequest.of(0, 10), 10));
    }
}
