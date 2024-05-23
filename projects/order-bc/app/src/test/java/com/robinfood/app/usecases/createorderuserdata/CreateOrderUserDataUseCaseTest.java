package com.robinfood.app.usecases.createorderuserdata;

import com.robinfood.app.mappers.input.OrderUserDataMapper;
import com.robinfood.core.dtos.request.transaction.RequestUserDTO;
import com.robinfood.core.entities.OrderUserDataEntity;
import com.robinfood.repository.orderuserdata.IOrderUserDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderUserDataUseCaseTest {

    @Mock
    private IOrderUserDataRepository orderUserDataRepository;

    @InjectMocks
    private CreateOrderUserDataUseCase createOrderUserDataUseCase;

    private final List<RequestUserDTO> requestUserDTOList = new ArrayList<>(Arrays.asList(
            new RequestUserDTO(
                    "donnajea@j9ysy.com",
                    1L,
                    "Lawrence",
                    "405-951-3360",
                    "Bobby M.",
                    1L,
                    "57",
                    1L
            )
    ));

    final List<OrderUserDataEntity> orderUserDataEntities = requestUserDTOList.stream()
            .map(OrderUserDataMapper::toOrderUserDataEntity)
            .collect(Collectors.toList());

    @Test
    void test_Create_Order_User_Data(){
        when(orderUserDataRepository.saveAll(orderUserDataEntities))
                .thenReturn(orderUserDataEntities);

        Boolean result = createOrderUserDataUseCase
                .invoke(requestUserDTOList)
                .join();

        verify(orderUserDataRepository)
                .saveAll(orderUserDataEntities);

        assertTrue(result);
    }
}
