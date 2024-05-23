package com.robinfood.app.usecases.createorderaddress;

import com.robinfood.core.dtos.OrderAddressDTO;
import com.robinfood.core.entities.OrderAddressEntity;
import com.robinfood.repository.orderaddress.IOrderAddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderAddressUseCaseImplTest {

    @Mock
    IOrderAddressRepository orderAddressRepository;

    @InjectMocks
    CreateOrderAddressUseCaseImpl createOrderAddressUseCase;

    @Test
    void when_an_address_is_entered_then_it_saves_it() throws ExecutionException, InterruptedException {

        when(orderAddressRepository.save(new OrderAddressEntity())).thenReturn(new OrderAddressEntity());
        assertEquals(
                createOrderAddressUseCase.invoke(new OrderAddressDTO()).get(),
                true
        );
    }

    @Test
    void when_an_address_is_wrong_throw_exception() {
        when(orderAddressRepository.save(new OrderAddressEntity())).thenThrow(RuntimeException.class);

        try {
            createOrderAddressUseCase.invoke(new OrderAddressDTO());
        } catch (Exception exception) {
            assertEquals(exception, true);
        }

    }
}