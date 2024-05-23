package com.robinfood.configurations.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.constants.PermissionsConstants;
import com.robinfood.configurations.models.Store;
import com.robinfood.configurations.models.UserStore;
import com.robinfood.configurations.samples.StoreSample;
import com.robinfood.configurations.services.UserStoreService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UserStoreController.class)
@TestPropertySource(properties = {
        "jwt.token.mod=configurations_bc"
})
class UserStoreControllerTest extends BaseControllerTest {

    private static final String MODULES = "configurations_bc";

    private static final String ENCODING = "utf-8";

    private static final Long TEST_LONG = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserStoreService userStoreService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_FindStoreByUserId_Should_ReturnStore_When_ReturnCompleteDataSuccess200()
            throws Exception {

        Store store = StoreSample.getStore();

        UserStore userStore = UserStore.builder()
                .store(store)
                .userId(TEST_LONG)
                .build();

        when(userStoreService.findStoreByUserId(anyLong())).thenReturn(userStore);

        String token = getJWT(MODULES, PermissionsConstants.SERVICE, "service");
        mockMvc.perform(get("/v1/user-stores/users/1/stores")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(ENCODING)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(content().string(Matchers.containsString("id")))
                .andExpect(jsonPath("$.error").isEmpty())
                .andExpect(jsonPath("$.data").isNotEmpty());
    }
}
