package com.robinfood.configurations.controllers.v1;

import static com.robinfood.configurations.constants.PermissionsConstants.CONFIGURATIONS_PREFIX;
import static com.robinfood.configurations.constants.PermissionsConstants.LIST_CHANNELS;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.StoreDTO;
import com.robinfood.configurations.models.Channel;
import com.robinfood.configurations.services.ChannelService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ChannelController.class)
@TestPropertySource(properties = {
    "jwt.token.mod=configurations_bc"
})
class ChannelControllerTest extends BaseControllerTest {

    @MockBean
    private ChannelService channelService;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private String module = "configurations_bc";

    private static final String JSON = "{\"sort\":\"name\"}";

    @Test
    void test_findChannelById_Should_ReturnChannel_When_ReturnCompleteDataSuccess200() throws Exception {
        Channel channel = getChannel();
        String token = getJWT(module, SERVICE, "service");

        //Mock
        when(channelService.findById(anyLong())).thenReturn(channel);

        //Call to code to test
        executeGetRequest(token, "/v1/channels/1");
    }

    @Test
    void test_listChannel_Should_Return200_When_ValidInput() throws Exception {
        Channel channel = getChannel();
        channel.setName("APP");
        List<Channel> channelList = List.of(getChannel(), channel);
        String token = getJWT(module, CONFIGURATIONS_PREFIX + LIST_CHANNELS, "service");

        //Mock
        when(channelService.list(anyString(), any(
            Pageable.class))).thenReturn(new PageImpl<>(
            channelList, PageRequest.of(0, 10), 10));
        //Call to code to test
        executeGetRequest(token, "/v1/channels?name=Autogestion&page=0&sort=name,asc");
        executeGetRequest(token, "/v1/channels?name=Autogestion&page=0&sort=name,desc");
        executeGetRequest(token, "/v1/channels?name=Autogestion&page=0&sort=name,des");
        executeGetRequest(token, "/v1/channels?name=Autogestion&page=0&sorted=false");
        executeGetRequest(token, "/v1/channels?name=Autogestion&page=0&sort=id,asc");
        executeGetRequest(token, "/v1/channels?name=Autogestion&page=0&sort=id,desc");

    }


    @Test
    void test_listChannel_Should_Return200_When_PageEmpty() throws Exception {
        String token = getJWT(module, CONFIGURATIONS_PREFIX + LIST_CHANNELS, "service");

        //Mock
        when(channelService.list(anyString(), any(Pageable.class))).thenReturn(new PageImpl<>(
            List.of(), PageRequest.of(0, 1), 0));

        //Call to code to test
        executeGetRequest(token, "/v1/channels?page=0&unsorted=true&sorted=true&empty=true");
        executeGetRequest(token, "/v1/channels?page=0&unsorted=true&sorted=true&empty=true");
        executeGetRequest(token, "/v1/channels?size=1&unsorted=true&sorted=false&empty=true");

    }


    @Test
    void test_listChannel_Should_Return200_When_SortDistinct() throws Exception {

        String token = getJWT(module, CONFIGURATIONS_PREFIX + LIST_CHANNELS, "service");
        List<Channel> channelList = List.of(getChannel());

        //Mock
        when(channelService.list(anyString(), any(Pageable.class))).thenReturn(new PageImpl<>(
            channelList, PageRequest.of(0, 10), 10));

        //Call to code to test
        mockMvc.perform(get("/v1/channels?page=0&name=&unsorted=true&sorted=true&empty=true")
                .header(HttpHeaders.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content("{\"sort\":\"id\"}")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty());

    }


    @Test
    void test_listChannel_Should_ReturnUnauthorized_When_BearerIsNotValid()
        throws Exception {
        String token = getJWT(module, "INVALID_PERMISSION",
            "service");

        //Call to code to test
        MvcResult mvcResult = mockMvc.perform(get("/v1/channels")
                .header(HttpHeaders.AUTHORIZATION, "Bearer" + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andReturn();

        ApiResponseDTO<List<StoreDTO>> result = objectMapper
            .readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                });
        assertNotNull(result);
        assertEquals(401, result.getCode());
        assertNotNull(result.getMessage());
        assertEquals("Unauthorized", result.getMessage());
        assertNull(result.getData());

    }


    private Channel getChannel() {
        return Channel.builder().id(1L).name("Autogestion").build();

    }


    private void executeGetRequest(String token, String path) throws Exception {

        mockMvc.perform(get(path)
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(200))
            .andExpect(jsonPath("$.message").isNotEmpty())
            .andExpect(jsonPath("$.error").isEmpty())
            .andExpect(jsonPath("$.data").isNotEmpty());
    }

}