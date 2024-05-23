package com.robinfood.repository.electronicbill;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.network.api.OrderBillNumberGeneratorBCAPI;
import com.robinfood.repository.mocks.TransactionRequestDTOMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ElectronicBillRemoteDataSourceTest {

    @Mock
    private OrderBillNumberGeneratorBCAPI orderBillNumberGeneratorBCAPI;

    @Mock
    private Call<ApiResponseEntity> mockApiResponseEntity;

    @InjectMocks
    private ElectronicBillRemoteDataSource electronicBillRemoteDataSource;

    private final TransactionRequestDTOMocks transactionRequestDTOMocks = new TransactionRequestDTOMocks();

    private final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTO;

    private final String token = "token";

    final ApiResponseEntity apiResponseEntityElectronicBill = ApiResponseEntity.builder()
            .code(200)
            .data(new Object())
            .locale("CO")
            .message("Menu es valid")
            .build();

    final ApiResponseEntity<Boolean> apiErrorResponseEntity = ApiResponseEntity.<Boolean>builder()
            .code(400)
            .error("Error")
            .locale("CO")
            .message("INVALID_SEND_ELECTRONIC_BILL")
            .build();

    @Test
    void test_Send_ElectronicBill_Returns_Correctly() throws Exception {

        when(orderBillNumberGeneratorBCAPI.sendElectronicBill(anyString(), any())).thenReturn(mockApiResponseEntity);

        when(mockApiResponseEntity.execute()).thenReturn(Response.success(apiResponseEntityElectronicBill));

        final Boolean result = electronicBillRemoteDataSource.sendElectronicBill(token, transactionRequestDTO);

        assertEquals(true, result);
    }

    @Test
    void test_Send_ElectronicBill_Returns_Exception() throws Exception {
        when(orderBillNumberGeneratorBCAPI.sendElectronicBill(anyString(),  any()))
                .thenReturn(mockApiResponseEntity);

        when(mockApiResponseEntity.execute()).thenThrow(new IOException());

        try {
            electronicBillRemoteDataSource.sendElectronicBill(token, transactionRequestDTO);
        } catch (Exception exception) {
            assertFalse(exception.getLocalizedMessage().contains(apiErrorResponseEntity.getMessage()));
        }
    }
}
