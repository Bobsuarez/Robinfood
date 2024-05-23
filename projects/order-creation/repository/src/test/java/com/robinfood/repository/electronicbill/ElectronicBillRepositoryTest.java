package com.robinfood.repository.electronicbill;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.repository.mocks.TransactionRequestDTOMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ElectronicBillRepositoryTest {

    @Mock
    private IElectronicBillRemoteDataSource electronicBillRemoteDataSource;

    @InjectMocks
    private ElectronicBillRepository electronicBillRepository;
    private final TransactionRequestDTOMocks transactionRequestDTOMocks = new TransactionRequestDTOMocks();
    private final TransactionRequestDTO transactionRequestDTO = transactionRequestDTOMocks.transactionRequestDTO;

    @Test
    void test_Send_Electronic_bill_Responds_Correctly() {

        String token = "token";

        when(electronicBillRemoteDataSource.sendElectronicBill(anyString(), any())).thenReturn(Boolean.TRUE);

        final Boolean response = electronicBillRepository.sendElectronicBill(token, transactionRequestDTO);

        assertTrue(response);
    }
}
