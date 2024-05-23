package com.robinfood.storeor.usecase.getposconfiguration;

import com.robinfood.storeor.dtos.UserStoreConfigurationsResponseDTO;
import com.robinfood.storeor.dtos.response.TreasureEntitiesDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentTaxesDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentsDTO;
import com.robinfood.storeor.dtos.response.CityResponseDTO;
import com.robinfood.storeor.dtos.response.StateResponseDTO;
import com.robinfood.storeor.dtos.response.CountryResponseDTO;
import com.robinfood.storeor.dtos.response.CompanyDTO;
import com.robinfood.storeor.dtos.response.HeadquarterDTO;
import com.robinfood.storeor.dtos.response.PosTypeDTO;
import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import com.robinfood.storeor.dtos.response.PosResponseDTO;
import com.robinfood.storeor.dtos.user.PosConfigurationResponseDTO;
import com.robinfood.storeor.dtos.user.UserPosResponseDTO;
import com.robinfood.storeor.dtos.user.UserStoreResponseDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mocks.dto.TreasureDepartmentTaxesDTOMock;
import com.robinfood.storeor.mocks.dto.TreasureEntitiesDTOMock;
import com.robinfood.storeor.mocks.dto.commandConfiguration.CommandConfigurationResponseDTOMock;
import com.robinfood.storeor.usecase.getconfigurationbypos.GetConfigurationByPosUseCase;
import com.robinfood.storeor.usecase.getstorebyidusecase.GetStoreByIdUseCase;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPosConfigurationByUserIdUseCaseTest {

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    @Mock
    private GetStoreByIdUseCase getStoreByIdUseCase;
    @Mock
    private GetConfigurationByPosUseCase getConfigurationByPosUseCase;
    @InjectMocks
    private GetPosConfigurationByUserIdUseCase getPosConfigurationByUserIdUseCase;

    private final String token = "token";

    private final CommandConfigurationResponseDTOMock commandConfiguration = new CommandConfigurationResponseDTOMock();
    private final TreasureEntitiesDTOMock entitiesMockDTO = new TreasureEntitiesDTOMock();
    private final List<TreasureEntitiesDTO> entitiesDTO = entitiesMockDTO.getDataDefaultList();
    private final TreasureDepartmentTaxesDTOMock taxesMockDTO = new TreasureDepartmentTaxesDTOMock();
    private final List<TreasureDepartmentTaxesDTO> taxesDTO = taxesMockDTO.getDataDefaultList();

    private final TreasureDepartmentsDTO treasureDepartmentsDTO = TreasureDepartmentsDTO.builder()
            .name("SEFAZ")
            .entities(entitiesDTO)
            .taxes(taxesDTO)
            .build();

    final CityResponseDTO cityResponseDTO = CityResponseDTO.builder()
            .id(1L)
            .name("Barranquilla")
            .timezone("America/Barranquilla")
            .build();

    final CountryResponseDTO countryResponseDTO = CountryResponseDTO.builder()
            .id(1L)
            .name("Colombia")
            .build();

    final StateResponseDTO stateResponseDTO = StateResponseDTO.builder()
            .id(1L)
            .name("Atlántico")
            .build();

    final CompanyDTO companyDTO = CompanyDTO.builder()
                                            .name("Robinfood Group")
                                            .identification("MFM1906048G1")
            .headquarter(new HeadquarterDTO())
            .country(new CountryResponseDTO(2L, "Mexico")).build();

    final StoreResponseDTO storeResponseDTO = StoreResponseDTO.builder()
            .address("Carr 42 B No. 30 - 25")
            .city(cityResponseDTO)
            .country(countryResponseDTO)
            .currencyType("COP")
            .currencySymbol("$")
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
            .id(1L)
            .company(companyDTO)
            .build();

    final UserStoreConfigurationsResponseDTO userStoreConfigurationsResponseDTO = UserStoreConfigurationsResponseDTO
            .builder()
            .userId(1L)
            .store(storeResponseDTO)
            .build();

    final PosTypeDTO posTypeDTO = PosTypeDTO.builder().id(1L).name("Pos Type Test").build();

    final PosResponseDTO posResponseDTO = PosResponseDTO.builder()
                                            .id(1L)
                                            .posTypes(posTypeDTO)
                                            .name("Pos")
                                            .code("poscode")
                                            .status(true).build();

    final UserStoreResponseDTO userStoreResponseDTO = UserStoreResponseDTO.builder()
            .address(storeResponseDTO.getAddress())
            .city(storeResponseDTO.getCity().getName())
            .country(storeResponseDTO.getCompany().getCountry().getName())
            .id(storeResponseDTO.getId())
            .internalName(storeResponseDTO.getInternalName())
            .name(storeResponseDTO.getName())
            .timeZone(storeResponseDTO.getCity().getTimezone())
            .uuid(storeResponseDTO.getUuid()).build();

    final UserPosResponseDTO userPosResponseDTO = UserPosResponseDTO.builder()
            .id(posResponseDTO.getId())
            .name(posResponseDTO.getName())
            .countryId(storeResponseDTO.getCompany().getCountry().getId())
            .currency(storeResponseDTO.getCurrencyType())
            .isDelivery(false)
            .flowId(5)
            .isMultiBrand(true).build();

    final PosConfigurationResponseDTO posConfigurationResponseDTO = PosConfigurationResponseDTO.builder()
            .store(userStoreResponseDTO)
            .pos(userPosResponseDTO).build();


    @Test
    void test_GetPosConfiguration_Returns_Correctly() {

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(token)
                .build()
        );

        when(getStoreByIdUseCase.findStoreByUserId(anyLong(), anyString())).thenReturn(userStoreConfigurationsResponseDTO);
        when(getConfigurationByPosUseCase.invoke(anyLong(), anyLong())).thenReturn(posResponseDTO);

        PosConfigurationResponseDTO  posConfiguration = getPosConfigurationByUserIdUseCase.invoke(1L);

        assertEquals(posConfiguration, posConfigurationResponseDTO);
    }
}
