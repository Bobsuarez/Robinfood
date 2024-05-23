package com.robinfood.app.controllers.orders.consolidatedorderdetail;

import com.robinfood.app.mocks.OrderDetailPlusHistoryDTOMock;
import com.robinfood.app.usecases.getorderdetailsplushistory.GetConsolidatedOrderDetailUseCase;
import com.robinfood.core.enums.Result;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.robinfood.core.constants.APIConstants.ORDER_PAID_V1;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(properties = {
        "jwt-token-prefix=Bearer ",
        "jwt-token-secret=gyXNfCp4Qx4Gn9OV7qK7uwRUX4f4bgrEEPvjdeLQFciHwtCKrc5rCm1kuNECIFP6",
        "jwt-token-aud=internal",
        "jwt-token-mod=order_creation_queries"
})
@ExtendWith(MockitoExtension.class)
public class OrderDetailsPlusHistoryControllerTest {

    private static final String BEARER_AUTH = "Bearer ";

    private static final String
            TOKEN =
            "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJwcmludC1sb2NhbC1zZXJ2aWNlIiwiYXVkIjoiaW50ZXJuYWwiLCJtb2QiOlsibWVudV9iYyIsIm1lbnVfYmFzZSIsIk1FTlVfRElTQ09VTlRTX1NFUlZJQ0VTIiwicG9zdjIiLCJvcmRlcl9jcmVhdGlvbl9xdWVyaWVzIiwib3JkZXJfY3JlYXRpb24iLCJvcmRlcl9iYyJdLCJwZXIiOlsibWVudV9iYXNlX3NlcnZpY2VzIiwibWVudV9iY19zZXJ2aWNlcyIsImFkX29yZGVyX2xpc3Rfb3JkZXJzIiwiYWRfb3JkZXJfbGlzdF9kZXRhaWwiLCJhZF9vcmRlcl9saXN0X2hpc3RvcnkiLCJhZF9vcmRlcl9saXN0X3ByaW50X29yZGVycyIsImFkX29yZGVyX2NyZWF0ZV90cmFuc2FjdGlvbiIsIm9yX29yZGVyX2RhaWx5X3NhbGVzX3N1bW1hcnkiLCJvcl9vcmRlcl9kYWlseV9zYWxlc19wYXltZW50X21ldGhvZHMiLCJvcl9vcmRlcl9kYWlseV9zYWxlc19wYXltZW50X21ldGhvZF9ncmFwaGljcyIsIm9yX29yZGVyX2hpc3Rvcnlfc3RhdHVzX3ZpZXciLCJvcl92aWV3X2hpc3RvcnlfY2FuY2VsZWQiXSwianRpIjoiNmRlOGNmZDQtYWMyMS00OTU4LTkwZjQtNjY2ZTNjN2NlZmZjIiwiZXhwIjoxNjQ2NDY4MDAzLCJpYXQiOjE2NDY0MjQ4MDMsIm5iZiI6MCwidXNlciI6eyJjb21wYW5pZXMiOlt7Im5hbWUiOiJNVVkiLCJ0aW1lem9uZSI6IkFtZXJpY2EvQm9nb3RhIiwiaWQiOjF9LHsibmFtZSI6Ik1VWSBNRVhJQ08iLCJ0aW1lem9uZSI6IkFtZXJpY2EvQm9nb3RhIiwiaWQiOjJ9LHsibmFtZSI6Ik1VWSBCUkFTSUwiLCJ0aW1lem9uZSI6IkFtZXJpY2EvU2FvX1BhdWxvIiwiaWQiOjV9XSwidXNlcl9pZCI6NCwicGhvbmUiOiIzMDU4MTQyNjUzIiwiYWxsb3dfYWRtaW4iOnRydWUsImRlZmF1bHRfY29tcGFueV9pZCI6NSwibGVnYWN5X2lkIjoyNiwibGFzdF9uYW1lIjoiU2lsdmEiLCJmaXJzdF9uYW1lIjoiU2VyZ2lvIiwiZW1haWwiOiJzc2lsdmFAbXV5LmNvbS5jbyIsImNvdW50cnlfaWQiOjF9LCJzdWIiOjQsImNvbXBhbnlfaWQiOjV9.hqH5yA96ebYSKP7vftiYJgPWJFUHjbIPUC1JMNb-Q4Sd5XqcWJc95nNjMpObBpcTbV8l-FUS4ucsHxNz0vlheg";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GetConsolidatedOrderDetailUseCase getConsolidatedOrderDetailUseCase;

    @Test
    void test_Invoke_Should_GetOrdersPaidUseCase_When_invokeCommand() throws Exception {

        when(getConsolidatedOrderDetailUseCase.invoke(anyString()))
                .thenReturn(new Result.Success<>(OrderDetailPlusHistoryDTOMock.getDataDefault()));

        mockMvc.perform(MockMvcRequestBuilders
                .get(ORDER_PAID_V1  + "/paid-orders/4782947hjsjsg/detail")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void test_Invoke_Should_BadRequest_When_EndPointGenerateError() throws Exception {

        when(getConsolidatedOrderDetailUseCase.invoke(anyString()))
                .thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.BAD_REQUEST));

        mockMvc.perform(MockMvcRequestBuilders
                .get(ORDER_PAID_V1  + "/paid-orders/4782947hjsjsg/detail")
                .header(HttpHeaders.AUTHORIZATION, BEARER_AUTH + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}
