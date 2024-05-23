package com.robinfood.app.controllers.paymentmethods;

import com.robinfood.app.usecases.getlistpaymentmethods.IGetListPaymentMethodsUseCase;
import com.robinfood.core.dtos.PaymentMethodEntityDTO;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.robinfood.core.constants.APIConstants.PAYMENT_METHODS_V1;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(properties = {
        "jwt-token-prefix=Bearer ",
        "jwt.token.secret=gyXNfCp4Qx4Gn9OV7qK7uwRUX4f4bgrEEPvjdeLQFciHwtCKrc5rCm1kuNECIFP6",
        "jwt-token-aud=internal",
        "jwt-token-mod=order_creation_queries"
})
@ExtendWith(MockitoExtension.class)
class PaymentMethodsControllerTest {

    private static final String BEARER_AUTH = "Bearer ";
    private static final String
            TOKEN =
            "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9" +
                    ".eyJpc3MiOiJwcmludC1sb2NhbC1zZXJ2aWNlIiwiYXVkIjoiaW50ZXJuYWwiLCJtb2QiOlsibWVudV9iYyIsIm1lbnVfYmFzZSIsIk1FTlVfRElTQ09VTlRTX1NFUlZJQ0VTIiwicG9zdjIiLCJvcmRlcl9jcmVhdGlvbl9xdWVyaWVzIiwib3JkZXJfY3JlYXRpb24iLCJvcmRlcl9iYyJdLCJwZXIiOlsibWVudV9iYXNlX3NlcnZpY2VzIiwibWVudV9iY19zZXJ2aWNlcyIsImFkX29yZGVyX2xpc3Rfb3JkZXJzIiwiYWRfb3JkZXJfbGlzdF9kZXRhaWwiLCJhZF9vcmRlcl9saXN0X2hpc3RvcnkiLCJhZF9vcmRlcl9saXN0X3ByaW50X29yZGVycyIsImFkX29yZGVyX2NyZWF0ZV90cmFuc2FjdGlvbiIsIm9yX29yZGVyX2RhaWx5X3NhbGVzX3N1bW1hcnkiLCJvcl9vcmRlcl9kYWlseV9zYWxlc19wYXltZW50X21ldGhvZHMiLCJvcl9vcmRlcl9kYWlseV9zYWxlc19wYXltZW50X21ldGhvZF9ncmFwaGljcyJdLCJqdGkiOiI2ZGU4Y2ZkNC1hYzIxLTQ5NTgtOTBmNC02NjZlM2M3Y2VmZmMiLCJleHAiOjE2NDY0NjgwMDMsImlhdCI6MTY0NjQyNDgwMywibmJmIjowLCJ1c2VyIjp7ImNvbXBhbmllcyI6W3sibmFtZSI6Ik1VWSIsInRpbWV6b25lIjoiQW1lcmljYS9Cb2dvdGEiLCJpZCI6MX0seyJuYW1lIjoiTVVZIE1FWElDTyIsInRpbWV6b25lIjoiQW1lcmljYS9Cb2dvdGEiLCJpZCI6Mn0seyJuYW1lIjoiTVVZIEJSQVNJTCIsInRpbWV6b25lIjoiQW1lcmljYS9TYW9fUGF1bG8iLCJpZCI6NX1dLCJ1c2VyX2lkIjo0LCJwaG9uZSI6IjMwNTgxNDI2NTMiLCJhbGxvd19hZG1pbiI6dHJ1ZSwiZGVmYXVsdF9jb21wYW55X2lkIjo1LCJsZWdhY3lfaWQiOjI2LCJsYXN0X25hbWUiOiJTaWx2YSIsImZpcnN0X25hbWUiOiJTZXJnaW8iLCJlbWFpbCI6InNzaWx2YUBtdXkuY29tLmNvIiwiY291bnRyeV9pZCI6MX0sInN1YiI6NCwiY29tcGFueV9pZCI6NX0.53cVBXL8PuMWcFLrCHJ6fyXCR64RlvxbSO39rxysXdVdFG-EA3hWxcGg2W897a0t6unFv8VjDmXAV0ExCwtH4A";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IGetListPaymentMethodsUseCase getListPaymentMethodsUseCase;

    @Test
    void test_getPosResolution_Request_OK() throws Exception {

        when(getListPaymentMethodsUseCase.invoke())
                .thenReturn(List.of(PaymentMethodEntityDTO.builder().build()));

        mockMvc.perform(MockMvcRequestBuilders
                .get(PAYMENT_METHODS_V1)
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
