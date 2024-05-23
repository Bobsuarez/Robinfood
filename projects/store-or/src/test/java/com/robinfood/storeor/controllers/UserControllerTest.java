package com.robinfood.storeor.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.storeor.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.user.PosConfigurationResponseDTO;
import com.robinfood.storeor.dtos.user.UserPosResponseDTO;
import com.robinfood.storeor.dtos.user.UserStoreResponseDTO;
import com.robinfood.storeor.enums.ApiResponseEnum;
import com.robinfood.storeor.usecase.getposconfiguration.IGetPosConfigurationByUserIdUseCase;
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

import java.nio.charset.StandardCharsets;

import static com.robinfood.storeor.configs.constants.APIConstants.POS_CONFIGURATION;
import static com.robinfood.storeor.configs.constants.APIConstants.USER_V1;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IUserController.class)
class UserControllerTest {

    private static final String BEARER_AUTH = "Bearer ";
    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxNzE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlcl9jcmVhdGlvbl9xdWVyaWVzIl0sInBlciI6WyJvcmNoX21lbnVfc2VydmljZXMiLCJjaGVja1VzZXJEYXRhIiwiY2hlY2tfdXNlcl9kYXRhIiwidGF4ZXMiLCJzeW5jT3JkZXIiLCJDUkVBVEVfVFJBTlNBQ1RJT04iLCJjaGVja0NvbXBhbnlEYXRhIiwiY2hlY2tfY29tcGFueV9kYXRhIiwiVEFYRVNfQkNfU0VSVklDRSIsIlRBWEVTX0JDX0xJU1RfSVRFTV9UQVgiLCJsb3lhbHR5X3RyYW5zYWN0aW9uX2NyZWRpdHMiLCJvcmRlcl9zeW5jIiwiT1JERVJfU1lOQyIsImJjX3NnaV9vcmRlci1yZWFkLWZpbmFuY2VfY2F0ZWdvcmllcyJdLCJ1c2VyIjp7InVzZXJfaWQiOjEyMzQ1NjcsImVtYWlsIjoiam9obmRvZUBteWNvbXBhbnkuY29tIiwiY291bnRyeV9pZCI6MSwiY29tcGFueV9pZCI6MSwiZmlyc3RfbmFtZSI6Ikpob24iLCJsYXN0X25hbWUiOiJEb2UiLCJwaG9uZSI6IjU1NS02MzgzMDIyIiwibWV0YWRhdGEiOnsic3RvcmVfaWQiOjV9fX0.dwuAkNywHlvFmwxk_J2k7KtMzCGXKoS7-wRNl0mlxmIO6CE-7wG0zP7_Q3CPZ6-HW5RjOm_0ZQWR-njxb1DUJQ";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGetPosConfigurationByUserIdUseCase getPosConfigurationByUserIdUseCase;

    @Autowired
    private ObjectMapper mapper;

    final UserStoreResponseDTO userStoreResponseDTO = UserStoreResponseDTO.builder()
            .address("address")
            .city("city")
            .country("country")
            .id(1L)
            .internalName("Internal name")
            .name("Store name")
            .timeZone("America/Bogota")
            .uuid("uuid").build();

    final UserPosResponseDTO userPosResponseDTO = UserPosResponseDTO.builder()
            .id(1L)
            .name("Pos")
            .countryId(2L)
            .currency("COP")
            .isDelivery(true)
            .flowId(5)
            .isMultiBrand(true).build();

    final PosConfigurationResponseDTO posConfigurationResponseDTO = PosConfigurationResponseDTO.builder()
            .store(userStoreResponseDTO)
            .pos(userPosResponseDTO).build();

    @Test
    void test_posConfiguration_Is_Ok() throws Exception {
        when(getPosConfigurationByUserIdUseCase.invoke(anyLong())).thenReturn(posConfigurationResponseDTO);

        AbstractApiResponseBuilderDTO<PosConfigurationResponseDTO> responseBuilderDTO =
                new OkAbstractApiResponseBuilderDTO<>();

        responseBuilderDTO.build(posConfigurationResponseDTO, ApiResponseEnum.RESPONSE_OK_POS_CONFIGURATION);

        var bodyResource = mockMvc.perform(
                    get(USER_V1 + "/1/" + POS_CONFIGURATION)
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
}
