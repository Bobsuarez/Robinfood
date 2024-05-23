package com.robinfood.ordereports_bc_muyapp.controller.users;

import com.robinfood.app.library.exception.business.TransactionExecutionException;
import com.robinfood.ordereports_bc_muyapp.constants.APIConstants;
import com.robinfood.ordereports_bc_muyapp.datamock.dto.ResponseOrderDetailDTOMock;
import com.robinfood.ordereports_bc_muyapp.usecases.getuserorderdetailbyuid.IGetUserOrderDetailByUIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

    private static final String BEARER_AUTH = "Bearer ";
    private static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoib3JkZXItY3JlYXRpb24iLCJhdWQiOiJzZXJ2aWNlIiwic3ViIjoib3JkZXItY3JlYXRpb24udjEiLCJwZXIiOltdLCJqdGkiOiIyYzhjNDYzNS0yM2I3LTQ1NDktYjBjMC1mZjMwNzQ2NDM0MmQiLCJpc3MiOiJiYWNrb2ZmaWNlLnYxIiwiaWF0IjoxNjg3NDYyOTkwLCJleHAiOjE5ODc0NjM1OTB9.eoynYjIgNp5AdMrU-7_agSD-j5W5EddTlAjrpk0ymFuy8Dib5vMrC0VVeVvjhgOL5DTgUfnSpLDPlcuv3YT08Q";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IGetUserOrderDetailByUIdUseCase getUserOrderDetailByUIdUseCase;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersController)
                .build();
    }


    @Test
    void test_GetOrderDetailByUserAndOrder_OK_() throws Exception {

        when(getUserOrderDetailByUIdUseCase.invoke(anyString()))
                .thenReturn(ResponseOrderDetailDTOMock.getDataDefault());

        mockMvc.perform(MockMvcRequestBuilders
                                .get(APIConstants.USERS_V1 + "/detail/1927192b-c74e-4d7c-828e-991cd9d73fa7")
                                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void test_GetOrderDetailByUserAndOrder_PreconditionFailed_Returns_Failure() throws Exception {

        doThrow(new TransactionExecutionException("Error al obtener el usuario"))
                .when(getUserOrderDetailByUIdUseCase)
                .invoke(anyString());

        mockMvc.perform(MockMvcRequestBuilders
                                .get(APIConstants.USERS_V1 + "/detail/1927192b-c74e-4d7c-828e-991cd9d73fa7")
                                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isPreconditionFailed());
    }

}