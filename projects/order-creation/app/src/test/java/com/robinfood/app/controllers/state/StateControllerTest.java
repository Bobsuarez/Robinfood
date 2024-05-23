package com.robinfood.app.controllers.state;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.app.controllers.states.StateController;
import com.robinfood.app.usecases.changestatusorders.IChangeOrderStateUseCase;
import com.robinfood.app.usecases.validchangeorder.IValidChangeOrderUseCase;
import com.robinfood.core.dtos.apiresponsebuilder.ApiResponseDTO;
import com.robinfood.core.dtos.staterequestdto.StateChangeRequestDTO;
import com.robinfood.core.dtos.staterespondto.StateChangeRespondDTO;
import com.robinfood.core.extensions.ObjectExtensions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.robinfood.core.constants.PermissionConstants.CREATE_TRANSACTION;
import static com.robinfood.core.constants.APIConstants.STATE_V1;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.robinfood.app.controllers.BaseTestController;

@ExtendWith(MockitoExtension.class)
class StateControllerTest extends BaseTestController {
    private MockMvc mockMvc;

    private final String modules = "orch_order";

    @InjectMocks
    private StateController controller;

    @Mock
    private IChangeOrderStateUseCase changeOrderStateUseCase;

    @Mock
    private IValidChangeOrderUseCase validChangeOrderUseCase;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .addPlaceholderValue("spring.mvc.allowed-origin-bcc", "*")
                .build();
    }

    @Test
    void test_ChangeOrderState_Completes_Successfully() throws Exception {

        StateChangeRequestDTO stateChangeRequestDTO = new StateChangeRequestDTO();
        stateChangeRequestDTO.setOrderId(1L);
        stateChangeRequestDTO.setStatusCode("DELIVERY_GOING_TO_STORE");
        stateChangeRequestDTO.setUserId(1L);
        stateChangeRequestDTO.setNotes("prueba");

        StateChangeRespondDTO stateChangeRespondDTO = new StateChangeRespondDTO();
        final String token = getJWT(modules, CREATE_TRANSACTION);
        when(changeOrderStateUseCase.invoke(stateChangeRequestDTO))
                .thenReturn(stateChangeRespondDTO);

        mockMvc.perform(post(STATE_V1)
                        .content(ObjectExtensions.toJson(stateChangeRequestDTO))
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(ObjectExtensions.toJson(new ApiResponseDTO<>(stateChangeRespondDTO))));
    }

    @Test
    void test_ChangeOrderState_Exception() throws Exception {

        StateChangeRequestDTO stateChangeRequestDTO = new StateChangeRequestDTO();

        final String token = getJWT(modules, CREATE_TRANSACTION);

        mockMvc.perform(post(STATE_V1)
                        .content(ObjectExtensions.toJson(stateChangeRequestDTO))
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
