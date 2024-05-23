package com.robinfood.storeor.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.storeor.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.configurationposbystore.StorePosDTO;
import com.robinfood.storeor.dtos.response.CityResponseDTO;
import com.robinfood.storeor.dtos.response.CountryResponseDTO;
import com.robinfood.storeor.dtos.response.StateResponseDTO;
import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentCategoryProductDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentTaxesDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentsDTO;
import com.robinfood.storeor.dtos.response.TreasureEntitiesDTO;
import com.robinfood.storeor.enums.ApiResponseEnum;
import com.robinfood.storeor.mocks.dto.TreasureDepartmentCategoryProductDTOMock;
import com.robinfood.storeor.mocks.dto.TreasureDepartmentTaxesDTOMock;
import com.robinfood.storeor.mocks.dto.TreasureEntitiesDTOMock;
import com.robinfood.storeor.mocks.dto.commandConfiguration.CommandConfigurationResponseDTOMock;
import com.robinfood.storeor.mocks.dto.configurationposbystore.StorePosDTOMock;
import com.robinfood.storeor.usecase.getconfigurationbystore.IGetConfigurationByStoreUseCase;
import com.robinfood.storeor.usecase.getconfigurationposbystore.IGetConfigurationPosByStoreUseCase;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.STORE_V1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IStoreController.class)
class StoreControllerTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxNzE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlcl9jcmVhdGlvbl9xdWVyaWVzIl0sInBlciI6WyJvcmNoX21lbnVfc2VydmljZXMiLCJjaGVja1VzZXJEYXRhIiwiY2hlY2tfdXNlcl9kYXRhIiwidGF4ZXMiLCJzeW5jT3JkZXIiLCJDUkVBVEVfVFJBTlNBQ1RJT04iLCJjaGVja0NvbXBhbnlEYXRhIiwiY2hlY2tfY29tcGFueV9kYXRhIiwiVEFYRVNfQkNfU0VSVklDRSIsIlRBWEVTX0JDX0xJU1RfSVRFTV9UQVgiLCJsb3lhbHR5X3RyYW5zYWN0aW9uX2NyZWRpdHMiLCJvcmRlcl9zeW5jIiwiT1JERVJfU1lOQyIsImJjX3NnaV9vcmRlci1yZWFkLWZpbmFuY2VfY2F0ZWdvcmllcyJdLCJ1c2VyIjp7InVzZXJfaWQiOjEyMzQ1NjcsImVtYWlsIjoiam9obmRvZUBteWNvbXBhbnkuY29tIiwiY291bnRyeV9pZCI6MSwiY29tcGFueV9pZCI6MSwiZmlyc3RfbmFtZSI6Ikpob24iLCJsYXN0X25hbWUiOiJEb2UiLCJwaG9uZSI6IjU1NS02MzgzMDIyIiwibWV0YWRhdGEiOnsic3RvcmVfaWQiOjV9fX0.dwuAkNywHlvFmwxk_J2k7KtMzCGXKoS7-wRNl0mlxmIO6CE-7wG0zP7_Q3CPZ6-HW5RjOm_0ZQWR-njxb1DUJQ";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGetConfigurationByStoreUseCase getConfigurationByStoreUseCase;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private IGetConfigurationPosByStoreUseCase getConfigurationPosByStoreUseCase;

    private final List<StorePosDTO> storePosDTOS = new StorePosDTOMock().storePosDTOs;

    private final TreasureEntitiesDTOMock entitiesMockDTO = new TreasureEntitiesDTOMock();

    private final List<TreasureEntitiesDTO> entitiesDTO = entitiesMockDTO.getDataDefaultList();

    private final TreasureDepartmentCategoryProductDTOMock productsMockDTO =
            new TreasureDepartmentCategoryProductDTOMock();

    private final List<TreasureDepartmentCategoryProductDTO> productsDTO = productsMockDTO.getDataDefaultList();

    private final CommandConfigurationResponseDTOMock commandConfigurationResponseDTO =
            new CommandConfigurationResponseDTOMock();

    private final TreasureDepartmentTaxesDTOMock taxesMockDTO = new TreasureDepartmentTaxesDTOMock();

    private final List<TreasureDepartmentTaxesDTO> taxesDTO = taxesMockDTO.getDataDefaultList();

    private final TreasureDepartmentsDTO treasureDepartmentsDTO = TreasureDepartmentsDTO.builder()
            .entities(entitiesDTO)
            .name("SEFAZ")
            .products(productsDTO)
            .taxes(taxesDTO)
            .build();

    final StoreResponseDTO storeResponseDTO = StoreResponseDTO
            .builder()
            .address("Carr 42 B No. 30 - 25")
            .city(
                    CityResponseDTO.builder()
                            .id(1L)
                            .name("Medellín")
                            .build()
            )
            .country(CountryResponseDTO.builder()
                    .id(1L)
                    .name("Colombia")
                    .build()
            )
            .email("store1@robinfood.com")
            .identification("123")
            .internalName("test")
            .location("Medellín")
            .name("MUY 79")
            .phone("3508907012")
            .timezone("UTC")
            .state(StateResponseDTO.builder()
                    .id(1L)
                    .name("Meta")
                    .build()
            )
            .treasuryDepartment(treasureDepartmentsDTO)
            .commandConfiguration(commandConfigurationResponseDTO.getDefautlListCommandConfiguration())
            .build();

    @Test
    void test_GetConfigurationByStore_Is_Ok() throws Exception {
        when(getConfigurationByStoreUseCase.invoke(anyLong(), anyBoolean()))
                .thenReturn(storeResponseDTO);

        AbstractApiResponseBuilderDTO<StoreResponseDTO> responseBuilderDTO = new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(storeResponseDTO, ApiResponseEnum.RESPONSE_OK_STORE);

        var bodyResource = mockMvc.perform(
                        get(STORE_V1 + "/1/configuration")
                                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals(bodyResource, mapper.writeValueAsString(responseBuilderDTO.getApiResponseDTO()),
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("locale", (o1, o2) -> true)
                ));
    }

    @Test
    void test_GetConfigurationStoreList_Is_Ok() throws Exception {
        when(getConfigurationByStoreUseCase.invoke(anyString(), anyLong(), anyInt(), anyInt(), any(Sort.class)))
                .thenReturn(new PageImpl<>(List.of(storeResponseDTO), PageRequest.of(0, 10), 10));

        AbstractApiResponseBuilderDTO<Page<StoreResponseDTO>> responseBuilderDTO = new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(new PageImpl<>(List.of(storeResponseDTO), PageRequest.of(0, 10), 10), ApiResponseEnum.RESPONSE_OK_STORE);

        var bodyResource = mockMvc.perform(
                        get(STORE_V1 + "/?company_country_id=1&page=0&sorted=true&unsorted=true&empty=true")
                                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals(bodyResource, mapper.writeValueAsString(responseBuilderDTO.getApiResponseDTO()),
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("locale", (o1, o2) -> true)
                ));
    }

    /**
     * Test Get Pos by Store
     */
    @Test
    void test_GetConfigurationPosByStoreController_invoke_Is_OK() throws Exception {
        when(getConfigurationPosByStoreUseCase.invoke(anyLong()))
                .thenReturn(storePosDTOS);

        AbstractApiResponseBuilderDTO<List<StorePosDTO>> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();

        responseBuilderDTO.build(storePosDTOS, ApiResponseEnum.RESPONSE_OK_POS_BY_STORE);

        var bodyResource = mockMvc.perform(
                        get(STORE_V1 + "/1/" + "pos")
                                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);


        JSONAssert.assertEquals(bodyResource, mapper.writeValueAsString(responseBuilderDTO.getApiResponseDTO()),
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("locale", (o1, o2) -> true)
                ));
    }

    @Test
    void test_GetConfigurationPosByStoreController_invoke_Is_Fail() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get(STORE_V1 + "/hhhhhhhh/" + "pos")
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}