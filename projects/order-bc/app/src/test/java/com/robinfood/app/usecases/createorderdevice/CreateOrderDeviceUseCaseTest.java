package com.robinfood.app.usecases.createorderdevice;

import com.robinfood.core.dtos.request.transaction.RequestDeviceDTO;
import com.robinfood.core.entities.OrderDeviceEntity;
import com.robinfood.repository.orderdevice.IOrderDeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CreateOrderDeviceUseCaseTest {

    @Mock
    private IOrderDeviceRepository orderDeviceRepository;

    @InjectMocks
    private CreateOrderDeviceUseCase createOrderDeviceUseCase;

    private final RequestDeviceDTO requestDeviceDTO = new RequestDeviceDTO(
            "172.80.4.207",
            1L,
            "America/Bogota",
            "1.5"
    );

    private final List<Long> orderIds = new ArrayList<>(Arrays.asList(
            1L
    ));

    private final List<OrderDeviceEntity> orderDeviceEntities = new ArrayList<>(Arrays.asList(
            new OrderDeviceEntity(
                    null,
                    null,
                    "172.80.4.207",
                    1L,
                    1L,
                    "1.5"
            )
    ));

    @Test
    void test_Create_Order_Device(){
        Mockito.when(orderDeviceRepository.saveAll(orderDeviceEntities))
                .thenReturn(orderDeviceEntities);

        createOrderDeviceUseCase.invoke(requestDeviceDTO, orderIds).join();

        Mockito.verify(orderDeviceRepository).saveAll(orderDeviceEntities);
    }
}
