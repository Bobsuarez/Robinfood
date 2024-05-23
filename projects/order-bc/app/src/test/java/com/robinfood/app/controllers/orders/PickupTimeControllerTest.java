package com.robinfood.app.controllers.orders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.app.datamocks.dto.input.pickuptime.PickupTimeDTOMock;
import com.robinfood.app.datamocks.dto.output.PickupTimeResponseDTOMock;
import com.robinfood.app.mappers.PickupTimeMappers;
import com.robinfood.app.usecases.savepickuptime.ISavePickupTimeUseCase;
import com.robinfood.core.dtos.pickuptime.PickupTimeDTO;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class PickupTimeControllerTest {

    private MockMvc mockMvc;
    private JacksonTester<PickupTimeDTO> jsonPickupTime;

    @Mock
    private ISavePickupTimeUseCase savePickupTimeUseCase;

    @Mock
    private PickupTimeMappers pickupTimeMappers;

    @InjectMocks
    private PickupTimeController controller;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .build();
    }

    @Test
    void given_pickup_time_then_return_pickup_time_saved() throws Exception {
        // Arrange
        var pickupTime = PickupTimeDTOMock.build();
        var pickupTimeResponse = PickupTimeResponseDTOMock.build();

        when(pickupTimeMappers.domainListToDtoList(any())).thenReturn(
            Collections.singletonList(pickupTimeResponse)
        );

        // Act - Assert
        mockMvc.perform(
                post("/api/v1/orders/pickup-time")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonPickupTime.write(pickupTime).getJson())
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data[0].id").isNotEmpty())
            .andExpect(jsonPath("$.data[0].id").value(pickupTimeResponse.getId()));
    }

}
