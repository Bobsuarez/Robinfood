package com.robinfood.storeor.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.storeor.dtos.apiresponsebuilder.AbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.apiresponsebuilder.OkAbstractApiResponseBuilderDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingRequestDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingResponseDTO;
import com.robinfood.storeor.dtos.electronicbilling.ElectronicBillDTO;
import com.robinfood.storeor.enums.ApiResponseEnum;
import com.robinfood.storeor.mocks.dto.electronicbilling.CreateElectronicBillingRequestDTOMocks;
import com.robinfood.storeor.mocks.dto.electronicbilling.CreateElectronicBillingResponseDTOMocks;
import com.robinfood.storeor.mocks.dto.electronicbilling.ElectronicBillDTOMock;
import com.robinfood.storeor.usecase.createelectronicbilling.ICreateElectronicBillingUseCase;
import com.robinfood.storeor.usecase.getelectronicbillbyordersid.IGetElectronicBillByOrdersIdUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;

import static com.robinfood.storeor.configs.constants.APIConstants.BILLING_V1;
import static com.robinfood.storeor.configs.constants.APIConstants.ELECTRONIC_BILLING;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(IElectronicBillingController.class)
class ElectronicBillingControllerTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhbmRyb2lkLnYxIiwianRpIjoiNEEyMDc5MkUtODA4NS00QzAyLTg4RDgtQzc2ODc0RDBENjE3Iiwic3ViIjoiMTIzNDU2NyIsIm5hbWUiOiJKb2huIERvZSIsImlhdCI6MTUxNjIzOTAyMiwiZXhwIjoxNzE2MjM5MDIyLCJhdWQiOiJpbnRlcm5hbCIsIm1vZCI6WyJvcmRlcl9jcmVhdGlvbl9xdWVyaWVzIl0sInBlciI6WyJvcmNoX21lbnVfc2VydmljZXMiLCJjaGVja1VzZXJEYXRhIiwiY2hlY2tfdXNlcl9kYXRhIiwidGF4ZXMiLCJzeW5jT3JkZXIiLCJDUkVBVEVfVFJBTlNBQ1RJT04iLCJjaGVja0NvbXBhbnlEYXRhIiwiY2hlY2tfY29tcGFueV9kYXRhIiwiVEFYRVNfQkNfU0VSVklDRSIsIlRBWEVTX0JDX0xJU1RfSVRFTV9UQVgiLCJsb3lhbHR5X3RyYW5zYWN0aW9uX2NyZWRpdHMiLCJvcmRlcl9zeW5jIiwiT1JERVJfU1lOQyIsImJjX3NnaV9vcmRlci1yZWFkLWZpbmFuY2VfY2F0ZWdvcmllcyJdLCJ1c2VyIjp7InVzZXJfaWQiOjEyMzQ1NjcsImVtYWlsIjoiam9obmRvZUBteWNvbXBhbnkuY29tIiwiY291bnRyeV9pZCI6MSwiY29tcGFueV9pZCI6MSwiZmlyc3RfbmFtZSI6Ikpob24iLCJsYXN0X25hbWUiOiJEb2UiLCJwaG9uZSI6IjU1NS02MzgzMDIyIiwibWV0YWRhdGEiOnsic3RvcmVfaWQiOjV9fX0.dwuAkNywHlvFmwxk_J2k7KtMzCGXKoS7-wRNl0mlxmIO6CE-7wG0zP7_Q3CPZ6-HW5RjOm_0ZQWR-njxb1DUJQ";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICreateElectronicBillingUseCase mockCreateElectronicBillingUseCase;

    @MockBean
    private IGetElectronicBillByOrdersIdUseCase mockGetElectronicBillByOrdersIdUseCase;

    final CreateElectronicBillingRequestDTO createElectronicBillingRequestDTOMock =
            new CreateElectronicBillingRequestDTOMocks().createElectronicBillingRequestDTO;

    final CreateElectronicBillingResponseDTO createElectronicBillingResponseDTOMock =
            new CreateElectronicBillingResponseDTOMocks().createElectronicBillingResponseDTO;

    private ElectronicBillDTOMock responseOrderElectronicBillDTO;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_Create_Order_Electronic_Billing() throws Exception {

        when(mockCreateElectronicBillingUseCase.invoke(createElectronicBillingRequestDTOMock))
                .thenReturn(createElectronicBillingResponseDTOMock);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(BILLING_V1 + ELECTRONIC_BILLING)
                        .content(objectMapper.writeValueAsString(createElectronicBillingRequestDTOMock))
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void test_getElectronicBillByOrdersIdCorrect() throws Exception {

        List<Long> ordersId=new ArrayList<>();
        ordersId.add(1l);
        ordersId.add(2l);

        when(mockGetElectronicBillByOrdersIdUseCase.invoke(ordersId))
                .thenReturn(responseOrderElectronicBillDTO.getDefaultListData());

        final AbstractApiResponseBuilderDTO<List<ElectronicBillDTO>> apiResponseDTOBuilder =
                new OkAbstractApiResponseBuilderDTO<>();

        apiResponseDTOBuilder.build(responseOrderElectronicBillDTO.getDefaultListData(), ApiResponseEnum.RESPONSE_OK_ELECTRONIC_BILL_LIST);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(BILLING_V1 + ELECTRONIC_BILLING)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .param("orderIds","1,2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void test_getElectronicBillByOrdersNotparam() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get(BILLING_V1 + ELECTRONIC_BILLING)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_getElectronicBillByOrderswithbadparams() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get(BILLING_V1 + ELECTRONIC_BILLING)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                        .param("orderIds","hhhhhhhh")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
