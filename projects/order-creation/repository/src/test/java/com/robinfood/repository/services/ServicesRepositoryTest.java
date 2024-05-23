package com.robinfood.repository.services;

import com.robinfood.core.dtos.transactionrequestdto.ServiceDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.entities.servicesentities.ServiceListRequestEntity;
import com.robinfood.core.entities.servicesentities.ServiceRequestEntity;
import com.robinfood.repository.mocks.TransactionRequestDTOMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServicesRepositoryTest {

    private final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMocks().transactionRequestDTOServices;

    private final String token = "token";

    private final ServiceDTO serviceDTO = ServiceDTO.builder()
            .id(123L)
            .discount(BigDecimal.valueOf(2))
            .value(BigDecimal.valueOf(21))
            .build();

    private final ServiceRequestEntity serviceRequestEntity = ServiceRequestEntity.builder()
            .grossValue(BigDecimal.valueOf(21))
            .discount(BigDecimal.valueOf(2))
            .netValue(BigDecimal.valueOf(19))
            .storeId(123L)
            .build();

    @Mock
    private IServicesDataSource mockServicesDataSource;

    @InjectMocks
    private ServicesRepository servicesRepository;

    @Test
    void given_service_list_request_entity_when_consumes_service_api_then_response_is_correct() throws Exception {

        when( mockServicesDataSource.validateService(anyString(), any(ServiceListRequestEntity.class)))
                .thenReturn(CompletableFuture.completedFuture(Boolean.TRUE));

        Boolean result = servicesRepository.validateService(token, List.of(serviceDTO), transactionRequestDTO).join();

        assertTrue(result);
    }
}
