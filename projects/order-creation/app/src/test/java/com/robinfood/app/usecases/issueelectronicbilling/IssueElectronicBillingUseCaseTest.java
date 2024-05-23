package com.robinfood.app.usecases.issueelectronicbilling;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.getposresolution.GetPosResolutionUseCase;
import com.robinfood.core.dtos.transactionrequestdto.CompanyDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.repository.electronicbill.IElectronicBillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssueElectronicBillingUseCaseTest {

    @Mock
    private IElectronicBillRepository electronicBillRepository;

    @Mock
    private GetPosResolutionUseCase getPosResolutionUseCase;

    @InjectMocks
    private IssueElectronicBillingUseCase issueElectronicBillingUseCase;

    private TransactionRequestDTO transactionRequest = new TransactionRequestDTOMock().transactionRequestDTO;

    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(issueElectronicBillingUseCase, "electronicBillStoreIds", List.of(1L, 2L));
    }

    @Test
    void test_sendElectronicBill_Col_successfully() {

        String token = "token";

        doNothing().when(getPosResolutionUseCase).invoke(anyString(),any(TransactionRequestDTO.class));

        when(electronicBillRepository.sendElectronicBill(any(), any()))
                .thenReturn(Boolean.TRUE);

        issueElectronicBillingUseCase.invoke(token, transactionRequest);

        verify(electronicBillRepository).sendElectronicBill(eq(token), eq(transactionRequest));
    }

    @Test
    void test_sendElectronicBill_Br_successfully() {

        String token = "token";

        transactionRequest.setCompany(new CompanyDTO("Brazil", 5L));

        lenient().when(electronicBillRepository.sendElectronicBill(any(), any()))
                .thenReturn(Boolean.TRUE);

        issueElectronicBillingUseCase.invoke(token, transactionRequest);

        verifyNoInteractions(electronicBillRepository);
    }

    @Test
    void test_sendElectronicBill_COL_Paid_Is_False_successfully() {

        String token = "token";

        transactionRequest.setPaid(Boolean.FALSE);

        doNothing().when(getPosResolutionUseCase).invoke(anyString(),any(TransactionRequestDTO.class));

        when(electronicBillRepository.sendElectronicBill(any(), any()))
                .thenReturn(Boolean.TRUE);

        issueElectronicBillingUseCase.invoke(token, transactionRequest);

        verify(electronicBillRepository).sendElectronicBill(eq(token), eq(transactionRequest));
    }
}
