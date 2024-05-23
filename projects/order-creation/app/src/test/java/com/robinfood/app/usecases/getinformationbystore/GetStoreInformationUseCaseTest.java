package com.robinfood.app.usecases.getinformationbystore;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.StoreDTO;
import com.robinfood.core.enums.TransactionCreationResult;
import com.robinfood.core.models.domain.configuration.FlowTax;
import com.robinfood.core.models.domain.configuration.State;
import com.robinfood.core.models.domain.configuration.StoreInformation;
import com.robinfood.repository.configurationsbc.IConfigurationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetStoreInformationUseCaseTest {

    @InjectMocks
    private GetInformationByStoreUseCase getInformationByStoreUseCase;

    @Mock
    private IConfigurationRepository configurationRepository;

    final String token = "token";

    @Test
    void test_Get_Store_Info() {

        StoreDTO store = new StoreDTO();
        store.setId(1L);

        OrderDTO order = OrderDTO.builder().id(1L).store(store).build();
        State state = State.builder().id(1L).build();
        StoreInformation storeInfo = StoreInformation.builder().state(state).build();

        when(configurationRepository.getStoreConfiguration(token, order.getId())).thenReturn(storeInfo);

        TransactionCreationResult result = getInformationByStoreUseCase.invoke(
                token,
                new TransactionRequestDTOMock().transactionRequestDTO
            ).join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_Get_Store_Info_With_Flow_Tax() {

        StoreDTO store = new StoreDTO();
        store.setId(1L);

        OrderDTO order = OrderDTO.builder().id(1L).store(store).build();
        State state = State.builder().id(1L).build();
        FlowTax flowTax = FlowTax.builder().value(2L).build();
        StoreInformation storeInfo = StoreInformation.builder()
                .state(state)
                .flowTax(flowTax)
                .build();

        when(configurationRepository.getStoreConfiguration(token, order.getId())).thenReturn(storeInfo);

        TransactionCreationResult result = getInformationByStoreUseCase.invoke(
                token,
                new TransactionRequestDTOMock().transactionRequestDTO
        ).join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_Get_Store_Info_With_Flow_Tax_isEmpty() {

        StoreDTO store = new StoreDTO();
        store.setId(1L);

        OrderDTO order = OrderDTO.builder().id(1L).store(store).build();
        State state = State.builder().id(1L).build();
        StoreInformation storeInfo = StoreInformation.builder()
                .state(state)
                .build();


        when(configurationRepository.getStoreConfiguration(token, order.getId())).thenReturn(storeInfo);

        TransactionCreationResult result = getInformationByStoreUseCase.invoke(
                token,
                new TransactionRequestDTOMock().transactionRequestDTO
        ).join();

        assertTrue(result instanceof TransactionCreationResult.StepValidationSuccess);
    }

    @Test
    void test_Get_Store_Info_With_parameters_Null() {

        assertThrows(
            NullPointerException.class,
            () -> getInformationByStoreUseCase.invoke(null, null)
        );
    }
}
