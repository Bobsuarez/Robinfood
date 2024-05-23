package com.robinfood.storeor.usecase.createelectronicbilling;

import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingRequestDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingResponseDTO;
import com.robinfood.storeor.entities.token.TokenModel;
import com.robinfood.storeor.mocks.dto.electronicbilling.CreateElectronicBillingRequestDTOMocks;
import com.robinfood.storeor.mocks.dto.electronicbilling.CreateElectronicBillingResponseDTOMocks;
import com.robinfood.storeor.repositories.electronicbillingrepository.IElectronicBillingRepository;
import com.robinfood.storeor.usecase.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateElectronicBillingUseCaseTest {
    private final String TOKEN = "token";
    final CreateElectronicBillingRequestDTO createElectronicBillingRequestDTOMock =
            new CreateElectronicBillingRequestDTOMocks().createElectronicBillingRequestDTO;

    final CreateElectronicBillingResponseDTO createElectronicBillingResponseDTOMock =
            new CreateElectronicBillingResponseDTOMocks().createElectronicBillingResponseDTO;

    @Mock
    private IElectronicBillingRepository mockCreateElectronicBillingRepository;

    @Mock
    private IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;

    @InjectMocks
    private CreateElectronicBillingUseCase createElectronicBillingUseCase;

    @Test
    void test_Create_Order_Electronic_Billing_Orders() {
        when(mockCreateElectronicBillingRepository.save(TOKEN, createElectronicBillingRequestDTOMock))
                .thenReturn(createElectronicBillingResponseDTOMock);

        when(getTokenBusinessCapabilityUseCase.invoke()).thenReturn(TokenModel.builder()
                .accessToken(TOKEN)
                .build()
        );

        final CreateElectronicBillingResponseDTO result =
                createElectronicBillingUseCase.invoke(createElectronicBillingRequestDTOMock);

        assertEquals(createElectronicBillingResponseDTOMock.getId(), result.getId());
        assertEquals(createElectronicBillingResponseDTOMock.getCreatedAt(), result.getCreatedAt());
        assertEquals(createElectronicBillingResponseDTOMock.getMessage(), result.getMessage());
    }
}