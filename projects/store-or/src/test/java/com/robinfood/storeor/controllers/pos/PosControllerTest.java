package com.robinfood.storeor.controllers.pos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.storeor.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.configurationposbystore.ActivateOrDeactivatePosDTO;
import com.robinfood.storeor.dtos.listposresponse.PosListResponseDTO;
import com.robinfood.storeor.enums.ApiResponseEnum;
import com.robinfood.storeor.mocks.dto.PosDTOMock;
import com.robinfood.storeor.mocks.dto.listposresponse.PosListResponseDTOMock;
import com.robinfood.storeor.mocks.dto.pos.ActivateOrDeactivatePosDTOMock;
import com.robinfood.storeor.mocks.dto.pos.StoreCreatePosDTOMock;
import com.robinfood.storeor.usecase.activateordeactivatepos.IActivateOrDeactivatePosConfigurationsUseCase;
import com.robinfood.storeor.usecase.getlistpos.IGetListPos;
import com.robinfood.storeor.usecase.pos.createposconfiguration.ICreatePosConfigurationsUseCase;
import com.robinfood.storeor.usecase.updatepos.IUpdatePosUseCase;
import com.robinfood.storeor.utils.ObjectMapperUtils;
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

import static com.robinfood.storeor.configs.constants.APIConstants.POS;
import static com.robinfood.storeor.configs.constants.APIConstants.POS_ID;
import static com.robinfood.storeor.configs.constants.APIConstants.POS_STORES_V1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IPosController.class)
public class PosControllerTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxNzE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlcl9jcmVhdGlvbl9xdWVyaWVzIl0sInBlciI6WyJvcmNoX21lbnVfc2VydmljZXMiLCJjaGVja1VzZXJEYXRhIiwiY2hlY2tfdXNlcl9kYXRhIiwidGF4ZXMiLCJzeW5jT3JkZXIiLCJDUkVBVEVfVFJBTlNBQ1RJT04iLCJjaGVja0NvbXBhbnlEYXRhIiwiY2hlY2tfY29tcGFueV9kYXRhIiwiVEFYRVNfQkNfU0VSVklDRSIsIlRBWEVTX0JDX0xJU1RfSVRFTV9UQVgiLCJsb3lhbHR5X3RyYW5zYWN0aW9uX2NyZWRpdHMiLCJvcmRlcl9zeW5jIiwiT1JERVJfU1lOQyIsImJjX3NnaV9vcmRlci1yZWFkLWZpbmFuY2VfY2F0ZWdvcmllcyJdLCJ1c2VyIjp7InVzZXJfaWQiOjEyMzQ1NjcsImVtYWlsIjoiam9obmRvZUBteWNvbXBhbnkuY29tIiwiY291bnRyeV9pZCI6MSwiY29tcGFueV9pZCI6MSwiZmlyc3RfbmFtZSI6Ikpob24iLCJsYXN0X25hbWUiOiJEb2UiLCJwaG9uZSI6IjU1NS02MzgzMDIyIiwibWV0YWRhdGEiOnsic3RvcmVfaWQiOjV9fX0.dwuAkNywHlvFmwxk_J2k7KtMzCGXKoS7-wRNl0mlxmIO6CE-7wG0zP7_Q3CPZ6-HW5RjOm_0ZQWR-njxb1DUJQ";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICreatePosConfigurationsUseCase createPosConfigurationsUseCase;

    @MockBean
    private IUpdatePosUseCase updatePosUseCase;

    @MockBean
    private IActivateOrDeactivatePosConfigurationsUseCase activateOrDeactivatePosConfigurationsUseCase;

    @MockBean
    private IGetListPos getListPos;

    @Autowired
    private ObjectMapper objectMapper;

    final PosListResponseDTO posListResponseDTO = PosListResponseDTOMock.build();

    final ActivateOrDeactivatePosDTO activateOrDeactivateDTO = new ActivateOrDeactivatePosDTOMock().defaultData();

    @Test
    void test_Create_Pos_Status_True() throws Exception {

        when(createPosConfigurationsUseCase.invoke(StoreCreatePosDTOMock.getDataDefault(true)))
                .thenReturn(StoreCreatePosDTOMock.getDataDefault(true));

        mockMvc.perform(MockMvcRequestBuilders
                        .post(POS_STORES_V1 + POS)
                        .content(objectMapper.writeValueAsString(StoreCreatePosDTOMock.getDataDefault(true)))
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void test_Create_Pos_Status_False() throws Exception {

        when(createPosConfigurationsUseCase.invoke(StoreCreatePosDTOMock.getDataDefault(false)))
                .thenReturn(StoreCreatePosDTOMock.getDataDefault(false));

        mockMvc.perform(MockMvcRequestBuilders
                        .post(POS_STORES_V1 + POS)
                        .content(objectMapper.writeValueAsString(StoreCreatePosDTOMock.getDataDefault(false)))
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void test_UpdatePos_When_DataOK_Should_IsAccept() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .put(POS_STORES_V1 + POS + POS_ID, 1L)
                        .content(ObjectMapperUtils.objectToJson(PosDTOMock.getDataDefault()))
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    void test_activateOrDeactivate_Pos() throws Exception {

        Long posId = 1L;

        doNothing().when(activateOrDeactivatePosConfigurationsUseCase).invoke(activateOrDeactivateDTO, 1L);

        mockMvc.perform(MockMvcRequestBuilders
                        .patch(POS_STORES_V1 + POS + "/" + posId + "/active")
                        .content(objectMapper.writeValueAsString(activateOrDeactivateDTO))
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    void test_Pos_Is_Ok() throws Exception {
        when(getListPos.invoke(anyInt(), anyString(), anyInt(), anyLong(), anyLong(), any(Sort.class)))
                .thenReturn(new PageImpl<>(List.of(posListResponseDTO), PageRequest.of(0, 10), 10));

        AbstractApiResponseBuilderDTO<Page<PosListResponseDTO>> responseBuilderDTO = new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(new PageImpl<>(List.of(posListResponseDTO), PageRequest.of(0, 10), 10),
                ApiResponseEnum.RESPONSE_OK_POS_CONFIGURATION);

        var bodyResource = mockMvc.perform(
                        get(POS_STORES_V1 + POS + "/all?storeId=1&page=1&size=10&sorted=true")
                                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals(bodyResource, objectMapper.writeValueAsString(responseBuilderDTO.getApiResponseDTO()),
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("locale", (o1, o2) -> true)
                ));
    }

    @Test
    void test_Pos_status_false_Is_Ok() throws Exception {
        when(getListPos.invoke(anyInt(), anyString(), anyInt(), anyLong(), anyLong(), any(Sort.class)))
                .thenReturn(new PageImpl<>(List.of(posListResponseDTO), PageRequest.of(0, 10), 10));

        AbstractApiResponseBuilderDTO<Page<PosListResponseDTO>> responseBuilderDTO = new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(new PageImpl<>(List.of(posListResponseDTO), PageRequest.of(0, 10), 10),
                ApiResponseEnum.RESPONSE_OK_POS_CONFIGURATION);

        var bodyResource = mockMvc.perform(
                        get(POS_STORES_V1 + POS + "/all?status=false&storeId=1&page=1&size=10&sorted=true")
                                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals(bodyResource, objectMapper.writeValueAsString(responseBuilderDTO.getApiResponseDTO()),
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("locale", (o1, o2) -> true)
                ));
    }

    @Test
    void test_Pos_status_true_Is_Ok() throws Exception {
        when(getListPos.invoke(anyInt(), anyString(), anyInt(), anyLong(), anyLong(), any(Sort.class)))
                .thenReturn(new PageImpl<>(List.of(posListResponseDTO), PageRequest.of(0, 10), 10));

        AbstractApiResponseBuilderDTO<Page<PosListResponseDTO>> responseBuilderDTO = new OkAbstractApiResponseBuilderDTO<>();
        responseBuilderDTO.build(new PageImpl<>(List.of(posListResponseDTO), PageRequest.of(0, 10), 10),
                ApiResponseEnum.RESPONSE_OK_POS_CONFIGURATION);

        var bodyResource = mockMvc.perform(
                        get(POS_STORES_V1 + POS + "/all?status=true&storeId=1&page=1&size=10&sorted=true")
                                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals(bodyResource, objectMapper.writeValueAsString(responseBuilderDTO.getApiResponseDTO()),
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("locale", (o1, o2) -> true)
                ));
    }
}
