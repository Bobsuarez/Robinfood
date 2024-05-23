package com.robinfood.localserver.app.controllers;

import com.robinfood.localserver.commons.dtos.http.ApiResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.robinfood.localserver.commons.constants.GlobalConstants.PING_V1;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PingController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    void test_GetOrderDetail_Should_Return_Ok() throws Exception {
        ApiResponseDTO<String> response = new ApiResponseDTO<>();
        response.setData("The service is running");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(PING_V1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}