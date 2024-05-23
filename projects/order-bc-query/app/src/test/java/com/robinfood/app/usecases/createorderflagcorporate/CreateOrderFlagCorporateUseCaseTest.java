package com.robinfood.app.usecases.createorderflagcorporate;

import com.robinfood.core.dtos.request.order.OrderFlagCorporateDTO;
import com.robinfood.core.entities.OrderFlagCorporateEntity;
import com.robinfood.repository.orderflagcorporate.IOrderFlagCorporateRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderFlagCorporateUseCaseTest {

    @Mock
    private IOrderFlagCorporateRepository mockOrderFlagCorporateRepository;

    @InjectMocks
    private CreateOrderFlagCorporateUseCase createOrderFlagCorporateUseCase;

    private final OrderFlagCorporateEntity orderFlagCorporateEntity = new OrderFlagCorporateEntity(
            null,
            1L,
            1L
    );

    private final OrderFlagCorporateDTO orderFlagCorporateDTO = new OrderFlagCorporateDTO(
            1L,
            true,
            1L
    );

    @Test
    void test_CreateOrderFlagCorporate_When_Save_Success() {

        when(mockOrderFlagCorporateRepository.saveAll(Arrays.asList(orderFlagCorporateEntity)))
                .thenReturn(Arrays.asList(orderFlagCorporateEntity));

        final Boolean result = createOrderFlagCorporateUseCase
                .invoke(Arrays.asList(orderFlagCorporateDTO))
                .join();

        Mockito.verify(mockOrderFlagCorporateRepository)
                .saveAll(Arrays.asList(orderFlagCorporateEntity));

        Assertions.assertTrue(result);
    }
}
