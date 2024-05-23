package com.robinfood.storeor.controllers.resolutions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.storeor.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivateDTO;
import com.robinfood.storeor.dtos.configurationposbystore.ResolutionDTO;
import com.robinfood.storeor.dtos.configurationposbystore.StoreResolutionsDTO;
import com.robinfood.storeor.dtos.findAllResolutions.SearchResolutionDTO;
import com.robinfood.storeor.dtos.response.ResponseResolutionsWithPosDTO;
import com.robinfood.storeor.dtos.response.DataResolutionResponseDTO;
import com.robinfood.storeor.enums.ApiResponseEnum;
import com.robinfood.storeor.mocks.dto.resolutions.ResolutionsListResponseDTOMock;
import com.robinfood.storeor.mocks.dto.resolutions.StoreResolutionsDTOMock;
import com.robinfood.storeor.mocks.dto.resolutions.ResolutionDTOMock;
import com.robinfood.storeor.mocks.dto.resolutions.ActivateOrDeactivateDTOMock;
import com.robinfood.storeor.mocks.dto.resolutions.SearchResolutionDTOMock;
import com.robinfood.storeor.mocks.dto.resolutions.PageableResponseDTOMock;
import com.robinfood.storeor.usecase.createresolutions.ICreateResolutionsUseCase;
import com.robinfood.storeor.usecase.activateordeactivateresolutions.IActivateOrDeactivateResolutionsUseCase;
import com.robinfood.storeor.usecase.findallresolutions.IFindAllResolutionsUseCase;
import com.robinfood.storeor.usecase.updateresolutions.IUpdateResolutionsUseCase;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.GET_ALL;
import static com.robinfood.storeor.configs.constants.APIConstants.POS_STORES_V1;
import static com.robinfood.storeor.configs.constants.APIConstants.RESOLUTIONS;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IResolutionsController.class)
public class ResolutionsControllerTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxNzE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlcl9jcmVhdGlvbl9xdWVyaWVzIl0sInBlciI6WyJvcmNoX21lbnVfc2VydmljZXMiLCJjaGVja1VzZXJEYXRhIiwiY2hlY2tfdXNlcl9kYXRhIiwidGF4ZXMiLCJzeW5jT3JkZXIiLCJDUkVBVEVfVFJBTlNBQ1RJT04iLCJjaGVja0NvbXBhbnlEYXRhIiwiY2hlY2tfY29tcGFueV9kYXRhIiwiVEFYRVNfQkNfU0VSVklDRSIsIlRBWEVTX0JDX0xJU1RfSVRFTV9UQVgiLCJsb3lhbHR5X3RyYW5zYWN0aW9uX2NyZWRpdHMiLCJvcmRlcl9zeW5jIiwiT1JERVJfU1lOQyIsImJjX3NnaV9vcmRlci1yZWFkLWZpbmFuY2VfY2F0ZWdvcmllcyJdLCJ1c2VyIjp7InVzZXJfaWQiOjEyMzQ1NjcsImVtYWlsIjoiam9obmRvZUBteWNvbXBhbnkuY29tIiwiY291bnRyeV9pZCI6MSwiY29tcGFueV9pZCI6MSwiZmlyc3RfbmFtZSI6Ikpob24iLCJsYXN0X25hbWUiOiJEb2UiLCJwaG9uZSI6IjU1NS02MzgzMDIyIiwibWV0YWRhdGEiOnsic3RvcmVfaWQiOjV9fX0.dwuAkNywHlvFmwxk_J2k7KtMzCGXKoS7-wRNl0mlxmIO6CE-7wG0zP7_Q3CPZ6-HW5RjOm_0ZQWR-njxb1DUJQ";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICreateResolutionsUseCase createResolutionsUseCase;

    @MockBean
    private IUpdateResolutionsUseCase updateResolutionsUseCase;

    @MockBean
    private IActivateOrDeactivateResolutionsUseCase activateOrDeactivateResolutionsUseCase;

    @MockBean
    private IFindAllResolutionsUseCase findAllResolutionsUseCase;

    @Autowired
    private ObjectMapper objectMapper;


    final StoreResolutionsDTO storeResolutionsDTOMock = new StoreResolutionsDTOMock().defaultData();
    final ResolutionDTO resolutionDTOMock = new ResolutionDTOMock().defaultData();
    final ActivateOrDeactivateDTO activateOrDeactivateDTO = new ActivateOrDeactivateDTOMock().defaultData();

    final SearchResolutionDTO searchResolutionDTO = new SearchResolutionDTOMock().defaultData();

    List<ResponseResolutionsWithPosDTO> responseResolutionsWithPosDTOs = List.of(
            ResponseResolutionsWithPosDTO.builder()
                    .posId(1L)
                    .id(1L)
                    .build()
    );

    @Test
    void test_Create_Resolutions() throws Exception {

        when(createResolutionsUseCase.invoke(storeResolutionsDTOMock))
                .thenReturn(responseResolutionsWithPosDTOs);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(POS_STORES_V1 + RESOLUTIONS)
                        .content(objectMapper.writeValueAsString(storeResolutionsDTOMock))
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void test_Update_Resolutions() throws Exception {

        Long resolutionId = 1L;

        doNothing().when(updateResolutionsUseCase).invoke(resolutionDTOMock, 1L);

        mockMvc.perform(MockMvcRequestBuilders
                        .put(POS_STORES_V1 + RESOLUTIONS + "/" + resolutionId)
                        .content(objectMapper.writeValueAsString(resolutionDTOMock))
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    void test_activateOrDeactivate_Resolutions() throws Exception {

        Long resolutionId = 1L;

        doNothing().when(activateOrDeactivateResolutionsUseCase).invoke(activateOrDeactivateDTO, 1L);

        mockMvc.perform(MockMvcRequestBuilders
                        .patch(POS_STORES_V1 + RESOLUTIONS + "/" + resolutionId + "/active")
                        .content(objectMapper.writeValueAsString(activateOrDeactivateDTO))
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }


    @Test
    void test_call_findAllResolutions() throws Exception {

        DataResolutionResponseDTO dataResolutionResponseDTO = new DataResolutionResponseDTO
                (Arrays.asList(new ResolutionsListResponseDTOMock().defaultData()), new PageableResponseDTOMock().defaultData());


        when(findAllResolutionsUseCase.invoke(searchResolutionDTO)).thenReturn(dataResolutionResponseDTO);

        AbstractApiResponseBuilderDTO<DataResolutionResponseDTO> responseBuilderDTO = new OkAbstractApiResponseBuilderDTO<>();

        responseBuilderDTO.build(dataResolutionResponseDTO, ApiResponseEnum.RESPONSE_OK_FIND_ALL_RESOLUTIONS);

        var bodyResource = mockMvc.perform(
                        get(POS_STORES_V1 + RESOLUTIONS + GET_ALL + "/?page=1&size=10&status=true&valueCustomFilter=&orderByEndDateResolution=")
                                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals(bodyResource, objectMapper.writeValueAsString(responseBuilderDTO.getApiResponseDTO()),
                new CustomComparator(JSONCompareMode.LENIENT, new Customization("locale", (o1, o2) -> true) ));
    }


}
