package com.robinfood.app.usecases.getinformationbystore;

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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetInformationByStoreUseCaseTest {

    @Mock
    private IConfigurationRepository configurationRepository;

    @InjectMocks
    private GetInformationByStoreUseCase getInformationByStoreUseCase;

    final String token = "token";

    @Test
    void test_Get_Information_By_Store_Success() {

        StoreDTO store = new StoreDTO();
        store.setId(1L);

        OrderDTO order = OrderDTO.builder().id(1L).store(store).build();
        State state = State.builder().id(1L).build();
        StoreInformation storeInfo = StoreInformation.builder().state(state).build();

        when(configurationRepository.getStoreConfiguration(token, order.getId())).thenReturn(storeInfo);

        TransactionCreationResult response = getInformationByStoreUseCase.invoke(
                token,
                new TransactionRequestDTOMock().transactionRequestDTO
        ).join();

        assertNotNull(response);
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

        TransactionCreationResult response = getInformationByStoreUseCase.invoke(
                token,
                new TransactionRequestDTOMock().transactionRequestDTO
        ).join();

        assertNotNull(response);
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

        TransactionCreationResult response = getInformationByStoreUseCase.invoke(
                token,
                new TransactionRequestDTOMock().transactionRequestDTO
        ).join();

        assertNotNull(response);
    }

    @Test
    void test_Get_Store_Info_With_parameters_Null() {

        assertThrows(
            NullPointerException.class,
            () -> getInformationByStoreUseCase.invoke(null, null)
        );
    }
}
